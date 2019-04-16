package com.yzh.www.dao;

import com.yzh.www.entity.Service;

import java.util.ArrayList;

public interface ServiceDao {
    /**
     * @param hotelid
     * @return
     */

    public ArrayList<Service> findAllByHotelId(int hotelid);

    /**
     *通过服务的名字，查找一个所需要的int型的字段，如酒店的id，服务的id
     * @param name
     * @param element
     * @return
     */
    public int findOneByName(String name, String element);


    public boolean update(String name,int price,String contence,int id);

    public Service findById(int id);


    public boolean delete(int id);


    public boolean insert(String name,int price,String contece,int hotelid);

    public boolean deleteByhotelId(int hotelId);
}
