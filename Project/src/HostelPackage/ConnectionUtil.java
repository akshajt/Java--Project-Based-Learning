package HostelPackage;

import java.sql.*;

public class ConnectionUtil {
    public static void main(String[] args) {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
            System.out.println("Successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
