package com.yzh.www.service;

import com.yzh.www.dao.*;
import com.yzh.www.entity.Hotel;
import com.yzh.www.entity.Order;
import com.yzh.www.entity.Room;
import com.yzh.www.view.CalendarView;
import Storage.CustomerStorage;
import Storage.HotelStorage;
import Storage.MangerStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class BaseServiceImpl implements BaseService {
    private HotelDao hotelDao;
    private RoomDao roomDao;
    private ServiceDao serviceDao;
    private AccountDao accountDao;

    public Hotel findHotel(int id){
        hotelDao = new HotelDaoImpl();
        return hotelDao.findById(id);
    }

    public ArrayList<Room> findAllRoom(int hotelId){
        roomDao = new RoomDaoImpl();
        ArrayList arrayList = roomDao.findByHotelId(hotelId);
        for (Iterator ite = arrayList.iterator(); ite.hasNext(); ) {
            ((Room) ite.next()).continueInit();
        }
        return arrayList;
    }


    public void creatAccountOut(CalendarView cv){
        int price=0;
        int hotelId = new HotelStorage().getHotel().getId();
        int customerid = new CustomerStorage().getUser().getId();
        serviceDao = new ServiceDaoImpl();
        for ( String service:cv.getSelectStr()) {
            if(service!=null) {
                price += serviceDao.findOneByName(service, "price") * Integer.parseInt(cv.getDuration());
            }
        }
        accountDao = new CustomerAccountDao();
        price += cv.getRoomPrice() * Integer.parseInt(cv.getDuration());
        accountDao.insert(hotelId,cv.getRoomId(),customerid
                ,new java.sql.Date(new Date().getTime()),(-1)*price);
        accountDao = new HotelAccountDaoI();
        accountDao.insert(hotelId, cv.getRoomId(),
                customerid, new java.sql.Date(new Date().getTime()), price);
    }

    public void creatAccontin(Order order){
        accountDao = new CustomerAccountDao();
        accountDao.insert(order.getHotel().getId(), order.getRoom().getId(), order.getCustomer().getId(),
                new java.sql.Date(new Date().getTime()), order.getPrice());
        accountDao = new HotelAccountDaoI();
        accountDao.insert(order.getHotel().getId(), order.getRoom().getId(), order.getCustomer().getId(),
                new java.sql.Date(new Date().getTime()),  -1* order.getPrice());
    }

    public ArrayList findAccount(int choice){
        if(choice==1){
            accountDao = new CustomerAccountDao();
            return accountDao.findByCustomer(new CustomerStorage().getUser().getId());
        }
        else if(choice==2){
            accountDao = new HotelAccountDaoI();
          return   accountDao.findByHotel(new MangerStorage().getUser().getHotel().getId());
        }
        return null;
    }
}
