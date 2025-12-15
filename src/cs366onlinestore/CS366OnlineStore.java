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

        boolean running = true;

        System.out.println("Welcome to the Online Store Management System");
        while (running) {
            System.out.println("======================================================");
            System.out.println("What would you like to do?");
            System.out.println("======================================================");
            System.out.println("1) Work with products");
            System.out.println("2) Work with customer");
            System.out.println("3) Work with customer orders");
            System.out.println("0) Exit Application");
            System.out.print("\nEnter choice: ");

            int choice = getUserInput(scan);

            switch (choice) {
                case 1 -> { //runProductMenu
                }
                case 2 -> runCustomerMenu(scan);
                case 3 -> { //runOrderMenu
                }
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid selection. Please try again.");
            }
        }
        scan.close();
    }
    
    private static int getUserInput(Scanner scan) {
        try {
            String input = scan.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // Return -1 to trigger "Invalid selection" in switch cases
        }
    }
    
    private static void runCustomerMenu(Scanner s) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- CUSTOMER MANAGEMENT MENU ---");
            System.out.println("1) Add new customer");
            System.out.println("2) Edit existing customer");
            System.out.println("3) Remove existing customer");
            System.out.println("4) View all customers");
            System.out.println("0) Back to Main Menu");
            System.out.print("Select Action: ");

            int choice = getUserInput(s);

            switch (choice) {
                case 1 -> // TODO (Zach): Logic to instantiate Customer and save
                    AddCustomer(s);
                case 2 -> // TODO (Zach): Logic to find customer and update fields
                    EditExistingCustomer(s);
                case 3 -> // TODO (Zach): Logic to delete customer from data structure
                    RemoveCustomer(s);
                case 4 -> // TODO (Zach): Loop through customer list and print details
                    GetAllCustomers();
                case 0 -> inMenu = false;
                default -> System.out.println("Invalid option.");
            }
        }
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
        System.out.println("Enter State (i.e. ND, MN, WA): ");
        String state = s.nextLine();
        System.out.println("Enter Zip: ");
        String zip = s.nextLine();

        // creating customer object (placeholder id)
        Customer cust = new Customer(0, fname, lname, email, phone, street, city, state, zip);

        // insert known data into db
        custDbOp.insertCustomerInDb(cust);
    }

    public static void EditExistingCustomer(Scanner s) {
        // Get fname and lname of Customer to edit
        System.out.println("Enter Details of Customer to edit");
        System.out.println("Customers First Name:");
        String firstname = s.next();
        System.out.println("Customers Last Name: ");
        String lastname = s.next();
        System.out.println("Customers Email: ");
        String custEmail = s.next();

        s.nextLine(); // prevent skipping fname edit

        //Get Customer record from DB
        Customer cust = custDbOp.getCustomerRecord(firstname.strip(), lastname.strip(), custEmail.strip());
        if (cust == null) {
            return;
        }
        System.out.println("=====================================");
        System.out.println("SUCCESS GETTING CUSTOMER!");
        System.out.println("=====================================");

        // Edit Customers Details
        System.out.println("EDIT DETAILS");
        System.out.println("=====================================");
        System.out.println("Edit First Name? (Current = " + cust.getFirstName() + "): ");
        String fname = s.nextLine();
        if (fname.isEmpty()) {
            System.out.println("Skipping Firstname");
            fname = cust.getFirstName();
        }

        System.out.println("Edit Last Name? (Current = " + cust.getLastName() + "): ");
        String lname = s.nextLine();
        if (lname.isEmpty()) {
            System.out.println("Skipping Lastname");
            lname = cust.getLastName();
        }

        System.out.println("Edit Email? (Current = " + cust.getEmail() + "): ");
        String email = s.nextLine();
        if (email.isEmpty()) {
            System.out.println("Skipping Email");
            email = cust.getEmail();
        }

        System.out.println("Edit Phone? (Current = " + cust.getPhone() + "): ");
        String phone = s.nextLine();
        if (phone.isEmpty()) {
            System.out.println("Skipping Phone");
            phone = cust.getPhone();
        }

        System.out.println("Edit Street? (Current = " + cust.getStreet() + "): ");
        String street = s.nextLine();
        if (street.isEmpty()) {
            System.out.println("Skipping Street");
            street = cust.getStreet();
        }

        System.out.println("Edit City? (Current = " + cust.getCity() + "): ");
        String city = s.nextLine();
        if (city.isEmpty()) {
            System.out.println("Skipping City");
            city = cust.getCity();
        }

        System.out.println("Edit State?(i.e. ND, MN, WA) (Current = " + cust.getState() + "): ");
        String state = s.nextLine();
        if (state.isEmpty()) {
            System.out.println("Skipping State");
            state = cust.getState();
        }

        System.out.println("Edit ZIP? (Current = " + cust.getZip() + "): ");
        String zip = s.nextLine();
        if (zip.isEmpty()) {
            System.out.println("Skipping ZIP");
            zip = cust.getZip();
        }

        // make updated customer
        Customer updatedCust = new Customer(
                cust.getId(),
                fname,
                lname,
                email,
                phone,
                street,
                city,
                state,
                zip
        );

        //send it to the db
        custDbOp.editCustomerRecord(updatedCust);
    }

    public static void RemoveCustomer(Scanner s) {
        // Get fname, lname, email, phone_num of Customer to drop
        System.out.println("Enter Details of Customer to edit");
        System.out.println("Customers First Name:");
        String firstname = s.next();
        System.out.println("Customers Last Name: ");
        String lastname = s.next();
        System.out.println("Customers Email: ");
        String custEmail = s.next();
        System.out.println("Customers Phone Number: ");
        String phone = s.next();

        custDbOp.removeCustomerRecord(firstname, lastname, custEmail, phone);
    }

    public static void GetAllCustomers() {
        ResultSet rs = custDbOp.getAllCustomers();
        if (rs == null) {
            System.out.println("No Customers Found :(");
            return;
        }
        try {
            System.out.println("Customer Info");
            while (rs.next()) {
                System.out.println("=====================================");
                System.out.println("Name: " + rs.getString("firstname") + " " + rs.getString("lastname"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone Number: " + rs.getString("phone"));
                System.out.println("Address: " + rs.getString("street") + " " + 
                                                 rs.getString("city") + ", " + 
                                                 rs.getString("state") + ", "+ 
                                                 rs.getString("zip"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all customer records: " + e.getLocalizedMessage());
        }

    }
}
