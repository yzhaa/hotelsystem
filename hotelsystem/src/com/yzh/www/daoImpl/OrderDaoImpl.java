package com.yzh.www.daoImpl;


import com.yzh.www.dao.OrderDao;
import com.yzh.www.entity.Order;
import com.yzh.www.factory.DaoFactory;

import java.sql.Date;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    private BaseDao baseDao = DaoFactory.getBaseDao();

    public ArrayList<Order> findByCustomerId(int customerId){
        return baseDao.getList(Order.class,  "select * from `order` where customerid=?",customerId);
    }

    public ArrayList<Order> findByRoomId(int roomId){
        return baseDao.getList(Order.class, "select * from `order` where roomid=?",roomId);
    }

    public void insertOrder( int customerId,int roomId, java.sql.Date dateFrom, Date dateTo){
        baseDao.insert("insert into `order` (customerid ,roomid , datefrom ,dateto) values(?,?,?,?)", customerId, roomId, dateFrom, dateTo);
    }

    public boolean delete(int id) {
        return baseDao.delete("delete from `order` where id=?",id);
    }
}
