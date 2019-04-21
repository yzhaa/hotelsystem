package com.yzh.www.daoImpl;

import com.yzh.www.dao.UserDao;
import com.yzh.www.entity.Customer;
import com.yzh.www.entity.User;

import java.util.ArrayList;


public class CustomerUserDaoImpl implements UserDao {
    private BaseDao baseDao = new BaseDao();

    public CustomerUserDaoImpl() {
        super();
    }

    @Override
    public boolean add(User user)  {
        Object[] objects = {user.getAccont(), user.getPassword()};
        return baseDao.insert("insert into customer(accont,password) values(?,?)", objects);
    }

    @Override
    public boolean update(User user)  {
        Object[] objects = {user.getUserName(), user.getPassword(), user.getPhoneNumber(), user.getIdCard(), user.getId()};
        return baseDao.update("update customer set username=? ,password=?,phonenumber=? ,idcard=? where id=?",objects);
    }


    @Override
    public User findByAccont(int accont)  {
        Object[] objects = {accont};
        ArrayList<Customer> arrayList = baseDao.getList(Customer.class, objects, "select * from customer where accont=?");
        if(arrayList.size()>0){
            return arrayList.get(0);
        }
        return null;
    }

    public Customer findByid(int id)  {
        Object[] objects = {id};
        return baseDao.getList(Customer.class, objects, "select * from customer where id=?").get(0);
    }

}

