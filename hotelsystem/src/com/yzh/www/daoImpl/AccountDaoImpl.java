package com.yzh.www.daoImpl;

import com.yzh.www.dao.AccountDao;
import com.yzh.www.entity.Account;
import java.sql.*;
import java.util.ArrayList;

/**
 * 对账单的增删查改
 */
public class AccountDaoImpl implements AccountDao {
    private  String formName;
    private BaseDao baseDao = new BaseDao();

    /**
     * 存储账单的名 ，因为有管理员和顾客账单
     */

    public  void setFormName(String formName){
        this.formName = formName;
    }

    /**
     * 通过顾客找账单
     * @param customerId 顾客的id
     * @return  返回包含账单的集合
     */
    public ArrayList<Account> findByCustomer(int customerId){
        Object[] objects = {customerId};
        return baseDao.getList(Account.class, objects, "select * from " + formName + " where customerid =?");
    }

    /**
     * 通过酒店找账单
     * @param hotelId 酒店的id
     * @return 返回包含账单的集合
     */
    public ArrayList<Account> findByHotel(int hotelId){
        Object[] objects = {hotelId};
        return baseDao.getList(Account.class, objects, "select * from " + formName + " where hotelid=?");
    }

    /**
     *  添加一条账单
     * @param hotelId 酒店的id
     * @param roomId  房间的id
     * @param customerId  顾客的id
     * @param date  创建账单的时间
     * @param money  订单的总钱数
     * @return  返回创建是否成功的布尔值
     */

    public Boolean insert(int hotelId, int roomId, int customerId, Date date, int money) {
        Object[] objects = {hotelId, roomId, customerId,date, money};
        return baseDao.insert("insert into " + formName + " (hotelid,roomid ,customerid ,date,money) " +
                "values(?,?,?,?,?)", objects);
    }

    /**
     * 通过顾客的id和房间的id删除一个账单
     * @param roomId  房间的id
     * @param customerId  顾客的id
     * @return  返回是否删除的布尔值
     */
    public boolean delete(int roomId,int customerId){
        Object[] objects = {roomId, customerId};
        return baseDao.delete("delete from " + formName + " where roomid=? and customerid=?", objects);
    }

    public boolean delete(int hotelid) {
        Object[] objects = {hotelid};
        return baseDao.delete("delete from " + formName + " where hotelid=?",objects);
    }
}
