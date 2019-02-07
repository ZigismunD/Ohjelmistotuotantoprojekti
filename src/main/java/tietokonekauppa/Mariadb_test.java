/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietokonekauppa;
//STEP 1. Import required packages

import java.sql.*;

public class Mariadb_test {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:4444/Tietokonekauppa";
    
    //  Database credentials
    static final String USER = "Kouhia";
    static final String PASS = "84EltB";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            
            //STEP 4: Test insert
            System.out.println("Inserting a row to OSA...");
            // create the mysql insert preparedstatement
            
            //Nimi
            String name = "c";
            //Hinta
            double price = 10;
            //Määrä
            int quantity = 3;
            //Luo SQL parametreista
            String sSQL = "INSERT INTO OSA (nimi, hinta, maara) VALUES ";
            sSQL = sSQL + "('" + name + "', " + price + ", " + quantity + ")";
            Statement st = conn.createStatement();
            //Execute query
            st.executeUpdate(sSQL);
            System.out.println("Inserted a row succesfully...");
            
            //STEP 5: Select * from OSA
            sSQL = "SELECT * FROM OSA";
            // create the java statement
            st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(sSQL);
            // iterate through the java resultset
            while (rs.next()) {
                int id = rs.getInt("id");
                name = rs.getString("nimi");
                price = rs.getDouble("hinta");
                quantity = rs.getInt("maara");
                // print the results
                System.out.format("%s, %s, %s, %s\n", id, name, price, quantity);
            }
            
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample
