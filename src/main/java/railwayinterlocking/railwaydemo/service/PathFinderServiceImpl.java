package railwayinterlocking.railwaydemo.service;

import org.springframework.stereotype.Service;
import railwayinterlocking.railwaydemo.model.dto.CheckConflictRequest;
import railwayinterlocking.railwaydemo.model.dto.Route;
import railwayinterlocking.railwaydemo.model.dto.TrackSection;

import java.util.*;

@Service
public class PathFinderServiceImpl implements PathFinderService {

    /*
    The checkConflicts function is used to check the conflicts between the given route and occupied route
    Checks the path for the given route.
    Get the path for the occupied route(s).
    checks if the path of given route overlaps with the path of any occupied route
    INPUT: CheckConflictRequest
    OUTPUT: true if conflicts. Else, false
    */
    public boolean checkConflicts(CheckConflictRequest request) {

        // Checking path for the route to check
        List<TrackSection> checkRoutePath = findPath(request.getStationGraph(), request.getCheckRoute());

        if(checkRoutePath == null){
            return false;
        }

        List<Route> occupiedRoutes = request.getRoutes().stream()
                .filter(Route::isOccupied).toList();

        boolean hasConflict = occupiedRoutes.stream()
                .map(occupiedRoute -> findPath(request.getStationGraph(), occupiedRoute))
                .flatMap(List::stream)
                .anyMatch(occupiedRoutePath -> sectionExistInPath(checkRoutePath, occupiedRoutePath));

        return !hasConflict;
    }

    /*
    The findPath function is used to find the path. It is implemented with BFS algorithm, and it
    traverses the graph in both directions
    INPUT: station graph, route to check
    OUTPUT: List of TrackSection representing the path for the given route.
            Outputs the empty list if no path found
    */
    public List<TrackSection> findPath(List<TrackSection> stationGraph, TrackSection checkRoute) {

        Queue<List<TrackSection>> queue = new LinkedList<>();
        List<TrackSection> dummySection = new ArrayList<>();
        dummySection.add(new TrackSection(checkRoute.getStart(), checkRoute.getStart()));
        queue.add(dummySection);

        while (!queue.isEmpty()) {
            List<TrackSection> path = queue.poll();
            TrackSection currentSection = path.get(path.size() - 1);

            if (currentSection.getEnd().equals(checkRoute.getEnd())) {
                path.remove(0);
                return path;
            }

            for (TrackSection section : stationGraph) {
                if (section.getStart().equals(currentSection.getEnd()) && !sectionExistInPath(path, section)) {
                    List<TrackSection> newPath = new ArrayList<>(path);
                    newPath.add(section);
                    queue.add(newPath);
                }

                if (section.getEnd().equals(currentSection.getEnd()) && !sectionExistInPath(path, section)) {
                    List<TrackSection> newPath = new ArrayList<>(path);
                    newPath.add(new TrackSection(section.getEnd(), section.getStart()));  // reversed section
                    queue.add(newPath);
                }
            }
        }

        return new ArrayList<>();
    }


    /*
    The sectionExistInPath function is used to check whether the path overlaps with a given track section.
    Checks for both forward and backward direction
    INPUT: List<TrackSection> path: a list representing the path, Track Section: section of a path
    OUTPUT: True if path contains the given section. Else returns false
    */
    public boolean sectionExistInPath(List<TrackSection> path, TrackSection section) {
        return path.stream().anyMatch(p ->
                (p.getStart().equals(section.getStart()) && p.getEnd().equals(section.getEnd())) ||
                        (p.getStart().equals(section.getEnd()) && p.getEnd().equals(section.getStart()))
        );
    }

}


