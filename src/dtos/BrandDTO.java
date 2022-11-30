/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Loi Lam
 */
public class BrandDTO {
    
    private String brandID;
    private String brandName;
    private String country;
    private String description;

    public BrandDTO() {
    }

    public BrandDTO(String brandID, String brandName, String country, String description) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.country = country;
        this.description = description;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
