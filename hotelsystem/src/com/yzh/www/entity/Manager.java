package com.yzh.www.entity;

import com.yzh.www.serviceImpl.BaseServiceImpl;

/**
 * 管理员对应的实体类
 */

public class Manager extends User {
    private Hotel hotel;

    public Manager(int id, String userName, String idCard, int accont, String password, String phoneNumer,int hotelId) {
        super(id, userName, idCard, accont, password, phoneNumer);
        this.hotel = new BaseServiceImpl().findHotel(hotelId);
    }

    public Manager() {
        super();
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
