package com.yzh.www.entity;

import com.yzh.www.serviceImpl.BaseServiceImpl;

/**
 * 服务对应的实体类
 */

public class Service {
    private int id;
    private Hotel hotel;
    private String name;
    private Long price;
    private String content;

    public Service() {
    }

    public Service(int id, String name, Long price, String content, int hotelId) {
        this.id = id;
        this.hotel = new BaseServiceImpl().findHotel(hotelId);
        this.name = name;
        this.price = price;
        this.content = content;
    }


    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getContent() {
        return content;
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
