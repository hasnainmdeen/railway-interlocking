package railwayinterlocking.railwaydemo.model.dto;


public class TrackSection {
    private String start;
    private String end;

    public TrackSection(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TrackSection{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
