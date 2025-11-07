package b;
import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {

    // Database credentials
    static final String URL = "jdbc:mysql://localhost:3306/storedb"; // Replace with your DB name
    static final String USER = "root";  // Replace with your MySQL username
    static final String PASS = "";      // Replace with your MySQL password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;

        try {
            // Step 1: Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Connect to Database
            conn = DriverManager.getConnection(URL, USER, PASS);
            conn.setAutoCommit(false); // Enable transaction control
            System.out.println("✅ Connected to database successfully!\n");

            int choice;
            do {
                System.out.println("\n===== PRODUCT MANAGEMENT MENU =====");
                System.out.println("1. Insert Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        insertProduct(conn, sc);
                        break;
                    case 2:
                        viewProducts(conn);
                        break;
                    case 3:
                        updateProduct(conn, sc);
                        break;
                    case 4:
                        deleteProduct(conn, sc);
                        break;
                    case 5:
                        System.out.println("Exiting... Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 5);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                sc.close();
            } catch (SQLException e) {
                System.out.println("❌ Error closing connection: " + e.getMessage());
            }
        }
    }

    // Function to insert a product
    static void insertProduct(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Product ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, qty);

            int rows = ps.executeUpdate();
            conn.commit();
            System.out.println(rows + " product inserted successfully!");
        } catch (Exception e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            System.out.println("❌ Error inserting product: " + e.getMessage());
        }
    }

    // Function to view all products
    static void viewProducts(Connection conn) {
        try {
            String sql = "SELECT * FROM Product";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nProduct Records:");
            System.out.println("--------------------------------------------");
            System.out.printf("%-10s %-20s %-10s %-10s%n", "ID", "Name", "Price", "Quantity");
            System.out.println("--------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10d %-20s %-10.2f %-10d%n",
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"));
            }
        } catch (Exception e) {
            System.out.println("❌ Error displaying products: " + e.getMessage());
        }
    }

    // Function to update product details
    static void updateProduct(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Product ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new Product Name: ");
            String name = sc.nextLine();
            System.out.print("Enter new Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter new Quantity: ");
            int qty = sc.nextInt();

            String sql = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("✅ Product updated successfully!");
            } else {
                System.out.println("⚠️ No product found with that ID.");
            }
        } catch (Exception e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            System.out.println("❌ Error updating product: " + e.getMessage());
        }
    }

    // Function to delete a product
    static void deleteProduct(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Product ID to delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM Product WHERE ProductID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("✅ Product deleted successfully!");
            } else {
                System.out.println("⚠️ No product found with that ID.");
            }
        } catch (Exception e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            System.out.println("❌ Error deleting product: " + e.getMessage());
        }
    }
}
