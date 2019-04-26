package com.yzh.www.daoImpl;

import com.yzh.www.dao.CusSerDao;
import com.yzh.www.factory.DaoFactory;

import java.util.ArrayList;

public class CusSerDaoImpl  implements CusSerDao {
    private BaseDao baseDao = DaoFactory.getBaseDao();

    public boolean isService(int serviceId){
      return   baseDao.executeQuery("select serviceid from cus_ser where serviceid=?",serviceId).size()>0;
    }

    public ArrayList findAll(int roomid,int customerid){
        return baseDao.executeQuery("select serviceid from cus_ser where roomid=? and customerid=?",roomid,customerid);
    }

    public boolean insert(int customerId,int roomId,int serviceId ){
        return baseDao.insert("insert into cus_ser (customerid ,roomid ,serviceid ) values(?,?,?)",customerId,roomId,serviceId);
    }

    public boolean delete(int id){
        return baseDao.delete("delete from cus_ser where id=?", id);
    }

    public boolean delete(int roomId,int customerId) {
        return baseDao.delete("delete from cus_ser where roomid=? and customerid=?",roomId,customerId);
    }
}
