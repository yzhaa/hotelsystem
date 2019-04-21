package com.yzh.www.daoImpl;


/**
 * 继承 AccountDaoImpl，该类为顾客账单的存储提供操作
 */
public class CustomerAccountDao extends AccountDaoImpl {
    public CustomerAccountDao() {
        setFormName("customeraccount");
    }
}
