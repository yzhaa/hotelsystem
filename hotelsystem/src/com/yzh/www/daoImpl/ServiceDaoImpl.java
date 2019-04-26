package com.yzh.www.daoImpl;

import com.yzh.www.dao.ServiceDao;
import com.yzh.www.entity.Service;
import com.yzh.www.factory.DaoFactory;

import java.util.ArrayList;

public class ServiceDaoImpl implements ServiceDao {
    private BaseDao baseDao = DaoFactory.getBaseDao();

    public ArrayList<Service> findAllByHotelId(int hotelid){
        return baseDao.getList(Service.class, "select * from service where hotelid=?",hotelid);
    }

    public int findOneByName(String name, String element){
        return baseDao.executeQuery("select " + element + " from service where name=?",name).get(0);
    }

    public boolean update(String name,int price,String contence,int id){
        return baseDao.update("update service set name=?,price=?,content=?  where id=?",name, price, contence, id);
    }

    public Service findById(int id){
        return baseDao.getList(Service.class,"select * from service where id=?",id).get(0);
    }


    public boolean delete(int id){
        return baseDao.delete("delete from service where id=?", id);
    }

    public boolean deleteByhotelId(int hotelId) {
        return baseDao.delete("delete from service where hotelid=?", hotelId);
    }

    public boolean insert(String name,long price,String contece,int hotelid){
        return baseDao.insert("insert into service (name,price,content,hotelid) values(?,?,?,?)",name, price, contece, hotelid);
    }

}
