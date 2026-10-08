package dorm.system.backend;

import dorm.system.model.MaintenanceStaff;
import dorm.system.model.MaintenanceTask;
import dorm.system.model.RepairRequest;
import dorm.system.model.Resident;
import dorm.system.ui.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormDAO {

    // =========================================================
    // LOGIN
    // =========================================================

    public boolean authenticateResident(
            int residentID,
            String password) {

        String sql =
                "SELECT residentID " +
                "FROM Resident " +
                "WHERE residentID = ? AND password = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, residentID);
            statement.setString(2, password);

            try (ResultSet resultSet =
                    statement.executeQuery()) {

                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean authenticateManager(
            int managerID,
            String password) {

        String sql =
                "SELECT managerID " +
                "FROM Hall_Manager " +
                "WHERE managerID = ? AND password = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, managerID);
            statement.setString(2, password);

            try (ResultSet resultSet =
                    statement.executeQuery()) {

                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean authenticateMaintenance(
            int staffID,
            String password) {

        String sql =
                "SELECT staffID " +
                "FROM Maintenance_Staff " +
                "WHERE staffID = ? AND password = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, staffID);
            statement.setString(2, password);

            try (ResultSet resultSet =
                    statement.executeQuery()) {

                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // =========================================================
    // RESIDENT
    // =========================================================

    public Resident getResidentById(int residentID) {

        String sql =
                "SELECT residentID, name, email, password, " +
                "roomNumber, contactNumber " +
                "FROM Resident " +
                "WHERE residentID = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, residentID);

            try (ResultSet rs =
                    statement.executeQuery()) {

                if (rs.next()) {

                    return new Resident(
                            rs.getInt("residentID"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("roomNumber"),
                            rs.getString("contactNumber")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // MAINTENANCE STAFF
    // =========================================================

    public MaintenanceStaff getMaintenanceStaffById(
            int staffID) {

        String sql =
                "SELECT staffID, name, contactNumber " +
                "FROM Maintenance_Staff " +
                "WHERE staffID = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, staffID);

            try (ResultSet rs =
                    statement.executeQuery()) {

                if (rs.next()) {

                    return new MaintenanceStaff(
                            rs.getInt("staffID"),
                            rs.getString("name"),
                            rs.getString("contactNumber")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // REPAIR REQUESTS
    // =========================================================

    public List<RepairRequest> getAllRepairRequests() {

        List<RepairRequest> requests =
                new ArrayList<>();

        String sql =
                "SELECT requestID, residentID, status, " +
                "issueType, priority, description, dateSubmitted " +
                "FROM Repair_Request " +
                "ORDER BY requestID";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            while (rs.next()) {

                RepairRequest request =
                        new RepairRequest(
                                rs.getInt("requestID"),
                                rs.getInt("residentID"),
                                rs.getString("status"),
                                rs.getString("issueType"),
                                rs.getString("priority"),
                                rs.getString("description"),
                                rs.getString("dateSubmitted")
                        );

                requests.add(request);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }


    public List<RepairRequest> getRequestsForResident(
            int residentID) {

        List<RepairRequest> requests =
                new ArrayList<>();

        String sql =
                "SELECT requestID, residentID, status, " +
                "issueType, priority, description, dateSubmitted " +
                "FROM Repair_Request " +
                "WHERE residentID = ? " +
                "ORDER BY requestID";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, residentID);

            try (ResultSet rs =
                    statement.executeQuery()) {

                while (rs.next()) {

                    requests.add(
                            new RepairRequest(
                                    rs.getInt("requestID"),
                                    rs.getInt("residentID"),
                                    rs.getString("status"),
                                    rs.getString("issueType"),
                                    rs.getString("priority"),
                                    rs.getString("description"),
                                    rs.getString("dateSubmitted")
                            )
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }


    public void saveRepairRequest(
            RepairRequest request) {

        String sql =
                "INSERT INTO Repair_Request " +
                "(requestID, residentID, status, issueType, " +
                "priority, description, dateSubmitted) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "status = VALUES(status), " +
                "issueType = VALUES(issueType), " +
                "priority = VALUES(priority), " +
                "description = VALUES(description), " +
                "dateSubmitted = VALUES(dateSubmitted)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    request.getRequestID()
            );

            statement.setInt(
                    2,
                    request.getResidentID()
            );

            statement.setString(
                    3,
                    request.getStatus()
            );

            statement.setString(
                    4,
                    request.getIssueType()
            );

            statement.setString(
                    5,
                    request.getPriority()
            );

            statement.setString(
                    6,
                    request.getDescription()
            );

            statement.setString(
                    7,
                    request.getDateSubmitted()
            );

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // =========================================================
    // MAINTENANCE TASKS
    // =========================================================

    public List<MaintenanceTask> getTasksForStaff(
            int staffID) {

        List<MaintenanceTask> tasks =
                new ArrayList<>();

        String sql =
                "SELECT taskID, requestID, staffID, " +
                "assignedDate, progress, completionDate " +
                "FROM Maintenance_Task " +
                "WHERE staffID = ? " +
                "ORDER BY taskID";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, staffID);

            try (ResultSet rs =
                    statement.executeQuery()) {

                while (rs.next()) {

                    tasks.add(
                            new MaintenanceTask(
                                    rs.getInt("taskID"),
                                    rs.getInt("requestID"),
                                    rs.getInt("staffID"),
                                    rs.getString("assignedDate"),
                                    rs.getString("progress"),
                                    rs.getString("completionDate")
                            )
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        return tasks;
    }

    public MaintenanceTask getTaskForRequest(
            int requestID) {

        String sql =
                "SELECT taskID, requestID, staffID, " +
                "assignedDate, progress, completionDate " +
                "FROM Maintenance_Task " +
                "WHERE requestID = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, requestID);

            try (ResultSet rs =
                    statement.executeQuery()) {

                if (rs.next()) {

                    return new MaintenanceTask(
                            rs.getInt("taskID"),
                            rs.getInt("requestID"),
                            rs.getInt("staffID"),
                            rs.getString("assignedDate"),
                            rs.getString("progress"),
                            rs.getString("completionDate")
                    );
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }
    
    public int getNextTaskId() {

        String sql =
                "SELECT COALESCE(MAX(taskID), 500) + 1 " +
                "FROM Maintenance_Task";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 501;
    }

    public void updateRepairRequestStatus(
            int requestID,
            String newStatus) {

        String sql =
                "UPDATE Repair_Request " +
                "SET status = ? " +
                "WHERE requestID = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, newStatus);
            statement.setInt(2, requestID);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    
    public void updateTaskStatus(
            int taskID,
            int requestID,
            String newStatus) {

        String updateTask =
                "UPDATE Maintenance_Task " +
                "SET progress = ?, " +
                "completionDate = ? " +
                "WHERE taskID = ?";

        String updateRequest =
                "UPDATE Repair_Request " +
                "SET status = ? " +
                "WHERE requestID = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection()
        ) {

            connection.setAutoCommit(false);

            try (
                    PreparedStatement taskStatement =
                            connection.prepareStatement(updateTask);

                    PreparedStatement requestStatement =
                            connection.prepareStatement(updateRequest)
            ) {

                String completionDate = null;

                if ("Completed".equalsIgnoreCase(
                        newStatus)) {

                    completionDate =
                            java.time.LocalDate
                                    .now()
                                    .toString();
                }

                taskStatement.setString(
                        1,
                        newStatus
                );

                taskStatement.setString(
                        2,
                        completionDate
                );

                taskStatement.setInt(
                        3,
                        taskID
                );

                taskStatement.executeUpdate();


                requestStatement.setString(
                        1,
                        newStatus
                );

                requestStatement.setInt(
                        2,
                        requestID
                );

                requestStatement.executeUpdate();


                connection.commit();

            } catch (SQLException e) {

                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // =========================================================
    // ASSIGN MAINTENANCE TASK
    // =========================================================

    public void assignTask(
            int taskID,
            int requestID,
            int staffID,
            String assignedDate,
            String progress) {

        String insertTask =
                "INSERT INTO Maintenance_Task " +
                "(taskID, requestID, staffID, assignedDate, " +
                "progress, completionDate) " +
                "VALUES (?, ?, ?, ?, ?, NULL)";

        String updateRequest =
                "UPDATE Repair_Request " +
                "SET status = ? " +
                "WHERE requestID = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection()
        ) {

            connection.setAutoCommit(false);

            try (
                    PreparedStatement taskStatement =
                            connection.prepareStatement(insertTask);

                    PreparedStatement requestStatement =
                            connection.prepareStatement(updateRequest)
            ) {

                taskStatement.setInt(1, taskID);
                taskStatement.setInt(2, requestID);
                taskStatement.setInt(3, staffID);
                taskStatement.setString(4, assignedDate);
                taskStatement.setString(5, progress);

                taskStatement.executeUpdate();


                requestStatement.setString(
                        1,
                        progress
                );

                requestStatement.setInt(
                        2,
                        requestID
                );

                requestStatement.executeUpdate();


                connection.commit();

            } catch (SQLException e) {

                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}