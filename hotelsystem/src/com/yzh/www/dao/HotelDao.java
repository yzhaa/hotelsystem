package com.yzh.www.dao;

import com.yzh.www.entity.Hotel;

import java.util.ArrayList;

public interface HotelDao {

    public ArrayList<Hotel> findByName(String name);


    public ArrayList<Hotel> findBytype(String type);

    public ArrayList<Hotel> findAll();


    public Hotel findById(int id);


    public boolean updata(Hotel hotel);


    public boolean delete(int id);


    public boolean creat(Hotel hotel);
}
