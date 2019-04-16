package com.yzh.www.dao;

import java.sql.Date;
import java.util.ArrayList;

public interface OrderDao {

    public ArrayList findByCustomerId(int customerId);

    public ArrayList findByRoomId(int roomId);

    /**
     *插入一条订单
     */
    public boolean insertOrder( int customerId,int roomId, java.sql.Date dateFrom, Date dateTo);


    public boolean delete(int id);
}
