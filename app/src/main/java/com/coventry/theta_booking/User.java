package com.coventry.theta_booking;

/**
 * Created by patela65 on 6/03/17.
 */
public class User {
    private String name;
    private String email;
    private String role;

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getGender(){
        return role;
    }


    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setRole(String gender){
        this.role = gender;
    }

}
