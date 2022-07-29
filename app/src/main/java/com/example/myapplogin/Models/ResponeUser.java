package com.example.myapplogin.Models;

import java.io.Serializable;

public class ResponeUser implements Serializable {
    private String  msg;
    private String username;

    public ResponeUser(String msg, String username) {
        this.msg = msg;
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
