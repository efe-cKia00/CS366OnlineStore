/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs366onlinestore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @author Zachary Adolphsen
 * @author Efe Awo-Osagie
 * @author Kal Larson
 */
public class CS366OnlineStore {

    private static dbManager dbManager = new dbManager();
    private static Connection dbcon;
    private static CustomerDbOps custDbOp;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            dbcon = dbManager.createConnection();
            if (dbcon == null) {
                System.out.println("CRITICAL ERROR: dbManager returned a null connection. Check your DB path!");
                return; // Stop the program here!
            }
            custDbOp = new CustomerDbOps(dbcon);
            System.out.println("Success");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        Scanner scan = new Scanner(System.in);

        AddCustomer(scan);
    }

    public static void AddCustomer(Scanner s) {
        System.out.println("Enter First Name:");
        String fname = s.nextLine();
        System.out.println("Enter Last Name: ");
        String lname = s.nextLine();
        System.out.println("Enter Email: ");
        String email = s.nextLine();
        System.out.println("Enter Phone Number: ");
        String phone = s.nextLine();
        System.out.println("Enter Street: ");
        String street = s.nextLine();
        System.out.println("Enter City: ");
        String city = s.nextLine();
        System.out.println("Enter State: ");
        String state = s.nextLine();
        System.out.println("Enter Zip: ");
        String zip = s.nextLine();

        // creating customer object
        Customer cust = new Customer(fname, lname, email, phone, street, city, state, zip);

        // insert known data into db
        custDbOp.insertCustomerInDb(cust);
    }

}
