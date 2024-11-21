package com.software.website.Entity;

public class Users_Device {

    private int DeviceID;
    private int UsersID;
    private int ProductID;
    private String Name;

    public Users_Device() {

    }


    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int DeviceID) {
        this.DeviceID = DeviceID;
    }

    public int getUsersID() {
        return UsersID;
    }

    public void setUsersID(int UsersID) {
        this.UsersID = UsersID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

}
