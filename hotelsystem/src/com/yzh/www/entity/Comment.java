package com.yzh.www.entity;

import com.yzh.www.service.BaseServiceImpl;


public class Comment {
    private int id;
    private String comment;
    private int point;
    private int roomid;
    private Hotel hotel;

    public Comment(int id, String comment, int point,int roomid,int hotelId) {
        this.id = id;
        this.comment = comment;
        this.point = point;
        this.roomid = roomid;
        this.hotel = new BaseServiceImpl().findHotel(hotelId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "评价分数：" + this.point+"\n评价内容：" + this.getComment()+"\n\n" ;
    }
}
