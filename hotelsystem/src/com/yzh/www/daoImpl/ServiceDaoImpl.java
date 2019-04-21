package com.yzh.www.daoImpl;

import com.yzh.www.dao.ServiceDao;
import com.yzh.www.entity.Service;
import java.util.ArrayList;

public class ServiceDaoImpl implements ServiceDao {
    private BaseDao baseDao = new BaseDao();

    public ArrayList<Service> findAllByHotelId(int hotelid){
        Object[] objects = {hotelid};
        return baseDao.getList(Service.class, objects, "select * from service where hotelid=?");
    }

    public int findOneByName(String name, String element){
        return baseDao.executeQuery("select " + element + " from service where name='" + name + "'").get(0);
    }

    public boolean update(String name,int price,String contence,int id){
        Object[] objects = {name, price, contence, id};
        return baseDao.update("update service set name=?,price=?,content=?  where id=?",objects);
    }

    public Service findById(int id){
        Object[] objects = {id};
        return baseDao.getList(Service.class, objects, "select * from service where id=?").get(0);
    }


    public boolean delete(int id){
        Object[] objects = {id};
        return baseDao.delete("delete from service where id=?", objects);
    }

    public boolean deleteByhotelId(int hotelId) {
        Object[] objects = {hotelId};
        return baseDao.delete("delete from service where hotelid=?", objects);
    }

    public boolean insert(String name,long price,String contece,int hotelid){
        Object[] objects = {name, price, contece, hotelid};
        return baseDao.insert("insert into service (name,price,content,hotelid) values(?,?,?,?)",objects);
    }

}
