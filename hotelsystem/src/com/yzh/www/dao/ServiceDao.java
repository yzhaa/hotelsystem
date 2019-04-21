package com.yzh.www.dao;

import com.yzh.www.entity.Service;

import java.util.ArrayList;

public interface ServiceDao {

    ArrayList<Service> findAllByHotelId(int hotelid);

    /**
     * 通过服务的名字，查找一个所需要的int型的字段，如酒店的id，服务的id
     */
    int findOneByName(String name, String element);

    boolean update(String name, int price, String contence, int id);

    Service findById(int id);

    boolean delete(int id);

    boolean insert(String name, long price, String contece, int hotelid);

    boolean deleteByhotelId(int hotelId);

}
