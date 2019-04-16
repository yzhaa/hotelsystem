package com.yzh.www.entity;

import com.yzh.www.service.BaseServiceImpl;

/**
 * 服务对应的实体类
 */

public class Service {
    private int id;
    private Hotel hotel;
    private String name;
    private int price;
    private String content;

    public Service() {
    }

    public Service(int id, int hotelId, String name, int price, String content) {
        this.id = id;
        this.hotel =new BaseServiceImpl().findHotel(hotelId);
        this.name = name;
        this.price = price;
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
