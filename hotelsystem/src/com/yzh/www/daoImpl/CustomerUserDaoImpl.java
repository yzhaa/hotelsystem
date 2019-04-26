package com.yzh.www.daoImpl;

import com.yzh.www.dao.UserDao;
import com.yzh.www.entity.Customer;
import com.yzh.www.entity.User;
import com.yzh.www.factory.DaoFactory;

import java.util.ArrayList;


public class CustomerUserDaoImpl implements UserDao {
    private BaseDao baseDao = DaoFactory.getBaseDao();

    public CustomerUserDaoImpl() {
        super();
    }

    @Override
    public boolean add(User user)  {
        return baseDao.insert("insert into customer(accont,password) values(?,?)", user.getAccont(), user.getPassword());
    }

    @Override
    public boolean update(User user)  {
        return baseDao.update("update customer set username=? ,password=?,phonenumber=? ,idcard=? where id=?",
                user.getUserName(), user.getPassword(), user.getPhoneNumber(), user.getIdCard(), user.getId());
    }


    @Override
    public User findByAccont(int accont)  {
        ArrayList<Customer> arrayList = baseDao.getList(Customer.class, "select * from customer where accont=?",accont);
        if(arrayList.size()>0){
            return arrayList.get(0);
        }
        return null;
    }

    public Customer findByid(int id)  {
        return baseDao.getList(Customer.class, "select * from customer where id=?",id).get(0);
    }

}

