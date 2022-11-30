/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import bd.MyConnection;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;


/**
 *
 * @author Loi Lam
 */
public class UserDAO {
    
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;
    
    ArrayList<UserDTO> userList;
    
    public ArrayList<UserDTO> getUserList() {
        return userList;
    }
    
    public void closeConnection() {
        try {
            if(rs != null)
                rs.close();
            else if(preStm != null)
                preStm.close();
            else if(conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean login(String userID, String password) throws SQLException, SQLServerException, NamingException{
        
        boolean checkLogin = false;
        String fullName;
        
        try {
            conn = MyConnection.makeConnection();
            String sql = "SELECT fullName, status FROM TblUsers WHERE userID=? AND  password=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if(rs.next()) {
                checkLogin = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checkLogin;
    }
    
    public boolean checkAllow(String userID, String password) throws SQLException, SQLServerException, NamingException{
        
        boolean checkLogin = false;
        String fullName;
        
        try {
            conn = MyConnection.makeConnection();
            String sql = "SELECT fullName, status FROM TblUsers WHERE userID=? AND  password=? AND status = 1";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if(rs.next()) {
                checkLogin = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checkLogin;
    }
    
}
