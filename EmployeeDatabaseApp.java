import java.sql.*;
import java.util.Scanner;

public class EmployeeDatabaseApp {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root"; // change to your MySQL username
    private static final String PASSWORD = "Mahesh@17"; // change to your MySQL password

    private Connection conn;
    private Scanner scanner;

    public EmployeeDatabaseApp() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to Database!");
            scanner = new Scanner(System.in);
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }

    public void addEmployee() {
        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Department: ");
            String dept = scanner.nextLine();
            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.executeUpdate();
            System.out.println("✅ Employee added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void viewEmployees() {
        try {
            String sql = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n--- Employee List ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Dept: %s | Salary: %.2f%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("department"), rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void updateEmployee() {
        try {
            System.out.print("Enter Employee ID to Update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Department: ");
            String dept = scanner.nextLine();
            System.out.print("Enter New Salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee updated successfully!");
            } else {
                System.out.println("❌ Employee not found!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void deleteEmployee() {
        try {
            System.out.print("Enter Employee ID to Delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            String sql = "DELETE FROM employees WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee deleted successfully!");
            } else {
                System.out.println("❌ Employee not found!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- Employee Database Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> {
                    System.out.println("👋 Exiting...");
                    try { conn.close(); } catch (SQLException ignored) {}
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        EmployeeDatabaseApp app = new EmployeeDatabaseApp();
        app.menu();
    }
}