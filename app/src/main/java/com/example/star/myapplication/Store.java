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
    String description;
    String images;
    String writer;


    public Store(String title, String kind, String address, String opentime,String closetime ,String phonenumber, String description, String images) {

        this.kind = kind;
        this.title = title;
        this.address = address;
        this.opentime = opentime;
        this.closetime = closetime;
        this.phonenumber = phonenumber;
        this.description = description;
        this.images = images;


    }

    public String toString() {

        return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s",title,kind,address,opentime,closetime,phonenumber,description,images);
    }

    public String getkind() {
        return kind;
    }
    public String gettitle() {
        return title;
    }
    public String getaddress() {
        String st = address;
        String[] arr = st.split(" ");
        return arr[0];
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
    public String getdescription() {return description; }
    public String getimages() {return images; }
}
