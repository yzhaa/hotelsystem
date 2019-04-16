package com.yzh.www.entity;

/**
 * 这个类是用来描述用户基本信息以及关于信息的赋值和获得方法
 */
public class User {
    private int id;
    private int accont;
    private String idCard;
    private String userName;
    private String password;
    private String phoneNumber;

    public User( ) {
        super();
    }

    public User(int id,String userName,String idCard,int accont ,String password, String phoneNumer){
        this.id=id;
        this.userName=userName;
        this.password=password;
        this.phoneNumber=phoneNumer;
        this.accont=accont;
        this.idCard=idCard;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAccont() {
        return accont;
    }

    public void setAccont(int accont) {
        this.accont = accont;
    }
}
