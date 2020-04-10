package com.example.climate;
//user class
public class User {
    public String username;
    public String country;
    public String state;
    public String city;
    public String email;
    public String phone_num;
    public User(){}
    public User(String username,String country,String state,String city,String email, String phone_num){
        this.username=username;
        this.country=country;
        this.state=state;
        this.city=city;
        this.email=email;
        this.phone_num=phone_num;
    }

}
