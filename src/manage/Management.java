/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import daos.BrandDAO;
import daos.MotoDAO;
import dtos.BrandDTO;
import dtos.MotoDTO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Loi Lam
 */
public class Management extends javax.swing.JFrame {

    Object brandHeader[] = new Object[4];
    Object brandData[] = new Object[4];
    
    Object motoHeader[] = new Object[8];
    Object motoData[] = new Object[8];
    
    ArrayList<BrandDTO> brandList = new ArrayList<>();
    ArrayList<MotoDTO> motoList = new ArrayList<>();
    Vector<String> brandIdList = new Vector<>();

    
    boolean addNewBrand = false;
    boolean addNewMoto = false;
    
    
    /**
     * Creates new form NewJFrame
     */
    public Management() throws SQLException, SQLServerException, NamingException {
        initComponents();
        
        brandHeader[0] = "ID";
        brandHeader[1] = "Brand Name";
        brandHeader[2] = "Country";
        brandHeader[3] = "Description";
        
        motoHeader[0] = "ID";
        motoHeader[1] = "Model";
        motoHeader[2] = "Year";
        motoHeader[3] = "Condition";
        motoHeader[4] = "Price";
        motoHeader[5] = "Quantity";
        motoHeader[6] = "Warranty";
        motoHeader[7] = "Brand Name";
        
                updateData();
//                updateCB();
    }

    public void updateData() throws SQLException, SQLServerException, NamingException {
        BrandDAO brandDao = new BrandDAO();
        brandDao.fetchBrand();
        brandList = brandDao.getBrandList();
        DefaultTableModel brandModel = (DefaultTableModel) this.tblBrand.getModel();
        brandModel.setRowCount(0);
        brandModel.setColumnIdentifiers(brandHeader);
        if(brandList != null) {
            for (int i = 0; i < brandList.size(); i++) {
                brandData[0] = brandList.get(i).getBrandID();
                brandData[1] = brandList.get(i).getBrandName();
                brandData[2] = brandList.get(i).getCountry();
                brandData[3] = brandList.get(i).getDescription();
                brandIdList.addElement(brandList.get(i).getBrandName());
                brandModel.addRow(brandData);
                brandModel.getDataVector().elementAt(i);
            }
        }
        
        
        MotoDAO motoDao = new MotoDAO();
        motoDao.fetchMoto();
        motoList = motoDao.getMotoList();
        DefaultTableModel motoModel = (DefaultTableModel) this.tblMoto.getModel();
        motoModel.setRowCount(0);
        motoModel.setColumnIdentifiers(motoHeader);
        if(motoList != null) {
            for (int i = 0; i < motoList.size(); i++) {
                motoData[0] = motoList.get(i).getMotocycleID();
                motoData[1] = motoList.get(i).getModel();
                motoData[2] = motoList.get(i).getYear();
                motoData[3] = motoList.get(i).getCondition();
                motoData[4] =(int) motoList.get(i).getPrice();
                motoData[5] = motoList.get(i).getQuantity();
                motoData[6] = motoList.get(i).getWarranty();
                for (int j = 0; j < brandList.size(); j++) {
                    if(brandList.get(j).getBrandID().equals(motoList.get(i).getBrandIDFK()))
                    motoData[7] = brandList.get(j).getBrandName();
                }
                
                motoModel.addRow(motoData);
                motoModel.getDataVector().elementAt(i);
            }
        }
        
        cbBrandName.setModel(new DefaultComboBoxModel<>(brandIdList));

        tblBrand.updateUI();
        tblMoto.updateUI();
        
        if(brandList == null)
            JOptionPane.showMessageDialog(this, "Your data of brand is empty");
        if(motoList == null)
            JOptionPane.showMessageDialog(this, "Your data of moto is empty");
    }
    
    public void updateCB() {
        brandIdList.removeAllElements();;
    }
    
    public void resetDataBrand() {
        txtBrandID.requestFocus();
        txtBrandID.setText("");
        txtBrandName.setText("");
        txtCountry.setText("");
        taDescription.setText("");
    }
    
    public void resetDataMoto() {
        txtMotoID.requestFocus();
        txtMotoID.setText("");
        txtModel.setText("");
        dcYear.setCalendar(Calendar.getInstance());
        txtCondition.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtWarranty.setText("");
        cbBrandName.setSelectedIndex(0);
    }
    
