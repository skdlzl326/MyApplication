package com.example.star.myapplication;


import java.io.Serializable;

public class Book implements Serializable{
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
        return String.format("%s\n%s\n%s\n%s",username,nickname,password,gender);
    }
}
