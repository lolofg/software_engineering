package com.software.website.Entity;

public class Product {

    private int ProductID;
    private String ProductName;
    private int ProductTypeID;

    public Product(){
    }

    public Product(int ProductID, String ProductName, int ProductTypeID){
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductTypeID = ProductTypeID;
    }

    public int getProductID(){
        return ProductID;
    }

    public void setProductID(int ProductID){
        this.ProductID = ProductID;
    }

    public String getProductName(){
        return ProductName;
    }

    public void setProductName(String ProductName){
        this.ProductName = ProductName;
    }

    public int getProductTypeID(){
        return ProductTypeID;
    }

    public void setProductTypeID(int ProductTypeID){
        this.ProductTypeID = ProductTypeID;
    }

    
}