package com.yzh.www.entity;

import com.yzh.www.service.CustomServiceImpl;
import com.yzh.www.util.Constant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 订单的实体类
 */
public class Order {
    private int id;
    private Hotel hotel;
    private int roomId;
    private Room room;
    private int customerId;
    private Customer customer;
    private Date date;
    private int duration;
    private ArrayList<Service> services;
    private int price;

    public Order(int id,int customerid,int roomId,Date from, Date dateto) {
        this.id = id;
        this.customerId = customerid;
        this.roomId = roomId;
        this.date = from;
        this.duration = (int) ((dateto.getTime()-from.getTime())/ Constant.DAYTIME);
}

    public void continueInit(){
        CustomServiceImpl csi = new CustomServiceImpl();
        this.room = csi.findRoom(roomId);
        this.customer = csi.findCustomerById(customerId);
        this.hotel = csi.findHotelByRoom(roomId);
        Iterator ite = csi.findCusSer(roomId, customerId).iterator();
        services = new ArrayList<Service>();
        for(;ite.hasNext();){
            int i = ((Integer) ite.next()).intValue();
            Service service=(csi.findService(i));
            price += service.getPrice() * duration;
            services.add(service);
        }
        price += room.getPrice() * duration;
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

    public Customer getCustomer() {
        return this.customer;
    }

    public int getPrice() {
        return price;
    }


    public StringBuilder servicesList(){
        Iterator ite = services.iterator();
        StringBuilder sb = new StringBuilder();
        for(;ite.hasNext();){
            sb.append(ite.next().toString());
            sb.append("  ");
        }
        return sb;
    }


    @Override
    public String toString() {
        return "酒店：" + hotel.getName() + "\t房间类型：" + room.getName() + "\n自" +
                new SimpleDateFormat("MM-dd").format(date) + "起，住宿" + duration + "天" +
                " \t酒店服务：" + servicesList();
    }
}
