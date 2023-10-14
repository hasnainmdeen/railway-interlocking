package railwayinterlocking.railwaydemo.service;

import railwayinterlocking.railwaydemo.model.dto.CheckConflictRequest;
import railwayinterlocking.railwaydemo.model.dto.TrackSection;

import java.util.List;

public interface PathFinderService {
    public List<TrackSection> findPath(List<TrackSection> stationGraph, TrackSection checkRoute);
    public boolean checkConflicts(CheckConflictRequest request);

    public boolean sectionExistInPath(List<TrackSection> path, TrackSection section);



}
