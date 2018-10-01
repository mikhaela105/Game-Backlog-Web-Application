<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--
Redirected from clicking “Add” on addGame.html. 
Shows user whether game was successfully added or not and if not,
what the error was.

@author Mikhaela Tajonera
-->

<jsp:useBean id="newGame" scope="request" class="models.NewGame" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body>
        <div class="panel">
            <h1>Game Information:</h1>
            Name: <jsp:getProperty name="newGame" property="name"/>
            <br>
            Status: <jsp:getProperty name="newGame" property="status"/>
            <br>
            Platform: ${platformName}
            <br>
            <br>
            <c:choose>
                <c:when test = "${newGame.isAdded == true}">
                    You have successfully added a new game into your backlog.
                </c:when>
                <c:otherwise>
                    You were unsuccessful in adding a new game:
                    <c:choose>
                        <c:when test = "${newGame.errorNumber == 1}">
                            <br>
                            The name you entered is not within the required length.
                            <br>
                            (Length must be between 3-30 letters).
                        </c:when>

                        <c:when test = "${newGame.errorNumber == 2}">
                            <br>
                            The game with the same name already exists in the database.
                        </c:when>
                    </c:choose>
                    <br>
                    <a href = "addGame.html"> <br><input class="button" type="submit" value="Try Again" name="retryAddGame" />
                    </c:otherwise>
                </c:choose>
                <form action="./GameBacklogServlet" method="post">
                    <br><input class="button" type="submit" value="View Backlog">
                </form>
        </div>
    </body>
</html>






<%


%>

<!--</head>
<body>
    <h1>Game Information:</h1>
    Game ID: 
    <div>Click below to continue.</div>
    <form action="./GameBacklogServlet" method="post">
        <input type="submit" value="View Backlog">
    </form>
</body>
</html>-->