    public boolean checkPrice(String price) {
        String ePattern = "\\d{10}";
           Pattern p = Pattern.compile(ePattern);
           Matcher m = p.matcher(price);
           return m.matches();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnAddNewBrand = new javax.swing.JButton();
        btnSaveBrand = new javax.swing.JButton();
        btnDeleteBrand = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBrandID = new javax.swing.JTextField();
        txtBrandName = new javax.swing.JTextField();
        txtCountry = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBrand = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnAddNewMoto = new javax.swing.JButton();
        btnSaveMoto = new javax.swing.JButton();
        btnDeleteMo = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMotoID = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        txtCondition = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtWarranty = new javax.swing.JTextField();
        cbBrandName = new javax.swing.JComboBox<>();
        dcYear = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMoto = new javax.swing.JTable();

        jTextField3.setText("jTextField3");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detail part:"));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 397));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.GridBagLayout());

        btnAddNewBrand.setText("Add New");
        btnAddNewBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewBrandActionPerformed(evt);
            }
        });
        jPanel7.add(btnAddNewBrand, new java.awt.GridBagConstraints());

        btnSaveBrand.setText("Save");
        btnSaveBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBrandActionPerformed(evt);
            }
        });
        jPanel7.add(btnSaveBrand, new java.awt.GridBagConstraints());

        btnDeleteBrand.setText("Delete");
        btnDeleteBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteBrandActionPerformed(evt);
            }
        });
        jPanel7.add(btnDeleteBrand, new java.awt.GridBagConstraints());

        jPanel3.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jLabel1.setText("Brand ID:");

        jLabel2.setText("Brand Name:");

        jLabel3.setText("Country:");

        jLabel4.setText("Description:");

        taDescription.setColumns(20);
        taDescription.setRows(5);
        jScrollPane3.setViewportView(taDescription);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(9, 9, 9))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)))
                            .addComponent(jLabel4))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtBrandID, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(txtBrandName)
                            .addComponent(txtCountry))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBrandID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBrandName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_END);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Main part:"));
        jPanel4.setLayout(new java.awt.BorderLayout());

        tblBrand.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Country", "Description"
            }
        ));
        tblBrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBrandMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBrand);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Brand", jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detail part:"));
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 397));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.GridBagLayout());

        btnAddNewMoto.setText("Add New");
        btnAddNewMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewMotoActionPerformed(evt);
            }
        });
        jPanel9.add(btnAddNewMoto, new java.awt.GridBagConstraints());

        btnSaveMoto.setText("Save");
        btnSaveMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMotoActionPerformed(evt);
            }
        });
        jPanel9.add(btnSaveMoto, new java.awt.GridBagConstraints());

        btnDeleteMo.setText("Delete");
        btnDeleteMo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMoActionPerformed(evt);
            }
        });
        jPanel9.add(btnDeleteMo, new java.awt.GridBagConstraints());

        jPanel5.add(jPanel9, java.awt.BorderLayout.PAGE_END);

        jLabel5.setText("Motocycle ID:");

        jLabel6.setText("Model:");

        jLabel7.setText("Year:");

        jLabel8.setText("Condition:");

        jLabel9.setText("Price:");

        jLabel10.setText("Quantity:");

        jLabel11.setText("Warranty:");

        jLabel12.setText("Brand Name:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMotoID, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtModel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(txtCondition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(txtWarranty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(cbBrandName, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel5)
                                                    .addComponent(txtMotoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel7))
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(dcYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCondition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9))
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10))
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11))
                    .addComponent(txtWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbBrandName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5, java.awt.BorderLayout.LINE_END);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Main part:"));
        jPanel6.setLayout(new java.awt.BorderLayout());

        tblMoto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Model", "Year", "Condition", "Price", "Quantity", "Warranty", "Brand Name"
            }
        ));
        tblMoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMotoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMoto);

        jPanel6.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Motocycle", jPanel2);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBrandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBrandMouseClicked
        // TODO add your handling code here:
        txtBrandID.setEnabled(false);
        int brandIndex = tblBrand.getSelectedRow();
        BrandDTO brandTbl = brandList.get(brandIndex);
        txtBrandID.setText(brandTbl.getBrandID());
        txtBrandName.setText(brandTbl.getBrandName());
        txtCountry.setText(brandTbl.getCountry());
        taDescription.setText(brandTbl.getDescription());
    }//GEN-LAST:event_tblBrandMouseClicked

    private void tblMotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMotoMouseClicked
        // TODO add your handling code here:
        txtMotoID.setEnabled(false);
        int motoIndex = tblMoto.getSelectedRow();
        MotoDTO motoTbl = motoList.get(motoIndex);
        txtMotoID.setText(motoTbl.getMotocycleID());
        txtModel.setText(motoTbl.getModel());
        dcYear.setDate(motoTbl.getYear());
        txtCondition.setText(motoTbl.getCondition());
        int priceB = (int)motoTbl.getPrice();
        txtPrice.setText(priceB+"");
        txtQuantity.setText(motoTbl.getQuantity()+"");
        txtWarranty.setText(motoTbl.getWarranty());
        int index = -1;
        for (int i = 0; i < brandList.size(); i++) {
            if(motoTbl.getBrandIDFK().equalsIgnoreCase(brandList.get(i).getBrandID()))
                index = i;
        }
        cbBrandName.setSelectedIndex(index);
        
    }//GEN-LAST:event_tblMotoMouseClicked

    private void btnAddNewBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewBrandActionPerformed
        // TODO add your handling code here:
        txtBrandID.setEnabled(true);
        resetDataBrand();
        addNewBrand = true;
    }//GEN-LAST:event_btnAddNewBrandActionPerformed

    private void btnSaveBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBrandActionPerformed
        // TODO add your handling code here:
        String brandID = txtBrandID.getText();
        if(brandID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input the Brand ID");
            return;
        }
        else if(brandID.length() > 10) {
            JOptionPane.showMessageDialog(this, "Max length of Brand ID is 10");
            return;
        }
        String brandName = txtBrandName.getText();
        if(brandName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input the Brand Name");
            return;
        }
        else if(brandName.length() > 50) {
            JOptionPane.showMessageDialog(this, "Max length of Brand name is 50");
            return;
        }
        String country = txtCountry.getText();
        if(country.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input the Country");
            return;
        }
        else if(country.length() > 50) {
            JOptionPane.showMessageDialog(this, "Max length of Country is 50");
            return;
        }
        String description = taDescription.getText();
        if(description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input the Description");
            return;
        }
        else if(description.length() > 200) {
            JOptionPane.showMessageDialog(this, "Max length of Description is 200");
            return;
        }
        BrandDAO brandDao = new BrandDAO();
        if(addNewBrand == true) {
            try {
                try {
                    boolean chechDup = brandDao.checkDuplicate(brandID);
                    if(chechDup) {
                        JOptionPane.showMessageDialog(this, "Brand ID had been duplicate.");
                        return;
                    }
                } catch (Exception e) {
                }
                boolean checkAddNew = brandDao.addNewBrand(brandID, brandName, country, description);
                if(checkAddNew) {
                    JOptionPane.showMessageDialog(this, "New Brand added Successful.");
                    updateCB();
                    updateData();
                }
            } catch (Exception e) {
            }
        } else if(addNewBrand == false){
            try {
                boolean checkUpdate = brandDao.updateBrand(brandID, brandName, country, description);
                if(checkUpdate) {
                    JOptionPane.showMessageDialog(this, "New Brand had been Updated.");
                    updateCB();
                    updateData();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error.");

            }
        }
        resetDataBrand();
        addNewBrand = false;
    }//GEN-LAST:event_btnSaveBrandActionPerformed

    private void btnDeleteBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteBrandActionPerformed
        // TODO add your handling code here:
        String brandID = txtBrandID.getText();
        if(!txtBrandID.isEditable() && txtBrandID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose brand you want to delete.");
            resetDataBrand();
            return;
        }
        if(brandID.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Please choose brand you want to delete.");
        } else {
            BrandDAO brandDao = new BrandDAO();
            boolean check = brandDao.checkP(brandID);
            if(check) {
                JOptionPane.showMessageDialog(this, "Cannot delete brand when still have moto.");
                    return;
            }
            try {
                int confirmDelete = JOptionPane.showConfirmDialog(null, "Do you want to delete this Brand?", "", 
                                                            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(confirmDelete == JOptionPane.YES_OPTION) {
                    boolean deleteBrand = brandDao.deleteBrand(brandID);
                    if(deleteBrand) {
                        JOptionPane.showMessageDialog(this, "This brand deleted");
                        updateData();
                        updateCB();
                    }
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnDeleteBrandActionPerformed

    private void btnAddNewMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewMotoActionPerformed
        // TODO add your handling code here:
        txtMotoID.setEnabled(true);
        resetDataMoto();
        addNewMoto = true;
    }//GEN-LAST:event_btnAddNewMotoActionPerformed

    private void btnSaveMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveMotoActionPerformed
        // TODO add your handling code here:
        String motoID = txtMotoID.getText();
        if(motoID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input the Moto ID");
            return;
        }
        else if(motoID.length() > 10) {
            JOptionPane.showMessageDialog(this, "Max length of Moto ID is 10");
            return;
        }
        String model = txtModel.getText();
        if(model.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input the Model");
            return;
        }
        else if(model.length() > 50) {
            JOptionPane.showMessageDialog(this, "Max length of Model is 50");
            return;
        }
//        String year = txtYear.getText();
//        if(year.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please input the year");
//            return;
//        }
//        String regex = "[0-9][0-9][0-9][0-9][-][0-1][0-2][-][0-3][0-9]";
//        if(!year.matches(regex) == true) {
//             JOptionPane.showMessageDialog(this, "Format od year is yyyy-mm-dd");
//            return;
//        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String year = sdf.format(dcYear.getDate());
        String condition = txtCondition.getText();
        if(condition.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input the condition");
            return;
        }
        else if(condition.length() > 50) {
            JOptionPane.showMessageDialog(this, "Max length of condition is 50");
            return;
        }
        Float price;
        try {
             price = Float.parseFloat(txtPrice.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter the number with Price");
            return;
        }
        if(txtPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input the price");
            return;
        } else if(price < 10000000 || price > 1000000000) {
            JOptionPane.showMessageDialog(this, "Price cannot lower 10.000.000 and upper 1.000.000.000");
            return;
        } else if(txtPrice.getText().length() > 10) {
            JOptionPane.showMessageDialog(this, "Max length of Price is 10");
            return;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(txtQuantity.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter the number with quantity");
            return;
        }
        if(txtQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input the quantity");
            return;
        }
        else if(txtQuantity.getText().length() > 100) {
            JOptionPane.showMessageDialog(this, "Max length of Quantity is 100");
            return;
        }
        String warranty = txtWarranty.getText();
        if(warranty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input the warranty");
            return;
        }
        else if(warranty.length() > 200) {
            JOptionPane.showMessageDialog(this, "Max length of warranty is 200");
            return;
        }
        String brandName = null;
        for (int i = 0; i < brandList.size(); i++) {
            if(cbBrandName.getSelectedItem().equals(brandList.get(i).getBrandName()))
                brandName = brandList.get(i).getBrandID();
        }
        MotoDAO motoDao = new MotoDAO();
        if(addNewMoto == true) {
            try {
                try {
                    boolean chechDup = motoDao.checkDuplicate(motoID);
                    if(chechDup) {
                        JOptionPane.showMessageDialog(this, "Moto ID had been duplicate.");
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Format of Year is yyyy-dd-mm");
            return;
                }
                boolean checkAddNew = motoDao.addNewMoto(motoID, model, year, condition, price, quantity, warranty, brandName);
                if(checkAddNew) {
                    JOptionPane.showMessageDialog(this, "New Moto added Successful.");
                    updateCB();
                    updateData();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Format of Year is yyyy-dd-mm");
            return;
            }
        } else if(addNewMoto == false){
            try {
                boolean checkUpdate = motoDao.updateMoto(motoID, model, year, condition, price, quantity, warranty, brandName);
                if(checkUpdate) {
                    JOptionPane.showMessageDialog(this, "New Moto had been Updated.");
                    updateCB();
                    updateData();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error.");
                return;
            }
        }
        resetDataMoto();
        addNewMoto = false;
    }//GEN-LAST:event_btnSaveMotoActionPerformed

    private void btnDeleteMoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMoActionPerformed
        // TODO add your handling code here:
        String motoID = txtMotoID.getText();
        if(!txtMotoID.isEditable() && txtMotoID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose moto you want to delete.");
            resetDataMoto();
            return;
        }
        if(motoID.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Please choose moto you want to delete.");
        } else {
            MotoDAO motoDao = new MotoDAO();
            try {
                int confirmDelete = JOptionPane.showConfirmDialog(null, "Do you want to delete this Moto?", "", 
                                                            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(confirmDelete == JOptionPane.YES_OPTION) {
                    boolean deleteMoto = motoDao.deleteMoto(motoID);
                    if(deleteMoto) {
                        JOptionPane.showMessageDialog(this, "This moto deleted");
                        updateData();
                    }
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnDeleteMoActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Management().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewBrand;
    private javax.swing.JButton btnAddNewMoto;
    private javax.swing.JButton btnDeleteBrand;
    private javax.swing.JButton btnDeleteMo;
    private javax.swing.JButton btnSaveBrand;
    private javax.swing.JButton btnSaveMoto;
    private javax.swing.JComboBox<String> cbBrandName;
    private com.toedter.calendar.JDateChooser dcYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTable tblBrand;
    private javax.swing.JTable tblMoto;
    private javax.swing.JTextField txtBrandID;
    private javax.swing.JTextField txtBrandName;
    private javax.swing.JTextField txtCondition;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtMotoID;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtWarranty;
    // End of variables declaration//GEN-END:variables
}
