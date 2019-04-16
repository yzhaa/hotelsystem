package com.yzh.www.dao;


/**
 *  继承 AccountDaoImpl，该类为酒店账单的存储提供操作
 */
public class HotelAccountDaoI extends AccountDaoImpl {
    public HotelAccountDaoI() {
        setFormName("hotelaccount");
    }
}
