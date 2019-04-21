package com.yzh.www.daoImpl;


import com.yzh.www.dao.OrderDao;
import com.yzh.www.entity.Order;
import java.sql.Date;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    private BaseDao baseDao = new BaseDao();

    public ArrayList<Order> findByCustomerId(int customerId){
        Object[] objects = {customerId};
        return baseDao.getList(Order.class, objects, "select * from `order` where customerid=?");
    }

    public ArrayList<Order> findByRoomId(int roomId){
        Object[] objects = {roomId};
        return baseDao.getList(Order.class, objects, "select * from `order` where roomid=?");
    }

    public void insertOrder( int customerId,int roomId, java.sql.Date dateFrom, Date dateTo){
        Object[] objects = {customerId, roomId, dateFrom, dateTo};
        baseDao.insert("insert into `order` (customerid ,roomid , datefrom ,dateto) values(?,?,?,?)", objects);
    }

    public boolean delete(int id) {
        Object[] objects = {id};
        return baseDao.delete("delete from `order` where id=?",objects);
    }
}
