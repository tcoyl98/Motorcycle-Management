/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Date;

/**
 *
 * @author Loi Lam
 */
public class MotoDTO {
    private String motocycleID;
    private String model;
    private Date year;
    private String condition;
    private float price;
    private int quantity;
    private String warranty;
    private String brandIDFK;

    public MotoDTO() {
    }

    public MotoDTO(String motocycleID, String model, Date year, String condition, float price, int quantity, String warranty, String brandIDFK) {
        this.motocycleID = motocycleID;
        this.model = model;
        this.year = year;
        this.condition = condition;
        this.price = price;
        this.quantity = quantity;
        this.warranty = warranty;
        this.brandIDFK = brandIDFK;
    }

    public String getMotocycleID() {
        return motocycleID;
    }

    public void setMotocycleID(String motocycleID) {
        this.motocycleID = motocycleID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getBrandIDFK() {
        return brandIDFK;
    }

    public void setBrandIDFK(String brandIDFK) {
        this.brandIDFK = brandIDFK;
    }
}
