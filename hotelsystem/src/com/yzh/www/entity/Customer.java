package com.yzh.www.entity;

public class Customer extends User{
    public Customer() {
    }

    public Customer(int id, String userName, String idCard, int accont, String password, String phoneNumer) {
        super(id, userName, idCard, accont, password, phoneNumer);
    }

    @Override
    public String toString() {
        return getId()+getAccont()+getPassword()+getUserName();
    }
}
