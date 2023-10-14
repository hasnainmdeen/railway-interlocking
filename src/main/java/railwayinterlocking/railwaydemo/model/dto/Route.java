package railwayinterlocking.railwaydemo.model.dto;

public class Route extends TrackSection {
    private boolean occupied;

    public Route(String start, String end, boolean occupied) {
        super(start, end);
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
