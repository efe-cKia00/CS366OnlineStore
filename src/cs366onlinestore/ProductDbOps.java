/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs366onlinestore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Efe
 */
public class ProductDbOps {

    private Connection dbcon;

    public ProductDbOps(Connection conn) {
        this.dbcon = conn;
    }

    public void insertProductInDb(Product prod) {
        String sql = "INSERT INTO products (provider_id, category, product_name, description, quantity, unit_price) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = dbcon.prepareStatement(sql)) {
            // Set parameters for the prepared statement
            pstmt.setInt(1, prod.getProviderId());
            pstmt.setInt(2, prod.getCategory());
            pstmt.setString(3, prod.getProductName());
            pstmt.setString(4, prod.getDescription());
            pstmt.setInt(5, prod.getQuantity());
            pstmt.setFloat(6, prod.getUnitPrice());

            // Execute the insert
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product successfully added to database!");
            } else {
                System.out.println("Failed to add product to database.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting product: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public Product getProductRecord(String productName, int productId) {
        try {
            String getProduct = "SELECT * FROM product WHERE product_name = ? AND category_id = ?";

            PreparedStatement pstmt = dbcon.prepareStatement(getProduct);
            // Set parameters for the prepared statement
            pstmt.setString(1, productName);
            pstmt.setInt(2, productId);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("product_id");
                int providerid = rs.getInt("provider_id");
                int categoryid = rs.getInt("category_id");
                String productname = rs.getString("product_name");
                String description = rs.getString("description");

            }
        } catch (SQLException e) {

        }
    }

    public void editProductRecord(Product prod) {
        try {
            String updateCustomer = "UPDATE product "
                    + "SET firstname = ?, lastname = ?, email = ?, "
                    + "phone = ?, street = ?, city = ?, state = ?, zip = ? "
                    + "WHERE customer_id = ?";
            PreparedStatement pstmt = dbcon.prepareStatement(updateCustomer);
            pstmt.setInt(1, prod.getProviderId());
            pstmt.setString(2, prod.getLastName());
            pstmt.setString(3, prod.getEmail());
            pstmt.setString(4, prod.getPhone());
            pstmt.setString(5, prod.getStreet());
            pstmt.setString(6, prod.getCity());
            pstmt.setString(7, prod.getState());
            pstmt.setString(8, cust.getZip());
            pstmt.setInt(9, cust.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Updating Customer: " + e.getLocalizedMessage());
        }
    }

    public ResultSet getProductPriceChangeHistory(String prodName, String prodCategory) {
        try {
            String getProductPriceHistory = "SELECT pc.old_price, pc.new_price, pc.reason_changed, pc.changed_at "
                    + "FROM PriceChange pc "
                    + "JOIN "
                    + "Product p ON pc.product_id = p.product_id "
                    + "WHERE "
                    + "p.name = ? AND p.category_id = ?";

            PreparedStatement pstmt = dbcon.prepareStatement(getProductPriceHistory);
            pstmt.setString(1, prodName);
            pstmt.setString(2, prodCategory);

            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return null;
        }
    }

    public ResultSet getMostExpensiveProducts(int n) {
        try {
            String getMostExpensiveProducts = "SELECT p.name AS product_name, c.category_name, p.unit_price "
                    + "FROM Product p "
                    + "JOIN "
                    + "Category c ON p.category_id = c.category_id "
                    + "ORDER BY "
                    + "p.unit_price DESC "
                    + "LIMIT ?";

            PreparedStatement pstmt = dbcon.prepareStatement(getMostExpensiveProducts);
            pstmt.setInt(1, n);

            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return null;
        }
    }
}
