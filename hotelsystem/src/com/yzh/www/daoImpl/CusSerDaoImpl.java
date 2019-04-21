package com.yzh.www.daoImpl;

import com.yzh.www.dao.CusSerDao;
import java.util.ArrayList;

public class CusSerDaoImpl  implements CusSerDao {
    private BaseDao baseDao = new BaseDao();

    public boolean isService(int serviceId){
      return   baseDao.executeQuery("select serviceid from cus_ser where serviceid='" + serviceId + "'").size()>0;
    }

    public ArrayList findAll(int roomid,int customerid){
        return baseDao.executeQuery("select serviceid from cus_ser where roomid='" + roomid + "' and customerid='" + customerid + "'");
    }

    public boolean insert(int customerId,int roomId,int serviceId ){
        Object[] objects = {customerId, roomId, serviceId};
        return baseDao.insert("insert into cus_ser (customerid ,roomid ,serviceid ) values(?,?,?)",objects);
    }

    public boolean delete(int id){
        Object[] objects = {id};
        return baseDao.delete("delete from cus_ser where id=?", objects);
    }

    public boolean delete(int roomId,int customerId) {
        Object[] objects = {roomId, customerId};
        return baseDao.delete("delete from cus_ser where roomid=? and customerid=?",objects);
    }
}
