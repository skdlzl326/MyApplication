package com.example.star.myapplication;


import java.io.Serializable;

public class Store implements Serializable{
    String title;
    String kind;
    String address;
    String opentime;
    String closetime;
    String reservation;
    String phonenumber;


    public Store(String title, String kind, String address, String opentime,String closetime ,String phonenumber) {

        this.kind = kind;
        this.title = title;
        this.address = address;
        this.opentime = opentime;
        this.closetime = closetime;
        this.phonenumber = phonenumber;

    }

    public String toString() {

        return String.format("%s\n%s\n%s\n%s\n%s\n%s",title,kind,address,opentime,closetime,phonenumber);
    }
    public String getkind() {
        return kind;
    }
    public String gettitle() {
        return title;
    }
    public String getaddress() {
        return address;
    }
    public String getopentime() {
        return opentime;
    }
    public String getclosetime() {
        return closetime;
    }
    public String getphonenumber() {
        return phonenumber;
    }
}
