package com.yzh.www.dao;

import com.yzh.www.entity.Order;

import java.sql.Date;
import java.util.ArrayList;

public interface OrderDao {

    ArrayList<Order> findByCustomerId(int customerId);

    ArrayList<Order> findByRoomId(int roomId);

    /**
     *插入一条订单
     */
    void insertOrder( int customerId,int roomId, java.sql.Date dateFrom, Date dateTo);


    boolean delete(int id);
}
