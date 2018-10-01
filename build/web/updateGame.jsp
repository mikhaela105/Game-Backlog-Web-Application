<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Redirected here when client wishes to edit a game. 
They are shown current game information as well as fields 
to change the game’s information. Buttons for going back and applying
changes are also available

@author Mikhaela Tajonera
-->
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Game</title>
    </head>
    <body>
        <div class="panel">
            <form name ="inputForm" action="./ApplyGameUpdateServlet">
                <h1>Game Information:</h1>
                Name: ${game.name}
                <br>
                Status: ${game.status}
                <br>
                Platform: ${game.platformName}
                <br><br>
                <input type="hidden" name="oldGameID" value="${game.ID}"/>
                <h2>Edit Information:</h2>
                Name: <input type="text" name="name" value="${game.name}" required=""/>
                <br><br>
                Status: <select name="status">
                    <option>Not Started</option>
                    <option>Started</option>
                    <option>Completed</option>
                </select>
                <br><br>
                Platform: <select name="platformName">
                    <option>PC</option>
                    <option>Playstation 4</option>
                    <option>Playstation 3</option>
                    <option>Xbox One</option>
                    <option>Xbox 360</option>
                    <option>Nintendo DS</option>
                    <option>Nintendo Switch</option>
                </select>
                <br><br>
                <input class="button" type="submit" value="Apply Changes">
            </form>
            <form name="BackForm" action="./GameBacklogServlet">
                <br><input class="button" type="submit" value="Back">
            </form>
        </div>
    </body>
</html>
