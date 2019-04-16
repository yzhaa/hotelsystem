package com.yzh.www.entity;

import java.util.Date;

/**
 * 酒店账单对应的实体类
 */
public class HotelAccount extends  Account {

    public HotelAccount(int id, int hotelId, int roomid, int customerId, Date date, int money) {
        super(id, hotelId, roomid, customerId, date, money);
    }

}
