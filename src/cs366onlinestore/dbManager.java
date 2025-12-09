/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs366onlinestore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbManager {
    
    private String dburl = "jdbc:postgresql://localhost:5432/online_store";
    private String username = "postgres";
    private String password = "Adolphboys2";
    
    public Connection createConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(dburl, username, password);
        return conn;
    }
    
    
}
