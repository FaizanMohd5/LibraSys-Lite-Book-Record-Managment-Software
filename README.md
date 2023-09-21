# LibraSys Lite Book Management Software

LibraSys Lite is a simple and user-friendly book management software developed in Java. It allows users to perform basic operations on a book inventory, including creating, viewing, updating, and deleting book records. This application is designed for single-user scenarios and does not require complex user authentication.

## Features

- **Graphical User Interface (GUI):** LibraSys Lite provides an intuitive graphical user interface created using Java Swing and AWT libraries, making it easy to interact with the application.

- **MySQL Database:** The application utilizes a MySQL database to store and manage book records efficiently.

- **CRUD Operations:** Users can perform CRUD (Create, Read, Update, Delete) operations on the book records, allowing for easy management of their book inventory.

- **JDBC Integration:** JDBC (Java Database Connectivity) is used to establish a connection between the Java application and the MySQL database, enabling seamless data retrieval and manipulation.

## Prerequisites

Before using LibraSys Lite, ensure you have the following prerequisites installed and configured:

- **Java Development Kit (JDK):** You need to have JDK installed on your system to run the Java application.

- **MySQL Database Server:** Install and run a MySQL database server where the book records will be stored.

- **MySQL JDBC Driver:** Make sure you have the MySQL JDBC driver available in your classpath.

## Getting Started

1. Clone the LibraSys Lite repository from GitHub to your local machine.

2. Configure your MySQL database:
    - Create a database named `librasysdb`.
    - Modify the database connection details in the `mysql-abstract-details.properties` file. You need to specify the database URL, username, and password.

3. Compile and run the Java application:
   ```bash
   javac SoftwareFrame.java
   java SoftwareFrame
   ```

4. Use the application to manage your book inventory:
    - Enter book details (ID, title, price, author, publisher) and click the "Save" button to add a new book.
    - Select a row in the table to edit or delete a book record.
    - Click the "Update" button to update a book record.
    - Click the "Delete" button to remove a book record.

## Version Control

The project uses Git and is hosted on GitHub, allowing you to track changes and collaborate with others if needed.

## Acknowledgments

LibraSys Lite was developed with the help of resources like Stack Overflow, providing guidance and solutions to overcome challenges during development.

Enjoy using LibraSys Lite for efficient book management!

**Note:** The `mysql-abstract-details.properties` file containing MySQL connection details is not provided in the repository for security reasons. You should create this file locally with your own database connection information.