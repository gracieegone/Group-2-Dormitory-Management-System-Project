package dorm.system.model;

/**
 * Maps directly to the "Resident" entity in the ERD.
 *
 * PK: residentID
 */
public class Resident {

    private int residentID;
    private String name;
    private String email;
    private String password;
    private String roomNumber;
    private String contactNumber;

    public Resident(
            int residentID,
            String name,
            String email,
            String password,
            String roomNumber,
            String contactNumber) {

        this.residentID = residentID;
        this.name = name;
        this.setEmail(email);
        this.setPassword(password);
        this.roomNumber = roomNumber;
        this.setContactNumber(contactNumber);
    }

    public int getResidentID() {
        return residentID;
    }

    public void setResidentID(int residentID) {
        this.residentID = residentID;
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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Resident{"
                + "residentID=" + residentID
                + ", name='" + name + '\''
                + ", roomNumber='" + roomNumber + '\''
                + '}';
    }
}