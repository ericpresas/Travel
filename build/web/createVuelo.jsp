<!DOCTYPE html>
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
                        <li>ID Vuelo: <input type="text" value="1234..." id="idvuelo"/></li><br>
                        <li>Numero Vuelo: <input type="text" value="1234..." id="numvuelo"/></li><br>
                        <li>Companyia: <input type="text" value="Flight company..." id="comp"/></li><br>
                        <li>Origen: <input type="text" value="Origen..." id="origen"/></li><br>
                        <li>Destino: <input type="text" value="Destino..." id="dest"/></li><br>
                        <li>Hora Salida: <input type="text" value="HH:MM" id="salida"/></li><br>
                        <li>Hora Llegada: <input type="text" value="HH:MM..." id="llegada"/></li><br><br>
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
            </tr>
            <tr>
              <td class="tg-yw4l">1</td>
              <td class="tg-yw4l">Swimming</td>
              <td class="tg-lqy6">1:30</td>
              <td class="tg-lqy6">2:05</td>
              <td class="tg-lqy6">1:15</td>
              <td class="tg-lqy6">1:15</td>
              <td class="tg-lqy6">1:41</td>
            </tr>
        </table>
        </div>
        
    </body>
</html>