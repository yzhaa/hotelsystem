package com.yzh.www.entity;


import com.yzh.www.factory.ServiceFactory;
import javafx.beans.property.SimpleStringProperty;

/**
 * 房间对应的实体类
 */

public class Room {
    private int id;
    private Hotel hotel;
    private String name;
    private String type;
    private String info;
    private int price;
    private SimpleStringProperty nameProperty;
    private SimpleStringProperty typeProperty;
    private SimpleStringProperty infoProperty;
    private SimpleStringProperty priceProperty;

    public Room() {
    }

    public Room(int id,int hotelid ,int customerid,String name, String type, String info, int price) {
        this.id = id;
        this.hotel = ServiceFactory.getBaseService().findHotel(hotelid);
        this.name = name;
        this.type = type;
        this.info = info;
        this.price = price;
    }

    public Room(Hotel hotel,String name,String type,String info,String price){
        this.hotel = hotel;
        this.name = name;
        this.type = type;
        this.info = info;
        nameProperty = new SimpleStringProperty(name);
        typeProperty = new SimpleStringProperty(type);
        infoProperty = new SimpleStringProperty(info);
        priceProperty = new SimpleStringProperty(String.valueOf(price));
        if(!price.equals("")) this.price = Integer.parseInt(price);
    }

    public void continueInit(){
        nameProperty = new SimpleStringProperty(name);
        typeProperty = new SimpleStringProperty(type);
        infoProperty = new SimpleStringProperty(info);
        priceProperty = new SimpleStringProperty(String.valueOf(price));
    }

    public int getId() {
        return id;
    }


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setPrice(String price) {
        if(!price.equals(""))
        this.price = Integer.parseInt(price);
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public SimpleStringProperty namePropertyProperty() {
        return nameProperty;
    }

    public SimpleStringProperty typePropertyProperty() {
        return typeProperty;
    }

    public SimpleStringProperty infoPropertyProperty() {
        return infoProperty;
    }

    public SimpleStringProperty pricePropertyProperty() {
        return priceProperty;
    }
}
