package com.yzh.www.daoImpl;

import com.yzh.www.dao.UserDao;
import com.yzh.www.entity.Manager;
import com.yzh.www.entity.User;
import com.yzh.www.factory.DaoFactory;

import java.util.ArrayList;

public class ManagerUserDaoImpl implements UserDao {
    private BaseDao baseDao = DaoFactory.getBaseDao();

    public ManagerUserDaoImpl() {
        super();
    }


    public void deleteHotelId(int id) {
        baseDao.delete("update manager set hotelid=0 where id=?", id);
    }

    @Override
    public boolean add(User user) {
        return baseDao.insert("insert into manager(accont,password,hotelid) values(?,?,0)", user.getAccont(),user.getPassword());
    }


    public void addHotel(int id,int hotelId) {
        baseDao.update("update  manager set hotelid=? where id=?", hotelId, id);
    }


    @Override
    public boolean update(User user) {
        return baseDao.update("update manager set username=? , idcard=? ,password=?, phonenumber=? where id=?",
                user.getUserName(), user.getIdCard(), user.getPassword(), user.getPhoneNumber(), user.getId());
    }


    @Override
    public User findByAccont(int accont) {
        ArrayList<Manager> arrayList = baseDao.getList(Manager.class,"select * from manager where accont=?" ,accont );
        if(arrayList.size()!=0){
            return arrayList.get(0);
        }
        return null;
    }

    public User findByid(int id) {
        return baseDao.getList(Manager.class, "select * from manager where id=?",id ).get(0);
    }

    public boolean isHasHotel(int id){
       return baseDao.executeQuery("select hotelid from manager where id=?",id).get(0)!=0;
    }

}
