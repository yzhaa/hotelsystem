package com.yzh.www.entity;

import java.util.Date;

public class CustomerAccount extends Account {
    public CustomerAccount(int id, int hotelId, int roomid, int customerId, Date date, int money) {
        super(id, hotelId, roomid, customerId, date, money);
    }
}
