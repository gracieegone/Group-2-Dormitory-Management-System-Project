package dorm.system.model;

/**
 * Maps directly to the "Maintenance_Staff" entity in the ERD.
 *
 * PK: staffID
 */
public class MaintenanceStaff {

    private int staffID;
    private String name;
    private String contactNumber;

    public MaintenanceStaff(
            int staffID,
            String name,
            String contactNumber) {

        this.staffID = staffID;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "MaintenanceStaff{"
                + "staffID=" + staffID
                + ", name='" + name + '\''
                + '}';
    }
}