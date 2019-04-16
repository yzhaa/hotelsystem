package com.yzh.www.entity;

public class Administrator  extends User{
    public Administrator() {
    }

    public Administrator(int id, String userName, String idCard, int accont, String password, String phoneNumer) {
        super(id, userName, idCard, accont, password, phoneNumer);
    }
}
