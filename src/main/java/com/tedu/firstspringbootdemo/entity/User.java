package com.tedu.firstspringbootdemo.entity;

import java.io.Serializable;

/**
 * 使用当前类实例表示一个注册用户
 */
public class User  implements Serializable {
    private String username;
    private String pwd;
    private String nick;
    private int age;

    public User() {
    }

    public User(String username, String pwd, String nick, int age) {
        this.username = username;
        this.pwd = pwd;
        this.nick = nick;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nick='" + nick + '\'' +
                ", age=" + age +
                '}';
    }
}
