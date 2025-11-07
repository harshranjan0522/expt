package c;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb"; // Replace with your DB name
    private static final String USER = "root";  // Replace with your username
    private static final String PASS = "";      // Replace with your password

    Connection conn;

    public StudentDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
        }
    }

    // Add Student
    public void addStudent(Student s) {
        String sql = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getStudentID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDepartment());
            ps.setDouble(4, s.getMarks());
            ps.executeUpdate();
            System.out.println("✅ Student added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding student: " + e.getMessage());
        }
    }

    // View All Students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("StudentID"),
                        rs.getString("Name"),
                        rs.getString("Department"),
                        rs.getDouble("Marks")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving students: " + e.getMessage());
        }
        return list;
    }

    // Update Student
    public void updateStudent(Student s) {
        String sql = "UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setDouble(3, s.getMarks());
            ps.setInt(4, s.getStudentID());
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Student updated successfully!");
            else
                System.out.println("⚠️ No student found with that ID.");
        } catch (SQLException e) {
            System.out.println("❌ Error updating student: " + e.getMessage());
        }
    }

    // Delete Student
    public void deleteStudent(int id) {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Student deleted successfully!");
            else
                System.out.println("⚠️ No student found with that ID.");
        } catch (SQLException e) {
            System.out.println("❌ Error deleting student: " + e.getMessage());
        }
    }
}
