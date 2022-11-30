/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import bd.MyConnection;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dtos.MotoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 *
 * @author Loi Lam
 */
public class MotoDAO {
    
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;
    
    ArrayList<MotoDTO> motoList;
    
    public ArrayList<MotoDTO> getMotoList() {
        return motoList;
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
    
    public void fetchMoto() throws SQLException, SQLServerException, NamingException{
        String motocycleID, model, condition, warranty, brandIDFK;
        Date year;
        int quantity;
        float price;
        try {
            conn = MyConnection.makeConnection();
            String sql = "SELECT motocycelID, model, year, condition, price, quantity, warranty, brandIDFK  FROM TblBikes";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while(rs.next()) {
                motocycleID = rs.getString(1);
                model = rs.getString(2);
                year = rs.getDate(3);
                condition = rs.getString(4);
                price = rs.getFloat(5);
                quantity = rs.getInt(6);
                warranty = rs.getString(7);
                brandIDFK = rs.getString(8);
                
                MotoDTO moto = new MotoDTO(motocycleID, model, year, condition, price, quantity, warranty, brandIDFK);
                if(getMotoList()== null)
                    this.motoList = new ArrayList<>();
                this.motoList.add(moto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }
    
    public boolean addNewMoto(String motocycleID, String model, String year, String condition, float price,
            int quantity, String warranty, String brandIDFK)
                                                                    throws SQLException, SQLServerException, NamingException{
        boolean checkAdd = false;
        try {
            conn = MyConnection.makeConnection();
            if(conn != null) {
                String sql = "INSERT INTO TblBikes (motocycelID, model, year, condition, price, quantity, warranty, brandIDFK)"
                        + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, motocycleID);
                preStm.setString(2, model);
                preStm.setString(3, year);
                preStm.setString(4, condition);
                preStm.setFloat(5, price);
                preStm.setInt(6, quantity);
                preStm.setString(7, warranty);
                preStm.setString(8, brandIDFK);
                checkAdd = preStm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checkAdd;
    }
    
    public boolean updateMoto(String motocycleID, String model, String year, String condition, float price,
            int quantity, String warranty, String brandIDFK)
                                                                    throws SQLException, SQLServerException, NamingException{
        boolean checkUpdate = false;
        try {
            conn = MyConnection.makeConnection();
            if(conn != null) {
                String sql = "UPDATE TblBikes SET model=?, year=?, condition=?, price=?, quantity=?,"
                        + " warranty=?, brandIDFK=?"
                        + " WHERE motocycelID=?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(8, motocycleID);
                preStm.setString(1, model);
                preStm.setString(2, year);
                preStm.setString(3, condition);
                preStm.setFloat(4, price);
                preStm.setInt(5, quantity);
                preStm.setString(6, warranty);
                preStm.setString(7, brandIDFK);
                checkUpdate = preStm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
                
            
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }
    
    public boolean deleteMoto(String motocycleID) throws SQLException, SQLServerException, NamingException{
        boolean checkDelete = false;
        try {
            conn = MyConnection.makeConnection();
            if(conn != null) {
                String sql = "DELETE FROM TblBikes WHERE motocycelID=?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, motocycleID);
                checkDelete = preStm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return checkDelete;
    }
    
    public boolean checkDuplicate(String motoID) {
        boolean checkDup = false;
        try {
            conn = MyConnection.makeConnection();
            String sql = "SELECT FROM TblBikes WHERE motocycelID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, motoID);
            rs = preStm.executeQuery();
            if(rs.next())
                checkDup = true;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return checkDup;
    }
}
