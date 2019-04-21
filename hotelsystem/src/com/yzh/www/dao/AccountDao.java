package com.yzh.www.dao;

import com.yzh.www.entity.Account;

import java.sql.Date;
import java.util.ArrayList;

/**
 * 对账单的增删查改
 */
public interface AccountDao {

      void setFormName(String formName);

    /**
     * 通过顾客找账单
     * @param customerId 顾客的id
     * @return  返回包含账单的集合
     */
     ArrayList<Account> findByCustomer(int customerId);

    /**
     * 通过酒店找账单
     * @param hotelId 酒店的id
     * @return 返回包含账单的集合
     */
     ArrayList<Account> findByHotel(int hotelId);

    /**
     *  添加一条账单
     * @param hotelId 酒店的id
     * @param roomId  房间的id
     * @param customerId  顾客的id
     * @param date  创建账单的时间
     * @param money  订单的总钱数
     * @return  返回创建是否成功的布尔值
     */

     Boolean insert(int hotelId, int roomId, int customerId, Date date, int money);

    /**
     * 通过顾客的id和房间的id删除一个账单
     * @param roomId  房间的id
     * @param customerId  顾客的id
     * @return  返回是否删除的布尔值
     */
     boolean delete(int roomId,int customerId);

}
