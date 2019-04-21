package com.yzh.www.entity;

import com.yzh.www.serviceImpl.BaseServiceImpl;
import com.yzh.www.serviceImpl.CustomServiceImpl;

import java.util.Date;

public class Account {
    private int id;
    private Hotel hotel;
    private Room room;
    private Customer customer;
    private Date date;
    private int money;

    public Account(int id, int hotelId, int roomid, int customerId, Date date, int money) {
        CustomServiceImpl csi = new CustomServiceImpl();
        this.id = id;
        this.hotel = new BaseServiceImpl().findHotel(hotelId);
        this.room = csi.findRoom(roomid);
        this.customer = csi.findCustomerById(customerId);
        this.date = date;
        this.money = money;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getMoney() {
        return money;
    }


    @Override
    public String toString() {
        if (hotel != null ) {
            if(room!=null){
                return "酒店:"+ hotel.getName() + "\t房间:" + room.getName() + "\t价格："+getMoney()+"\n\n";
            }
            return "酒店:"+ hotel.getName() + "\t房间:该房间已被删除"  + "\t价格："+getMoney()+"\n\n";
        }
        return "酒店:该酒店已被删除" + "\t价格："+getMoney()+"\n\n";
    }
}

