<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="container">
            <ul id="nav">
                <li><a href="menu.html">Home</a></li>
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
                        <li><a href="#">Buscar por ID</a>
                            <ul>
                                <li><input type="text" value="id..." id="buscar"/></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="active" ><a href="index.html">Log out</a></li>
            </ul>
            
        </div>
        <div style="align-self: center;">
            <table class="tg" align="center">
            <tr>
              <th class="tg-3cas" colspan="7">Busqueda<br></th>
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
              <c:forEach items="${searchqry}" var="search">
                <td class="tg-yw4l"><c:out value="${search.toString()}" /></td>
              </c:forEach>
            </tr>
        </table>
        </div>       
    </body>
</html>