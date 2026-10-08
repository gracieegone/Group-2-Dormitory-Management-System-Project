package dorm.system.model;

/**
 * Maps directly to the "Faculty_Report" entity in the ERD.
 *
 * PK: reportID
 * FK: managerID -> Hall_Manager.managerID
 */
public class FacultyReport {

    private int reportID;
    private int managerID;
    private String reportDate;
    private String reportDetails;

    public FacultyReport(
            int reportID,
            int managerID,
            String reportDate,
            String reportDetails) {

        this.reportID = reportID;
        this.managerID = managerID;
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    @Override
    public String toString() {
        return "FacultyReport{"
                + "reportID=" + reportID
                + ", managerID=" + managerID
                + '}';
    }
}