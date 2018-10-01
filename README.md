# Game-Backlog-Web-Application
Developed for the paper Distributed and Mobile Systems at AUT. It is a multi-tier system which uses Glassfish and Apache Derby. Makes user of JavaServer Pages, servlets, beans, simple HTML and CSS.

The project developed is a system that lets a client keep track of their video game progresses. In this
application, the client can add and remove video games to and from their backlog. Each game stores its
name, status (Not started, started, completed), platform played on (PC, Playstation, etc.) as well as the
date the game was added.

Features:
- Adding games
- Removing games
- Filtering backlog
- Editing games

Setup
=======
This application was developed using NetBeans IDE 8.2 and GlassFish Server 4.1.1.
JDK: 1.8
Java EE Version: Java EE 7 Web
Browser: Google Chrome

To run the project please ensure you can access the Apache Derby Database Server.

1. Under the models package, run the file CreateDatabase.java to create the tables required as
well as to populate it with sample data.

2. Compile and run the application by right-clicking on the project name DMS_A2 and clicking
deploy. Once the console has said that the project has been deployed, ensure that you are
connected to the database server before clicking the green button on top of NetBeans to start
the application.

Note: If you cannot connect to the database or require the login, use:
Username: xht5252
Password: password

Refere to the [User Manual](https://github.com/mikhaela105/Game-Backlog-Web-Application/blob/master/User%20Manual.pdf) to see how to interact with the application.
