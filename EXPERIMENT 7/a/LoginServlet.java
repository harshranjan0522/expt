import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String uname = request.getParameter("username");
        String pass = request.getParameter("password");

        // Hardcoded validation (you can use DB validation too)
        if (uname.equals("admin") && pass.equals("1234")) {
            out.println("<h2>Welcome, " + uname + "!</h2>");
            out.println("<p>Login Successful ✅</p>");
        } else {
            out.println("<h3 style='color:red;'>Invalid Username or Password ❌</h3>");
            out.println("<a href='login.html'>Try Again</a>");
        }
    }
}
