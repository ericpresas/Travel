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
        int option=0;
        if (request.getParameter("busquedasimple") != null) {
            travel.add(request.getParameter("busquedasimple"));
            option=1;
        }
        else{
            travel.add(Integer.valueOf(request.getParameter("idvuelo")));
            travel.add(request.getParameter("numvuelo"));
            travel.add(request.getParameter("comp"));
            travel.add(request.getParameter("origen"));
            travel.add(request.getParameter("dest"));
            travel.add(request.getParameter("salida"));
            travel.add(request.getParameter("llegada"));        
        }
        
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/travel", "eric", "eric");
            PreparedStatement pst;
            List queryList = new ArrayList();
            Boolean firstNull=false;
            Boolean first=false;
            String query="select * from vuelos where ";
            
            if (travel.get(0)!=null) {
                query=query + "id_vuelo=? ";
                firstNull=true;
                first=true;
                queryList.add(travel.get(0));
            }
            
            if (travel.get(1)!=null && firstNull) {
                query=query + "and num_vuelo=? ";
                queryList.add(travel.get(1));
            }else if(travel.get(1)!=null && firstNull==false){
                query=query + "num_vuelo=? ";
                firstNull=true;
                queryList.add(travel.get(1));
            }
            
            if (travel.get(2)!=null && firstNull) {
                query=query + "and companyia=? ";
                queryList.add(travel.get(2));
            }else if(travel.get(2)!=null && firstNull==false){
                query=query + "companyia=? ";
                queryList.add(travel.get(2));
                firstNull=true;
            }
            
            if (travel.get(3)!=null && firstNull) {
                query=query + "and origen=? ";
                queryList.add(travel.get(3));
            }else if(travel.get(3)!=null && firstNull==false){
                query=query + "origen=? ";
                firstNull=true;
                queryList.add(travel.get(3));
            }
            
            if (travel.get(4)!=null && firstNull) {
                query=query + "and hora_salida=? ";
                queryList.add(travel.get(4));
            }else if(travel.get(4)!=null && firstNull==false){
                query=query + "hora_salida=? ";
                queryList.add(travel.get(4));
                firstNull=true;
            }
            
            if (travel.get(5)!=null && firstNull) {
                query=query + "and destino=? ";
                queryList.add(travel.get(5));
            }else if(travel.get(5)!=null && firstNull==false){
                query=query + "destino=? ";
                firstNull=true;
                queryList.add(travel.get(5));
            }
            
            if (travel.get(6)!=null && firstNull) {
                query=query + "and hora_llegada>? ";
                queryList.add(travel.get(6));
            }else if(travel.get(6)!=null && firstNull==false){
                query=query + "and hora_llegada>? ";
                firstNull=true;
                queryList.add(travel.get(6));
            }      
            
            if (option == 0){
                pst = conn.prepareStatement(query);
                for(int i=0;i<queryList.size();i++){
                    if (first && i==0) pst.setInt(1,(Integer) travel.get(0));
                    else pst.setString(i+1,(String) queryList.get(i));
                }
            }
            else {
                pst = conn.prepareStatement("insert into vuelos (ID_VUELO,NUM_VUELO,COMPANYIA,ORIGEN,HORA_SALIDA,DESTINO,HORA_LLEGADA) "
                    + "                                         values(?,?,?,?,?,?,?)");
                pst.setInt(1,(Integer) travel.get(0));
            }
            List vuelos = new ArrayList();
            ResultSet Query = pst.executeQuery();
            vuelos.add(Query.getString("ID_VUELO"));
            vuelos.add(Query.getString("NUM_VUELO"));
            vuelos.add(Query.getString(3));
            vuelos.add(Query.getString(4));
            vuelos.add(Query.getString(5));
            vuelos.add(Query.getString(6));
            vuelos.add(Query.getString(7));
            request.setAttribute("searchqry", vuelos); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("buscarVuelo.jsp");
            dispatcher.forward( request, response );
            response.sendRedirect("/travel/buscarVuelo.jsp");
            
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}