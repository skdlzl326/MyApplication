package com.example.star.myapplication;


public class Book {
    String id;
    String password;
    String name;
    String nickname;
    String sex;

    public Book(String id, String password, String name, String nickname, String sex) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.sex = sex;
    }

    public String toString() {
        return String.format("ID = %s \n Nickname = %s",id,id, nickname);
    }
}
