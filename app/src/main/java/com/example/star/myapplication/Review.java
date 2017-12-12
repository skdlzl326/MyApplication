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

    public Review(String storename, String content, String writer,String images,String grade, String date) {
        this.storename = storename;
        this.content = content;
        this.writer = writer;
        this.images = images;
        this.grade = grade;
        this.date = date;
    }

    public String toString() {
        return String.format("%s\n%s\n%s\n%s",storename,content,writer,images,grade,date);
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
