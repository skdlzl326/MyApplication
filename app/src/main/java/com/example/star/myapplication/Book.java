package com.example.star.myapplication;


public class Book {
    String username;
    String password;
    String nickname;
    String gender;

    public Book(String username, String password, String nickname, String gender) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
    }

    public String toString() {
        return String.format("ID = %s \n Nickname = %s",username,nickname);
    }
}
