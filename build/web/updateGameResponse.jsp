<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Shows results of updating game, either successful
or not

@author Mikhaela Tajonera
-->
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game Updated</title>
    </head>
    <body>
        <div class="panel">
            <% out.println((String) request.getAttribute("updateResponse"));%>
            <form name="BackForm" action="./GameBacklogServlet">
                <input class="button" type="submit" value="Back">
            </form>
        </div>
    </body>
</html>
