package dorm.system.model;

/**
 * Maps directly to the "Repair_Request" entity in the ERD.
 *
 * PK: requestID
 * FK: residentID -> Resident.residentID
 */
public class RepairRequest {

    private int requestID;
    private int residentID;
    private String status;
    private String issueType;
    private String priority;
    private String description;
    private String dateSubmitted;

    public RepairRequest(
            int requestID,
            int residentID,
            String status,
            String issueType,
            String priority,
            String description,
            String dateSubmitted) {

        this.requestID = requestID;
        this.residentID = residentID;
        this.status = status;
        this.issueType = issueType;
        this.priority = priority;
        this.description = description;
        this.dateSubmitted = dateSubmitted;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getResidentID() {
        return residentID;
    }

    public void setResidentID(int residentID) {
        this.residentID = residentID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    @Override
    public String toString() {
        return "RepairRequest{"
                + "requestID=" + requestID
                + ", residentID=" + residentID
                + ", status='" + status + '\''
                + '}';
    }
}