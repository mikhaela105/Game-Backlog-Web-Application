<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--
JavaServer Page that shows the user all the games that they
removed from the backlog.

@author Mikhaela Tajonera
-->
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove Games</title>
    </head>
    <body>
        <div class="panel">
            The following games were removed:
            <table id ="removeTable" align="center">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Platform</th>
                    <th>Date Added</th>
                </tr>

                <jsp:useBean id="removeList" class="models.GameList" scope="request"/>
                <c:forEach items = "${removeList.gameList}" var = "game" varStatus="row">
                    <tr>
                        <td>${game.ID}</td>
                        <td>${game.name}</td>
                        <td>${game.status}</td>
                        <td>${game.platformName}</td>
                        <td>${game.dateAdded}</td>
                    </tr>
                </c:forEach>
            </table>

            <form action="./GameBacklogServlet" method="post">
                <br><input class="button" type="submit" value="View Backlog">
            </form>
        </div>
    </body>
</html>
