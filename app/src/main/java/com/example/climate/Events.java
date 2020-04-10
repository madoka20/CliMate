package com.example.climate;

import java.io.Serializable;
import java.sql.Timestamp;
//event class
public class Events implements Serializable {
    public int userid=0;
    public String eventid;

    public String type;
    public double lat;
    public double lon;
    public Timestamp time;
    public int dangerlevel=1;

    public String description=" ";

    public Events(){

    }
    public Events(String type,double lat, double lon,Timestamp time,int dangerlevel,String description){
       // this.eventid=eventid;

        this.type=type;
        this.lat=lat;
        this.lon=lon;
        this.time=time;
        this.dangerlevel=dangerlevel;

        this.description=description;

    }



}
