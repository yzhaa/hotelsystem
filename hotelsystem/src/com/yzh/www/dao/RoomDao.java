package com.yzh.www.dao;

import com.yzh.www.entity.Room;

import java.util.ArrayList;

public interface RoomDao {

    public ArrayList<Room> findByHotelId(int id);

    public Room findById(int id);

    /**
     *找酒店空房间
     */
    public ArrayList<Room> findAllEmpty(int hotelid);

    /**
     *判断房间有没有住
     */
    public boolean isEmpty(int id);

    /**
     *插入一个顾客的进房间
     */
    public boolean insertCustomer(int customerId,int roomId);

    /**
     *从房间移除顾客
     */
    public boolean removeCustomer(int id);

    /**
     *找到房间所对应酒店的id
     */
    public int findHotelIdById(int id);

    public boolean updata(Room room);

    public boolean delete(int id);

    /**
     *删除酒店的房间
     */
    public boolean deleteByHotelId(int hotelId);

    public boolean creat(Room room);
}
