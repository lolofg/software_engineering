package com.software.website.Entity;

public class ProductType {
    private int ProductType_ID; 
    private String ProductType_Name; 

    public ProductType(){
    }

    public int getProductTypeID() {
        return ProductType_ID;
    }

    public void setProductTypeID(int ProductType_ID) {
        this.ProductType_ID = ProductType_ID;
    }

    
    public String getProductTypeName() {
        return ProductType_Name;
    }

    public void setProductTypeName(String ProductType_Name) {
        this.ProductType_Name = ProductType_Name;
    }

}

