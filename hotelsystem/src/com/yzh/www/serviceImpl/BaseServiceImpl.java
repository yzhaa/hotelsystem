package com.yzh.www.serviceImpl;

import com.yzh.www.dao.*;
import com.yzh.www.entity.*;
import com.yzh.www.factory.DaoFactory;
import com.yzh.www.service.BaseService;
import com.yzh.www.view.CalendarView;
import Storage.CustomerStorage;
import Storage.HotelStorage;
import Storage.MangerStorage;

import java.util.ArrayList;
import java.util.Date;

public class BaseServiceImpl implements BaseService {

    private AccountDao accountDao;

    public Hotel findHotel(int id){
        return DaoFactory.getHotelDao().findById(id);
    }

    public ArrayList<Room> findAllRoom(int hotelId){
        ArrayList<Room> arrayList = DaoFactory.getRoomDao().findByHotelId(hotelId);
        for (Room room:arrayList ) {
            room.continueInit();
        }
        return arrayList;
    }


    public void creatAccountOut(CalendarView cv){
        int price=0;
        int hotelId = new HotelStorage().getHotel().getId();
        int customerid = new CustomerStorage().getUser().getId();
        ServiceDao serviceDao = DaoFactory.getServiceDao();
        for ( String service:cv.getSelectStr()) {
            if(service!=null) {
                price += serviceDao.findOneByName(service, "price") * Integer.parseInt(cv.getDuration());
            }
        }
        accountDao = DaoFactory.getCustomerAccountDao();
        price += cv.getRoomPrice() * Integer.parseInt(cv.getDuration());
        accountDao.insert(hotelId,cv.getRoomId(),customerid
                ,new java.sql.Date(new Date().getTime()),(-1)*price);
        accountDao = DaoFactory.getHotelAccountDao();
        accountDao.insert(hotelId, cv.getRoomId(),
                customerid, new java.sql.Date(new Date().getTime()), price);
    }

    public void creatAccontin(Order order){
        accountDao = DaoFactory.getCustomerAccountDao();
        accountDao.insert(order.getHotel().getId(), order.getRoom().getId(), order.getCustomer().getId(),
                new java.sql.Date(new Date().getTime()), order.getPrice());
        accountDao = DaoFactory.getHotelAccountDao();
        accountDao.insert(order.getHotel().getId(), order.getRoom().getId(), order.getCustomer().getId(),
                new java.sql.Date(new Date().getTime()),  -1* order.getPrice());
    }

    public ArrayList<Account> findAccount(int choice){
        if(choice==1){
            accountDao = DaoFactory.getCustomerAccountDao();
            return accountDao.findByCustomer(new CustomerStorage().getUser().getId());
        }
        else if(choice==2){
            accountDao = DaoFactory.getHotelAccountDao();
          return   accountDao.findByHotel(new MangerStorage().getUser().getHotel().getId());
        }
        return null;
    }
}
