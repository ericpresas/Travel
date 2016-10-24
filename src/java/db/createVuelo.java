package db;

import java.util.UUID;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
import java.util.Random;
 
/**
 * Servlet implementation class MySQLConnect
 */
 
public class createVuelo extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Random rand = new Random();
        Integer  idVuelo;
        idVuelo = rand.nextInt(1000) + 1;
        String numVuelo = request.getParameter("numvuelo");
        String company = request.getParameter("comp");
        String origen = request.getParameter("origen");
        String destino = request.getParameter("dest");
        String salida = request.getParameter("salida");
        String llegada = request.getParameter("llegada");
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/travel", "eric", "eric");
            PreparedStatement pst = conn.prepareStatement("insert into vuelos (ID_VUELO,NUM_VUELO,COMPANYIA,ORIGEN,HORA_SALIDA,DESTINO,HORA_LLEGADA) "
                    + "                                         values(?,?,?,?,?,?,?)");

            pst.setInt(1,idVuelo);
            pst.setString(2,numVuelo);
            pst.setString(3,company);
            pst.setString(4,origen);
            pst.setString(5,salida);
            pst.setString(6,destino);
            pst.setString(7,llegada);
            pst.executeUpdate();
            response.sendRedirect("/travel/createVuelo.jsp");
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}