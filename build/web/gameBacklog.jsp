<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Game"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--
Displays all the games recorded in the backlog.
Has buttons to remove and add games, refresh
the backlog, and to redirect to the search feature

@author Mikhaela Tajonera
-->
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game Backlog</title>
    </head>
    <body>
        <div class="panel" align="center">
            <h1>Game Backlog</h1>
            <c:if test = "${removeCheck == false}">
                Please select a game to remove!
                <br>
            </c:if>

            <form action="./RemoveGameServlet" method="post">

                <table id ="gameTable" align="center">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Status</th>
                        <th>Platform</th>
                        <th>Date Added</th>
                    </tr>

                    <jsp:useBean id="games" class="models.GameList" scope="session"/>
                    <c:forEach items = "${games.gameList}" var = "game" varStatus="row">
                        <tr>
                            <td>${game.ID}</td>
                            <td>${game.name}</td>
                            <td>${game.status}</td>
                            <td>${game.platformName}</td>
                            <td>${game.dateAdded}</td>
                            <td><input type="checkbox" name="gameID" value="${game.ID}"/></td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <input type="hidden" value="/gameBacklog.jsp" name="pageName">
                <input class ="button" type="submit" value="Remove Selected Games">
                <br>
            </form>
            <a href = "addGame.html"> <br><input class ="button" type="submit" value="Add Game" name="addGame">
                <br>
                <form action="./GameBacklogServlet" method="post">
                    <br>
                    <input class ="button" type="submit" value="Refresh Backlog">
                </form>
            </a>

            <form action="./SearchBacklogServlet" method="post">
                <br>
                <input class ="button" type="submit" value="Search">
                <input type="hidden" name="name" value="" />
                <input type="hidden" name="status" value="Any"/>
                <input type="hidden" name="platformName" value="Any"/>
            </form>
        </div>
    </body>
</html>
