package railwayinterlocking.railwaydemo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CheckConflictRequest {
    @JsonProperty("station_graph")
    private List<TrackSection> stationGraph;
    private List<Route> routes;

    @JsonProperty("check_route")
    private TrackSection checkRoute;

    public CheckConflictRequest(List<TrackSection> stationGraph, List<Route> routes, TrackSection checkRoute) {
        this.stationGraph = stationGraph;
        this.routes = routes;
        this.checkRoute = checkRoute;
    }

    public List<TrackSection> getStationGraph() {
        return stationGraph;
    }

    public void setStationGraph(List<TrackSection> stationGraph) {
        this.stationGraph = stationGraph;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public TrackSection getCheckRoute() {
        return checkRoute;
    }

    public void setCheckRoute(TrackSection checkRoute) {
        this.checkRoute = checkRoute;
    }
}

