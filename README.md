# CST 8319 - Flower Order System
This repository hosts the code for the Flower Order System, designed as part of the coursework for CST 8319. The application is built using Java Servlets and JSP, with a MySQL database for backend storage.

## Directory Overview
This section provides an overview of each main directory in the repository, detailing their purpose and contents, to help you navigate the project effectively.

flower - 
The flower directory contains the core components of our Flower Order System project. It includes backend and database scripts integral to the application's functionality. This is the main part of our project.

FrontEnd (Not part of the project) - 
The FrontEnd directory contains various frontend assets and configurations that were added for experimental purposes and are not part of the official project. These files are for testing and development outside the main application scope.

.vscode - 
The .vscode directory includes configuration files for Visual Studio Code that are used to enhance the development environment but are not necessary for the project's functionality.

README.md - 
These documentation files provide detailed information about the project, including setup, installation, usage, and an overview of the project structure. README.md is the main file that you are currently reading, which includes comprehensive documentation of the project.
## Branching Model
master - The main branch representing the stable, production-ready code. This branch should always be in a working condition. All feature branches should branch off from the master. After a feature is completed and tested, it should be merged back into the master branch.
Feature Branches - Working branches that branch off from master. Each team member will create branches off the master, work on their feature, and merge the feature branch back to master. Before merging a feature branch into master, merge master into the feature branch first, test to exclude any merging/integration issues. If stable, merge the feature branch into the master branch.
## Feature Development Workflow
- Each collaborator works on a separate feature branch for their assigned task.
- Regularly pull changes from master to keep feature branches up to date.
- Once a feature is complete, the collaborator submits a pull request (PR) from their feature branch to master.
- PR should include code changes, a summary of the changes, and any related issues.
- Ensure that no one directly pushes changes to master.
## Code Review
- Before merging a feature branch into master, require at least one code review from another team member.
- Reviewers should ensure code quality, adherence to coding standards, and correctness.
- Address feedback and make necessary changes before merging.
## Prepare workspace
When you clone the project, run it on the server and make sure you can see login.jsp in the browser, i.e., the server is running and requests are forwarded to the controller. This is to ensure everybody is on the same page and local environments are set up properly.
## Environment Setup
- Java: JDK 17(Java SE 17)
- Tomcat Version: v9.0
- MySQL Connector: mysql-connection 8.0.13 jar
## Setting Up Your Local Environment
- Clone the Repository: Start by cloning the repository to your local machine.
- Configure Tomcat: Ensure Tomcat v9.0 is installed and configured to run the application.
- Database Setup: Install MySQL and set up the database using the scripts found in resources/sql.
- Run the Application: Deploy the application on Tomcat and navigate to http://localhost:8080/flower to view the login page.
- Verify Setup: Ensure that the login page is accessible and that the application connects to the database without issues.
## Project Folder Structure
Understanding the folder structure will help in navigating and utilizing the repository effectively:

src/main/java/com/flower
- controller: Contains all servlets that manage the flow of data between the server and the client interface. These servlets handle user interactions by responding to various HTTP requests.
- dao: Data Access Objects that provide abstract interfaces to the database, allowing for manipulation of the data source without exposing details.
- model: Defines data structures for the application. This folder contains classes that represent entities such as User, Product, Order, etc.
- util: Utilities like DBConnection.java which manages the connection to the database, ensuring all components communicate effectively.
- resources/sql: This directory contains SQL scripts for creating database schemas and inserting initial data to set up the database properly.
- webapp: Includes static resources (images, CSS, JS) and JSP files that create the client-side user interface.
- test/java/com/flower: Contains unit tests that verify the functionality and correctness of both business logic and UI behaviors.
