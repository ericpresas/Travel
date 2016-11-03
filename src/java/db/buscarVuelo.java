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
 
public class buscarVuelo extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List travel = new ArrayList();
        PrintWriter out = response.getWriter();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/travel", "eric", "eric");
            PreparedStatement pst;
            List queryList = new ArrayList();
            travel.add(request.getParameter("origen"));
            travel.add(request.getParameter("destino"));
            travel.add(request.getParameter("companyia"));
            System.out.print(travel);
            String query="select * from vuelos where COMPANYIA='"+travel.get(2).toString()+"' AND DESTINO='"+travel.get(1).toString()+"' AND ORIGEN='"+travel.get(0).toString()+"'";                
            System.out.println(query);
            pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            System.out.println("**************Resultats**************");
        List all = new ArrayList();

        while ( rs.next() ) {
            all.add(rs.getInt("id_vuelo"));
            all.add(rs.getString("num_vuelo"));
            all.add(rs.getString("companyia"));
            all.add(rs.getString("origen"));
            all.add(rs.getString("hora_salida"));
            all.add(rs.getString("destino"));
            all.add(rs.getString("hora_llegada"));
            
            System.out.println(all);
        }
       
        if(all.isEmpty())
        {
              int missatge =2;
            request.setAttribute("tipus", missatge); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward( request, response );

             response.sendRedirect("/Travel-master/error.jsp");
        }
        
            request.setAttribute("search", all); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("buscarVuelo.jsp");
            dispatcher.forward( request, response );
            response.sendRedirect("/Travel-master/buscarVuelo.jsp");


            
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}