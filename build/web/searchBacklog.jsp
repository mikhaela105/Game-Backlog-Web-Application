<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Game"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Allows client to filter through the backlog, specifying details 
such as name, status and platform. Games can also be removed and edited
from this page.

@author Mikhaela Tajonera
-->
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Backlog</title>
    </head>
    <body>
         <div class="panel">

        <form name ="inputForm" action="./SearchBacklogServlet">
            <h1>Search Your Backlog</h1>

            Name: <input type="text" name="name" value=""/>
            <br><br>
            Status: <select name="status">
                <option>Any</option>
                <option>Not Started</option>
                <option>Started</option>
                <option>Completed</option>
            </select>
            <br><br>
            Platform: <select name="platformName">
                <option>Any</option>
                <option>PC</option>
                <option>Playstation 4</option>
                <option>Playstation 3</option>
                <option>Xbox One</option>
                <option>Xbox 360</option>
                <option>Nintendo DS</option>
                <option>Nintendo Switch</option>
            </select>
            <br>
            <br>
            <input type="submit" value="Search">
        </form>
        <br>
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
                        <td><input type="checkbox" name="gameID" value="${game.ID}"></td>
                    <form name="editGameForm" action="./UpdateGameServlet">
                        <td><input type="submit" name="editGame" value="Edit"></td>
                        <input type="hidden" name="editGameID" value="${game.ID}">
                    </form>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <input type="hidden" value="/searchBacklog.jsp" name="pageName">
            <input class="button" type="submit" value="Remove Selected Games">
        </form>
        <br>
        <form name="BackForm" action="./GameBacklogServlet">
            <br>
            <input class="button" type="submit" value="Back">
        </form>
         </div>

    </body>
</html>
