package db;

import java.util.UUID;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
        List travel = new ArrayList();
        travel.add(idVuelo);
        travel.add(request.getParameter("numvuelo"));
        travel.add(request.getParameter("comp"));
        travel.add(request.getParameter("origen"));
        travel.add(request.getParameter("dest"));
        travel.add(request.getParameter("salida"));
        travel.add(request.getParameter("llegada"));
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/travel", "eric", "eric");
            PreparedStatement pst = conn.prepareStatement("insert into vuelos (ID_VUELO,NUM_VUELO,COMPANYIA,ORIGEN,HORA_SALIDA,DESTINO,HORA_LLEGADA) "
                    + "                                         values(?,?,?,?,?,?,?)");

            pst.setInt(1,(Integer) travel.get(0));
            pst.setString(2,(String) travel.get(1));
            pst.setString(3,(String) travel.get(2));
            pst.setString(4,(String) travel.get(3));
            pst.setString(5,(String) travel.get(4));
            pst.setString(6,(String) travel.get(5));
            pst.setString(7,(String) travel.get(6));
            pst.executeUpdate();
            
            request.setAttribute("travel", travel); 
            System.out.println(travel);
            RequestDispatcher dispatcher = request.getRequestDispatcher("createVuelo.jsp");
            dispatcher.forward( request, response );
            response.sendRedirect("/travel/createVuelo.jsp");
            
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}