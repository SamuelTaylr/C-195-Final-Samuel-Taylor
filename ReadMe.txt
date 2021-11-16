Title: C-195 Scheduling Application

Purpose: This application is designed to allow creating, updating and deleting appointments
and customers for a business.  It also allows creating, updating, and deleting of customers.  There
are various ways for the user to view and interact with the data, including filtering appointments
by contact, by type, and by week or month.  There are various alerts and validation that prevents 
the user from inputting incompatible information.

Author: Samuel Taylor
stay608@wgu.edu

Version: 1.0

Date: 9/30/2021

IDE: IntelliJ Community 2021.2.1.
JDK: 11.0.11
JavaFX: SDK-16

Directions: To run the program press the play button in intelliJ, then enter either username/password 
present in the database (test, test) or (admin, admin).  Navigating the menus is user friendly and 
straightforward and requires no advanced knowledge to operate.

Additional Report: I chose to make my additional report include several tabs that may be of intererst
to the user.  Including contacts, filtering appointments by contact and type, as well as total counts of 
appointments by type and month.

Lambda Expressions: One of my lambda expressions can be found in the reportcontroller.  It functions like a button handler
and executes code to filter a tableview list for each contact.  My second lambda expression can be found in the
appointmentcontroller.  It functions as a button handler for deleting appointments.

MySQL connector driver version: mysql-connector-java:8.0.22