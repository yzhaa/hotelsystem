package com.yzh.www.daoImpl;

import com.yzh.www.dao.UserDao;
import com.yzh.www.entity.Administrator;
import com.yzh.www.entity.User;
import com.yzh.www.factory.DaoFactory;

import java.util.ArrayList;


public class AdministratorUserDaoImpl implements UserDao {
    private BaseDao baseDao = DaoFactory.getBaseDao();
    @Override
    public boolean add(User user) {
        return false;
    }


    @Override
    public User findByAccont(int accont)  {
        ArrayList<Administrator> arrayList = baseDao.getList(Administrator.class,  "select * from administrator where" +
                " accont=?",accont);
        if(arrayList.size()>0){
            return arrayList.get(0);
        }
        return null;
    }
    @Override
    public boolean update(User user)  {
        return baseDao.update("update administrator set username=?,idcard=? password=?,phonenumber=?  where id=?",
                user.getUserName(), user.getIdCard(), user.getPassword(), user.getPhoneNumber(), user.getId());
    }

    @Override
    public User findByid(int id) {
        return null;
    }
}
