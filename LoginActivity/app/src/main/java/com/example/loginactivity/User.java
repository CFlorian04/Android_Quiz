package com.example.loginactivity;

import java.io.Serializable;

public class User implements Serializable {
    String userName, userPWD;

    public User(String Name, String password) {
        this.userName = Name;
        this.userPWD = password;

    }
    public User() {
        this.userName = "";
        this.userPWD = "";
    }

    public String getuserName() {
        return userName;
    }

    public String getuserPWD() {
        return userPWD;
    }

    public String getInfo() {
        return "Bonjour " + this.getuserName() + ". Votre mot de passe est : " + this.getuserPWD();
    }

    public void setUser(User pUser) {
        this.userName = pUser.userName;
        this.userPWD = pUser.userPWD;
    }
}
