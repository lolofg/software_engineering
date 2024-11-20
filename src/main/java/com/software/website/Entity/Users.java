package com.software.website.Entity;

public class Users {
    private int User_ID;
    private String FirstName;
    private String LastName;
    private String Email; 

    public Users(){
    }

    public int getUserID(){
        return User_ID;
    }

    public void setUserID(int userID){
        this.User_ID = userID; 
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
