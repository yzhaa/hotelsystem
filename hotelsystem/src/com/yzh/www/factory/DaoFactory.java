package com.yzh.www.factory;

import com.yzh.www.dao.*;
import com.yzh.www.daoImpl.*;

public class DaoFactory {
    private static CustomerAccountDao customerAccountDao;
    private static HotelAccountDaoImpl hotelAccountDao;
    private static CommentDao commentDao;
    private static CusSerDao cusSerDao;
    private static AdministratorUserDaoImpl administratorUserDao;
    private static CustomerUserDaoImpl customerUserDao;
    private static ManagerUserDaoImpl managerUserDao;
    private static HotelDao hotelDao;
    private static OrderDao orderDao;
    private static RoomDao roomDao;
    private static ServiceDao serviceDao;
    private static BaseDao baseDao;


    public static CustomerAccountDao getCustomerAccountDao(){
        if (customerAccountDao == null) {
            customerAccountDao = new CustomerAccountDao();
        }
        return customerAccountDao;
    }

    public static HotelAccountDaoImpl getHotelAccountDao(){
        if (hotelAccountDao == null) {
            hotelAccountDao = new HotelAccountDaoImpl();
        }
        return hotelAccountDao;
    }

    public static AdministratorUserDaoImpl getAdministratorUserDao(){
        if (administratorUserDao == null) {

            administratorUserDao = new AdministratorUserDaoImpl();
        }
        return administratorUserDao;
    }

    public static CommentDao getCommentDao(){
        if (commentDao == null) {
            commentDao = new CommentDaoImpl();
        }
        return commentDao;
    }

    public static CusSerDao getCusSerDao(){
        if (cusSerDao == null) {
            cusSerDao = new CusSerDaoImpl();
        }
        return cusSerDao;
    }

    public static CustomerUserDaoImpl getCustomerUserDao() {
        if (customerUserDao == null) {
            customerUserDao = new CustomerUserDaoImpl();
        }
        return customerUserDao;
    }

    public static ManagerUserDaoImpl getManagerUserDao(){
        if (managerUserDao == null) {
            managerUserDao = new ManagerUserDaoImpl();
        }
        return managerUserDao;
    }

    public static HotelDao getHotelDao(){
        if (hotelDao == null) {
            hotelDao = new HotelDaoImpl();
        }
        return hotelDao;
    }

    public static OrderDao getOrderDao(){
        if (orderDao == null) {
            orderDao = new OrderDaoImpl();
        }
        return orderDao;
    }

    public static RoomDao getRoomDao(){
        if (roomDao == null) {
            roomDao = new RoomDaoImpl();
        }
        return roomDao;
    }
    public static ServiceDao getServiceDao(){
        if (serviceDao == null) {
            serviceDao = new ServiceDaoImpl();
        }
        return serviceDao;
    }

    public static BaseDao getBaseDao(){
        if (baseDao == null) {
            baseDao = new BaseDao();
        }
        return baseDao;
    }

}
