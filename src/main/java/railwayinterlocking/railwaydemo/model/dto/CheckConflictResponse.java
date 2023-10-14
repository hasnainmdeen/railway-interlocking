package railwayinterlocking.railwaydemo.model.dto;

public class CheckConflictResponse {
    private boolean success;

    public CheckConflictResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
