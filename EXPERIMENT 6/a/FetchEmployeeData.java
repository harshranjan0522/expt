package a;
import java.sql.*;

public class FetchEmployeeData {

    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/companydb"; // replace 'companydb' with your DB name
        String username = "root"; // replace with your MySQL username
        String password = "";     // replace with your MySQL password

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Connected to the database successfully!\n");

            // Step 3: Create a Statement object
            stmt = conn.createStatement();

            // Step 4: Execute SQL query
            String query = "SELECT EmpID, Name, Salary FROM Employee";
            rs = stmt.executeQuery(query);

            // Step 5: Display the data
            System.out.println("Employee Records:");
            System.out.println("----------------------------------");
            System.out.printf("%-10s %-20s %-10s%n", "EmpID", "Name", "Salary");
            System.out.println("----------------------------------");

            while (rs.next()) {
                int empID = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                System.out.printf("%-10d %-20s %-10.2f%n", empID, name, salary);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found. Please add the connector jar file.");
        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
        } finally {
            // Step 6: Close connections
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("\n✅ Connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("❌ Error closing connection: " + e.getMessage());
            }
        }
    }
}
