package com.example.climate;

import android.location.Location;

import java.io.File;
import java.io.Serializable;
import java.security.PublicKey;
import java.sql.Timestamp;

public class Events implements Serializable {
    public int userid=0;


    public String type;
    public double lat;
    public double lon;
    public Timestamp time;
    public int dangerlevel=1;

    public String description=" ";

    public Events(){

    }
    public Events(int userid,String type,double lat, double lon,Timestamp time,int dangerlevel,String description){
        this.userid=userid;

        this.type=type;
        this.lat=lat;
        this.lon=lon;
        this.time=time;
        this.dangerlevel=dangerlevel;

        this.description=description;

    }



}
