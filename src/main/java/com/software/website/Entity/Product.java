package com.software.website.Entity;

public class Product {

    private int Product_ID; 
    private String Product_Name; 
    private String productcol; 
    private int ProductType_ID; 

    public Product(){
    }

    public Product(int Product_ID, String Product_Name, String productcol, int ProductType_ID){
        this.Product_ID = Product_ID; 
        this.Product_Name = Product_Name;
        this.productcol = productcol;
        this.ProductType_ID = ProductType_ID; 
    }

    public int getProductID(){
        return Product_ID; 
    }

    public void setProductID(int Product_ID){
        this.Product_ID = Product_ID; 
    }

    public String getProductName(){
        return Product_Name; 
    }

    public void setProductName(String Product_Name){
        this.Product_Name = Product_Name; 
    }

    public String getProductCol(){
        return productcol; 
    }

    public void setProductCol(String productcol){
        this.productcol = productcol; 
    }

    public int getProductTypeID(){
        return ProductType_ID; 
    }

    public void setProductTypeID(int ProductType_ID){
        this.ProductType_ID = ProductType_ID; 
    }

    
}