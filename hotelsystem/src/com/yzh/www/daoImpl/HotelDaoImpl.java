package com.yzh.www.daoImpl;

import com.yzh.www.dao.HotelDao;
import com.yzh.www.entity.Hotel;
import com.yzh.www.factory.DaoFactory;

import java.util.ArrayList;

public class HotelDaoImpl implements HotelDao {
    private BaseDao baseDao = DaoFactory.getBaseDao();

    public ArrayList<Hotel> findByName(String name){
       return baseDao.getList(Hotel.class,  "select * from hotel where name like ?","%"+name+"%");
    }

    public ArrayList<Hotel> findBytype(String type){
        return baseDao.getList(Hotel.class,  "select * from hotel where type=?",type);
    }

    public ArrayList<Hotel> findAll(){
        return baseDao.getList(Hotel.class,  "select * from hotel",null);
    }

    public Hotel findById(int id){
        ArrayList<Hotel> arrayList=baseDao.getList(Hotel.class, "select * from hotel where id=?",id);
        if(arrayList.size()>0){
            return arrayList.get(0);
        }
        return null;
    }

    public void updata(Hotel hotel){
        baseDao.update("update hotel set name=? ,type=? ,info=?, managerid =?  where id=?",
                hotel.getName(),hotel.getType(),hotel.getInfo(),hotel.getManager(),hotel.getId());
    }

    public boolean delete(int id){
        return baseDao.delete("delete from hotel where id=?", id);
    }

    public void creat(Hotel hotel){
        baseDao.insert("insert into hotel (id,name,type,info,managerid) values(?,?,?,?,?)",
                hotel.getId(), hotel.getName(), hotel.getType(), hotel.getInfo(), hotel.getManagerId());
    }

}
