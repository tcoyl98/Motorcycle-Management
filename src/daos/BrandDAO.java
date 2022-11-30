/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import bd.MyConnection;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dtos.BrandDTO;
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
public class BrandDAO {
    
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;
    
    ArrayList<BrandDTO> brandList;
    
    public ArrayList<BrandDTO> getBrandList() {
        return brandList;
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
    
    public void fetchBrand() throws SQLException, SQLServerException, NamingException{
        
        String brandID, brandName, country, description;
        
        try {
            conn = MyConnection.makeConnection();
            String sql = "SELECT brandID, brandName, country, description FROM TblBrands";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while(rs.next()) {
                brandID = rs.getString(1);
                brandName = rs.getString(2);
                country = rs.getString(3);
                description = rs.getString(4);
                BrandDTO brand = new BrandDTO(brandID, brandName, country, description);
                if(getBrandList()== null)
                    this.brandList = new ArrayList<>();
                this.brandList.add(brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    
    public boolean addNewBrand(String brandID, String brandName, String country, String description) 
                                                                            throws SQLException, SQLServerException, NamingException {
        boolean checkAdd = false; 
        try {
            conn = MyConnection.makeConnection();
            if(conn != null) {
                String sql = "INSERT INTO TblBrands (brandID, brandName, country, description) VALUES(?, ?, ?, ?)";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, brandID);
                preStm.setString(2, brandName);
                preStm.setString(3, country);
                preStm.setString(4, description);
                checkAdd = preStm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checkAdd;
    }
    
    public boolean updateBrand(String brandID, String brandName, String country, String description) 
                                                                            throws SQLException, SQLServerException, NamingException {
        boolean checkUpdate = false;
        try {
            conn = MyConnection.makeConnection();
            if(conn != null) {
                String sql = "UPDATE TblBrands SET brandName=?, country=?, description=? WHERE brandID=?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(4, brandID);
                preStm.setString(1, brandName);
                preStm.setString(2, country);
                preStm.setString(3, description);
                checkUpdate = preStm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }
    
    public boolean deleteBrand(String brandID) throws SQLException, SQLServerException, NamingException {
        boolean checkDelete = false;
        try {
            conn = MyConnection.makeConnection();
            if(conn != null) {
                String sql = "DELETE FROM TblBrands WHERE brandID=?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, brandID);
                checkDelete = preStm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checkDelete;
    }
    
    public boolean checkDuplicate(String brandID) {
        boolean checkDup = false;
        try {
            conn = MyConnection.makeConnection();
            String sql = "SELECT FROM TblBrands WHERE brandID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, brandID);
            rs = preStm.executeQuery();
            if(rs.next())
                checkDup = true;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return checkDup;
    }
    
    public boolean checkP(String p) {
        boolean checkP =false;
        try {
            conn = MyConnection.makeConnection();
            String sql = "SELECT brandName, model FROM TblBrands, TblBikes \n" +
                            "WHERE TblBrands.brandID = ? AND TblBikes.brandIDFK = ? \n" +
                            "ORDER BY brandName, model \n";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, p);
            preStm.setString(2, p);
            rs = preStm.executeQuery();
            if(rs.next()) {
                checkP = true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return checkP;
    }
}
