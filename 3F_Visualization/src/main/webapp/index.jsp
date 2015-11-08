<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    </head>
    <body bgcolor="xb20000">
        <h1 style="color: white">Sample using iframe.</h1>
        <div id="myDiv"></div>
        <iframe id="terrainFrame" src="http://localhost:8080/3F_Visualization/terrain.html" width="1200px" height="800px" scrolling="no" frameborder="0"></iframe>
        <!--<script>
            var myVar = setInterval(function() {
                reloadIFrame()
            }, 1000);

            function reloadIFrame() {
                document.getElementById("terrainFrame").contentWindow.location.reload(true);
            }
        </script>-->
    </body>
</html>
