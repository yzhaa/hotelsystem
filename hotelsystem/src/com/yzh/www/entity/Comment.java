package com.yzh.www.entity;

import com.yzh.www.serviceImpl.BaseServiceImpl;


public class Comment {
    private int id;
    private String comment;
    private int point;
    private Hotel hotel;

    public Comment(int id, String comment, int point,int hotelId) {
        this.id = id;
        this.comment = comment;
        this.point = point;
        this.hotel = new BaseServiceImpl().findHotel(hotelId);
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

    @Override
    public String toString() {
        return "评价分数：" + this.point+"\n评价内容：" + comment+"\n\n" ;
    }
}
