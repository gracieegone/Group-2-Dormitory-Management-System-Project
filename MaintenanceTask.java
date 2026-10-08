package dorm.system.model;

/**
 * Maps directly to the "Maintenance_Task" entity in the ERD.
 *
 * PK: taskID
 * FK: requestID -> Repair_Request.requestID
 * FK: staffID   -> Maintenance_Staff.staffID
 */
public class MaintenanceTask {

    private int taskID;
    private int requestID;
    private int staffID;
    private String assignedDate;
    private String progress;
    private String completionDate;

    public MaintenanceTask(
            int taskID,
            int requestID,
            int staffID,
            String assignedDate,
            String progress,
            String completionDate) {

        this.taskID = taskID;
        this.requestID = requestID;
        this.staffID = staffID;
        this.assignedDate = assignedDate;
        this.progress = progress;
        this.completionDate = completionDate;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public String toString() {
        return "MaintenanceTask{"
                + "taskID=" + taskID
                + ", requestID=" + requestID
                + ", staffID=" + staffID
                + ", progress='" + progress + '\''
                + '}';
    }
}