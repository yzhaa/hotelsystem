package com.yzh.www.daoImpl;

import com.yzh.www.dao.UserDao;
import com.yzh.www.entity.Manager;
import com.yzh.www.entity.User;

import java.util.ArrayList;

public class ManagerUserDaoImpl implements UserDao {
    private BaseDao baseDao = new BaseDao();

    public ManagerUserDaoImpl() {
        super();
    }


    public void deleteHotelId(int id) {
        Object[] objects = {id};
        baseDao.delete("update manager set hotelid=0 where id=?", objects);
    }

    @Override
    public boolean add(User user) {
        Object[] objects = {user.getAccont(), user.getPassword()};
        return baseDao.insert("insert into manager(accont,password,hotelid) values(?,?,0)", objects);
    }


    public void addHotel(int id,int hotelId) {
        Object[] objects = {hotelId, id};
        baseDao.update("update  manager set hotelid=? where id=?",objects);
    }


    @Override
    public boolean update(User user) {
        Object[] objects = {user.getUserName(), user.getIdCard(), user.getPassword(), user.getPhoneNumber(),user.getId()};
        return baseDao.update("update manager set username=? , idcard=? ,password=?, phonenumber=? where id=?", objects);
    }


    @Override
    public User findByAccont(int accont) {
        Object[] objects = {accont};
        ArrayList<Manager> arrayList = baseDao.getList(Manager.class, objects, "select * from manager where accont=?");
        if(arrayList.size()!=0){
            return arrayList.get(0);
        }
        return null;
    }

    public User findByid(int id) {
        Object[] objects = {id};
        return baseDao.getList(Manager.class, objects, "select * from manager where id=?").get(0);
    }

    public boolean isHasHotel(int id){
       return baseDao.executeQuery("select hotelid from manager where id='"+id+"'").get(0)!=0;
    }

}
