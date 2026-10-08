package dorm.system.ui;

import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        Connection connection =
                DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println("SUCCESS! MySQL is connected.");
        } else {
            System.out.println("FAILED! MySQL is not connected.");
        }
    }
}