package com.yzh.www.daoImpl;

import com.yzh.www.dao.HotelDao;
import com.yzh.www.entity.Hotel;
import java.util.ArrayList;

public class HotelDaoImpl implements HotelDao {
    private BaseDao baseDao = new BaseDao();

    public ArrayList<Hotel> findByName(String name){
       return baseDao.getList(Hotel.class, null, "select * from hotel where name like '%"+name+"%'");
    }

    public ArrayList<Hotel> findBytype(String type){
        Object[] objects = {type};
        return baseDao.getList(Hotel.class, objects, "select * from hotel where type=?");
    }

    public ArrayList<Hotel> findAll(){
        return baseDao.getList(Hotel.class, null, "select * from hotel");
    }

    public Hotel findById(int id){
        Object[] objects = {id};
        ArrayList<Hotel> arrayList=baseDao.getList(Hotel.class, objects, "select * from hotel where id=?");
        if(arrayList.size()>0){
            return arrayList.get(0);
        }
        return null;
    }

    public void updata(Hotel hotel){
        Object[] object = {hotel};
        baseDao.update("update hotel set name=? ,type=? ,info=?, managerid =?  where id=?", object);
    }

    public boolean delete(int id){
        Object[] object = {id};
        return baseDao.delete("delete from hotel where id=?", object);
    }

    public void creat(Hotel hotel){
        Object[] objects = {hotel.getId(), hotel.getName(), hotel.getType(), hotel.getInfo(), hotel.getManagerId()};
        baseDao.insert("insert into hotel (id,name,type,info,managerid) values(?,?,?,?,?)", objects);
    }

}
