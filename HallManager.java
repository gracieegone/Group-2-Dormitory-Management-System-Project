package dorm.system.model;

/**
 * Maps directly to the "Hall_Manager" entity in the ERD.
 *
 * PK: managerID
 */
public class HallManager {

    private int managerID;
    private String name;
    private String email;
    private String password;
    private String contactNumber;

    public HallManager(
            int managerID,
            String name,
            String email,
            String password,
            String contactNumber) {

        this.managerID = managerID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "HallManager{"
                + "managerID=" + managerID
                + ", name='" + name + '\''
                + '}';
    }
}