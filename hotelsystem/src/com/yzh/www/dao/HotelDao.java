package com.yzh.www.dao;

import com.yzh.www.entity.Hotel;

import java.util.ArrayList;

public interface HotelDao {

    ArrayList<Hotel> findByName(String name);

    ArrayList<Hotel> findBytype(String type);

    ArrayList<Hotel> findAll();

    Hotel findById(int id);

    void updata(Hotel hotel);

    boolean delete(int id);

    void creat(Hotel hotel);
}
