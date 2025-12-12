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
 * @author zlado
 */
public class CustomerDbOps {
    private Connection dbcon;
    
    public CustomerDbOps(Connection conn){
        this.dbcon = conn;
    }
    
    public void insertCustomerInDb(Customer cust){
        try {
            String addCustomer = "INSERT INTO customer (firstname, lastname, email, phone, street, city, state, zip) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = dbcon.prepareStatement(addCustomer);
            pstmt.setString(1, cust.getFirstName());
            pstmt.setString(2, cust.getLastName());
            pstmt.setString(3, cust.getEmail());
            pstmt.setString(4, cust.getPhone());
            pstmt.setString(5, cust.getStreet());
            pstmt.setString(6, cust.getCity());
            pstmt.setString(7, cust.getState());
            pstmt.setString(8, cust.getZip());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting customer: " + e.getLocalizedMessage());
        }
    }
    
}
