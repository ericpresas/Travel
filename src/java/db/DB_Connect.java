package db;

import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
 
/**
 * Servlet implementation class MySQLConnect
 */
 
public class DB_Connect extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/travel", "eric", "eric");
            PreparedStatement pst = conn.prepareStatement("Select ID_USUARIO,PASSWORD from USUARIOS where ID_USUARIO=? and PASSWORD=?");
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                out.println("Correct login credentials");
                response.sendRedirect("/Travel-master/menu.jsp");
            } 
            else {
            int missatge =1;
            request.setAttribute("tipus", missatge); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward( request, response );

             response.sendRedirect("/Travel-master/error.jsp");
            }
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}