package com.software.website.Entity;

public class Users {
    private int UserID;
    private String FirstName;
    private String LastName;
    private String Email; 

    public Users(){
    }

    public int getUserID(){
        return UserID;
    }

    public void setUserID(int userID){
        this.UserID = userID;
    }

    public String getFirstName(){
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }


    
}
