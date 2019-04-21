package com.yzh.www.daoImpl;

import com.yzh.www.dao.RoomDao;
import com.yzh.www.entity.Room;
import java.util.ArrayList;

public class RoomDaoImpl implements RoomDao {
    private BaseDao baseDao = new BaseDao();

    public ArrayList<Room> findByHotelId(int id){
        Object[] objects = {id};
        return baseDao.getList(Room.class, objects, "select * from room where hotelid=?");
    }

    public Room findById(int id){
        Object[] objects = {id};
        ArrayList<Room> arrayList = baseDao.getList(Room.class, objects, "select * from room where id=?");
        if(arrayList.size()>0){
            return arrayList.get(0);
        }
        return null;
    }

    public ArrayList<Room> findAllEmpty(int hotelid){
        Object[] objects = {hotelid};
        return baseDao.getList(Room.class, objects, "select * from room where customerid=0 and hotelid=?");
    }

    public boolean isEmpty(int id){
        ArrayList<Integer> arrayList = baseDao.executeQuery("select customerid from room where id='" + id + "'");
        if(arrayList.size()>0){
            return arrayList.get(0)==0;
        }
        return true;
    }

    public void insertCustomer(int customerId,int roomId){
        Object[] objects = {customerId, roomId};
        baseDao.insert("update room set customerid=? where id=?", objects);
    }

    public boolean removeCustomer(int id){
        Object[] objects = {id};
        return baseDao.update("update room set customerid=0 where id=?",objects);
    }

    public int findHotelIdById(int id){
      return   baseDao.executeQuery("select hotelid from room where id='" + id + "'").get(0);
    }

    public void updata(Room room){
        Object[] objects = {room.getName(), room.getType(), room.getInfo(), room.getPrice(), room.getId()};
        baseDao.update( "update room set name=?,type=?,info=?, price =? where id=?",objects);
    }

    public boolean delete(int id){
        Object[] objects = {id};
        return baseDao.delete("delete from room where id=?",objects);
    }

    public boolean deleteByHotelId(int hotelId){
        Object[] objects = {hotelId};
        return baseDao.delete("delete from room where hotelid=?", objects);
    }

    public void creat(Room room) {
        Object[] objects = {room.getName(), room.getType(), room.getInfo(), room.getPrice(), room.getHotel().getId(),0};
        baseDao.insert("insert into room (name,type,info,price,hotelid,customerid ) values(?,?,?,?,?,?)",objects);
    }
}
