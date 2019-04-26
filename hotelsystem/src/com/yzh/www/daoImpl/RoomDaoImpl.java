package com.yzh.www.daoImpl;

import com.yzh.www.dao.RoomDao;
import com.yzh.www.entity.Room;
import com.yzh.www.factory.DaoFactory;

import java.util.ArrayList;

public class RoomDaoImpl implements RoomDao {
    private BaseDao baseDao = DaoFactory.getBaseDao();

    public ArrayList<Room> findByHotelId(int id){
        return baseDao.getList(Room.class,  "select * from room where hotelid=?",id);
    }

    public Room findById(int id){
        ArrayList<Room> arrayList = baseDao.getList(Room.class,  "select * from room where id=?",id);
        if(arrayList.size()>0){
            return arrayList.get(0);
        }
        return null;
    }

    public ArrayList<Room> findAllEmpty(int hotelid){
        return baseDao.getList(Room.class, "select * from room where customerid=0 and hotelid=?",hotelid);
    }

    public boolean isEmpty(int id){
        ArrayList<Integer> arrayList = baseDao.executeQuery("select customerid from room where id=?",id);
        if(arrayList.size()>0){
            return arrayList.get(0)==0;
        }
        return true;
    }

    public void insertCustomer(int customerId,int roomId){
        baseDao.insert("update room set customerid=? where id=?", customerId, roomId);
    }

    public boolean removeCustomer(int id){
        return baseDao.update("update room set customerid=0 where id=?",id);
    }

    public int findHotelIdById(int id){
      return   baseDao.executeQuery("select hotelid from room where id=?",id).get(0);
    }

    public void updata(Room room){
        baseDao.update( "update room set name=?,type=?,info=?, price =? where id=?",
                room.getName(), room.getType(), room.getInfo(), room.getPrice(), room.getId());
    }

    public boolean delete(int id){
        return baseDao.delete("delete from room where id=?",id);
    }

    public boolean deleteByHotelId(int hotelId){
        return baseDao.delete("delete from room where hotelid=?", hotelId);
    }

    public void creat(Room room) {
        baseDao.insert("insert into room (name,type,info,price,hotelid,customerid ) values(?,?,?,?,?,?)",
                room.getName(), room.getType(), room.getInfo(), room.getPrice(), room.getHotel().getId(),0);
    }
}
