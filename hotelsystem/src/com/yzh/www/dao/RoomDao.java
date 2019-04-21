package com.yzh.www.dao;

import com.yzh.www.entity.Room;

import java.util.ArrayList;

public interface RoomDao {

    ArrayList<Room> findByHotelId(int id);

    Room findById(int id);

    /**
     * 找酒店空房间
     */
    ArrayList<Room> findAllEmpty(int hotelid);

    /**
     *判断房间有没有住
     */
     boolean isEmpty(int id);

    /**
     *插入一个顾客的进房间
     */
     void insertCustomer(int customerId,int roomId);

    /**
     *从房间移除顾客
     */
     boolean removeCustomer(int id);

    /**
     *找到房间所对应酒店的id
     */
     int findHotelIdById(int id);

     void updata(Room room);

     boolean delete(int id);

    /**
     *删除酒店的房间
     */
     boolean deleteByHotelId(int hotelId);

     void creat(Room room);
}
