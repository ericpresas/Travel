<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

        <meta charset="utf-8" />
        <meta name="author" content="Script Tutorials" />
        <title>Travel</title>

        <!-- add styles -->
        <link href="css/main.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            function showhide(id) {
               var e = document.getElementById(id);
               e.style.display = (e.style.display == 'block') ? 'none' : 'block';
            }
        </script>
    </head>
    <body>


        <%
        Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/travel", "eric", "eric");
            PreparedStatement pst;
            String query="select companyia, origen, destino from vuelos";                
            pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
        List comp = new ArrayList();
        List orig = new ArrayList();
        List dest = new ArrayList();

        while ( rs.next() ) {
            if (rs.getString("companyia")!=null) comp.add(rs.getString("companyia"));
            if (rs.getString("origen")!=null) orig.add(rs.getString("origen"));
            if (rs.getString("destino")!=null) dest.add(rs.getString("destino"));            
        }
        System.out.println(comp);
        request.setAttribute("comp", comp);
        request.setAttribute("orig", orig);
        request.setAttribute("dest", dest);
        conn.close();
        
        %>
        <div class="container">
            <ul id="nav">
                <li><a href="#">Home</a></li>
                <li><a href="#s1">Alta Vuelo</a>
                    <span id="s1"></span>
                    <ul class="subs">
                        <li><a href="javascript:showhide('crearvuelo')">Crear Vuelo</a></li>
                    <div id="crearvuelo" style="display: none;">
                        <form action="./createVuelo" method="post">
                            <li>ID Vuelo: <input type="text" value="1234..." name="idvuelo"/></li><br>
                            <li>Numero Vuelo: <input type="text" value="1234..." name="numvuelo"/></li><br>
                            <li>Companyia: <input type="text" value="Flight company..." name="comp"/></li><br>
                            <li>Origen: <input type="text" value="Origen..." name="origen"/></li><br>
                            <li>Destino: <input type="text" value="Destino..." name="dest"/></li><br>
                            <li>Hora Salida: <input type="text" value="HH:MM" name="salida"/></li><br>
                            <li>Hora Llegada: <input type="text" value="HH:MM..." name="llegada"/></li><br><br>
                            <input type="submit" value="Crear Vuelo"/>
                        </form>
                    </div>        
                    </ul>
                    
                </li>
                <li><a href="#s2">Buscar Vuelo</a>
                    <span id="s2"></span>
                    <ul class="subs">
                        <li><a href="javascript:showhide('buscarvuelosimple')">Menu de busqueda</a>
                            <ul>
                                <div id="buscarvuelosimple" style="display: none;">
                                <form action="./buscarVuelo" method="post" id="bvuelo">
                                    <li>Companyia: 
                                         <select name="companyia" form="bvuelo">
                                            <c:forEach items="${comp}" var="company" >
                                                <option value="${company.toString()}">
                                                    <c:out value="${company.toString()}" />
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </li><br>
                                    <li>Origen: 
                                         <select name="origen" form="bvuelo">
                                            <c:forEach items="${orig}" var="origen" >
                                                <option value="${origen.toString()}">
                                                    <c:out value="${origen.toString()}" />
                                                </option>
                                            </c:forEach>
                                        </select>
                                        </li><br>
                                    <li>Destino: 
                                         <select name="destino" form="bvuelo">
                                            <c:forEach items="${dest}" var="destino" >
                                                <option value="${destino.toString()}">
                                                    <c:out value="${destino.toString()}" />
                                                </option>
                                            </c:forEach>
                                        </select>                                  
                                    </li><br>
                                    <input type="submit" value="Buscar Vuelo"/>
                                </form>
                                    

                                </div>
                            </ul>
                        </li>
                        
                    </ul>
                </li>
                <li class="active" ><a href="index.html">Log out</a></li>
            </ul>
        </div>

        <div class="container" >
            <table class="tg">
            <tr>
              <th class="tg-3cas" colspan="7">Vuelo Creado<br></th>
            </tr>
            <tr>
              <td class="tg-v4ss">ID Vuelo<br></td>
              <td class="tg-v4ss">Numero Vuelo<br></td>
              <td class="tg-v4ss">Company</td>
              <td class="tg-v4ss">Origen</td>
              <td class="tg-v4ss">Salida</td>
              <td class="tg-v4ss">Destino</td>
              <td class="tg-v4ss">Llegada</td>
            <tr>
              <c:forEach items="${travel}" var="element">
                <td class="tg-yw4l"><c:out value="${element.toString()}" /></td>
              </c:forEach>
            </tr>
        </table>
        </div>
        
    </body>
</html>