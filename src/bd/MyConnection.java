/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Loi Lam
 */
public class MyConnection {
    
    public static Connection makeConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MotocycleManagement";
            String user = "sa";
            String pw = "";
            Connection conn = DriverManager.getConnection(url, user, pw);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
