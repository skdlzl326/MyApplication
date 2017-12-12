package com.example.star.myapplication;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    String storename;
    String content;
    String writer;
    String images;
    String grade;
    String date;

    public Review(String grade, String storename, String content, String writer,String images,String date) {
        this.grade = grade;
        this.storename = storename;
        this.content = content;
        this.writer = writer;
        this.images = images;
        this.date = date;
    }

    public String toString() {
        return String.format("%s\n%s\n%s\n%s\n%s\n%s",grade,storename,content,writer,images,date);
    }

    public String getStorename() {
        return storename;
    }
    public String getContent() {
        return content;
    }
    public String getWriter() {return writer;}
    public String getImages() {return images; }
    public String getGrade() {return grade; }
    public String getDate(){return date;}
}
