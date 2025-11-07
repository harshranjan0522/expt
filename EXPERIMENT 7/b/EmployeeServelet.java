import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String empid = request.getParameter("empid");

        String url = "jdbc:mysql://localhost:3306/companydb"; // Replace with your DB
        String user = "root";
        String pass = "";

        out.println("<html><body>");
        out.println("<h2>Employee Details</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            PreparedStatement ps;
            if (empid != null && !empid.isEmpty()) {
                ps = conn.prepareStatement("SELECT * FROM Employee WHERE EmpID=?");
                ps.setInt(1, Integer.parseInt(empid));
            } else {
                ps = conn.prepareStatement("SELECT * FROM Employee");
            }

            ResultSet rs = ps.executeQuery();

            out.println("<table border='1'><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" +
                        rs.getString("Name") + "</td><td>" +
                        rs.getDouble("Salary") + "</td></tr>");
            }
            out.println("</table>");

            conn.close();
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}
