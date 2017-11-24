package com.example.star.myapplication;


import java.io.Serializable;

public class Store implements Serializable{
    String phonenumber;
    String reservation;
    String closetime;
    String opentime;
    String address;
    String title;
    String kind;

    public Store(String phonenumber, String reservation, String closetime, String opentime, String address, String title, String kind) {
        this.phonenumber = phonenumber;
        this.reservation = reservation;
        this.closetime = closetime;
        this.opentime = opentime;
        this.address = address;
        this.title = title;
        this.kind = kind;
    }

    public String toString() {
        return String.format("%s\n%s\n%s",title,address,kind);
    }
}
