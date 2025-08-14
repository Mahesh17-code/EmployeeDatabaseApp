# Java JDBC – Employee Database App

## 📌 Overview
This is a **Java Console Application** that connects to a MySQL database using **JDBC** to perform basic **CRUD operations** on an Employee database.  
Users can **Add, View, Update, and Delete** employee records through a menu-driven interface.

---

## 🛠️ Features
- **Add Employee** – Insert a new employee record into the database.
- **View Employees** – Display all employees from the database.
- **Update Employee** – Modify employee details based on ID.
- **Delete Employee** – Remove an employee record by ID.
- **Menu Driven** – Easy-to-use console interface.

---

## 📂 Technologies Used
- **Java (JDK 8+)**
- **MySQL** / (PostgreSQL possible with minor changes)
- **JDBC API**
- **MySQL Connector/J (JDBC Driver)**

---

## ⚙️ Setup & Installation

###
1️⃣ Database Setup
```sql
CREATE DATABASE employee_db;
USE employee_db;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    salary DOUBLE NOT NULL
);

2️⃣ Download MySQL JDBC Driver

Download from: https://dev.mysql.com/downloads/connector/j/

Add the .jar file to your project’s classpath.


3️⃣ Configure Database Credentials

In EmployeeDatabaseApp.java:

private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
private static final String USER = "root";      // change to your MySQL username
private static final String PASSWORD = "root";  // change to your MySQL password

4️⃣ Compile & Run

javac EmployeeDatabaseApp.java
java EmployeeDatabaseApp


---

📸 Sample Output

✅ Connected to Database!

--- Employee Database Menu ---
1. Add Employee
2. View Employees
3. Update Employee
4. Delete Employee
5. Exit
Choose option: 1
Enter Name: John
Enter Department: IT
Enter Salary: 55000
✅ Employee added successfully!

