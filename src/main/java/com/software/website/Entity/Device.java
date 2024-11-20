package com.software.website.Entity;

public class Device {

    private String Device_ID;
    private int Users_ID;
    private int Product_ID;
    private String Name;

    public Device() {

    }

    public String getDeviceID(){
        return Device_ID;
    }

    public void setDeviceID(String Device_ID) {
        this.Device_ID = Device_ID; 
    }

    public int getUsersID() {
        return Users_ID;
    }

    public void setUsersID(int Users_ID) {
        this.Users_ID = Users_ID;
    }

    public int getProductID() {
        return Product_ID;
    }

    public void setProductID(int Product_ID) {
        this.Product_ID = Product_ID;
    }

    public String getName(){
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name; 
    }





}
 