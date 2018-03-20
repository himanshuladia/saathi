package com.example.android.hackrajasthan.Login;



public class UserInformation {

    String Name;
    String Email;
    boolean Authority;

    public UserInformation(String Name,String Email)
    {
        this.Name=Name;
        this.Email=Email;
        Authority=false;
    }
}