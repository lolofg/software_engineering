package com.software.website.Entity;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email; 

    public User(){
    }

    public int getUserID(){
        return userID;
    }

    public void setUserID(int userID){
        this.userID = userID; 
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    
}
