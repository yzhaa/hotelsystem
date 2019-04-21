package com.yzh.www.serviceImpl;


import com.yzh.www.dao.*;
import com.yzh.www.dao.UserDao;
import com.yzh.www.daoImpl.*;
import com.yzh.www.entity.*;

import Storage.MangerStorage;
import com.yzh.www.factory.DaoFactory;
import com.yzh.www.factory.ServiceFactory;
import com.yzh.www.service.ManagerService;

import java.util.ArrayList;

public class ManagerServiceImpl implements ManagerService {
    private UserDao userDao;
    private ServiceDao serviceDao;

    public int changeRoomInfo(Room room,int choice) {
        RoomDao roomDao = DaoFactory.getRoomDao();
        switch (choice){
            case 1:
                if(room.getName().equals("")||room.getInfo().equals("")||room.getType().equals("")||room.getPrice()==0){
                    return 1;
                }
                roomDao.updata(room);break;
            case 2:
                if(roomDao.isEmpty(room.getId())){
                    roomDao.delete(room.getId());
                    return 2;
                } else {
                    return 3;
                }
            case 3:
                if(room.getName().equals("")||room.getInfo().equals("")||room.getType().equals("")||room.getPrice()==0){
                    return 4;
                }
                 roomDao.creat(room);break;
        }
        return 0;
    }


    public ArrayList<Order> findOrderByHotel() {
        ArrayList<Order> arrayList = new ArrayList<>();
        OrderDao orderDao = DaoFactory.getOrderDao();
        ArrayList<Room> alroom = ServiceFactory.getBaseService().findAllRoom(new MangerStorage().getUser().getHotel().getId());
        for (Room room:alroom ) {
            arrayList.addAll(orderDao.findByRoomId((room.getId())));
        }
        for(Order order:arrayList){
            order.continueInit();
        }
        return arrayList;
    }
    public ArrayList<Comment> findComment(){
        CommentDao commentDao = DaoFactory.getCommentDao();
        return commentDao.findAll(new MangerStorage().getUser().getHotel().getId());
    }
    public Manager findManaByAccont(int accont){
        userDao = DaoFactory.getManagerUserDao();
        return (Manager) userDao.findByAccont(accont);
    }

    public Manager findManaById(int id){
        userDao = DaoFactory.getManagerUserDao();
        return (Manager) userDao.findByid(id);
    }

    public ArrayList findService(){
        serviceDao = DaoFactory.getServiceDao();
        return serviceDao.findAllByHotelId(new MangerStorage().getUser().getHotel().getId());
    }

    public boolean changeService(String name,String price,String contence,int id){
        if(judgeServiceInfo( name, price, contence)){
            serviceDao = DaoFactory.getServiceDao();
            return serviceDao.update(name, Integer.parseInt(price), contence, id);
        }
        return false;
    }

    public int deleteService(Service service){
        if(service==null){
            return 0;
        }
        if(!new CusSerDaoImpl().isService(service.getId())){
            serviceDao = DaoFactory.getServiceDao();
           serviceDao.delete(service.getId());
           return 1;
        }
        else {
            return 2;
        }
    }

    public boolean judgeServiceInfo(String name,String price,String contence) {
        return !(name.equals("")||price.equals("")||contence.equals(""));
    }

    public boolean cheakHotel() {
        Hotel hotel = new MangerStorage().getUser().getHotel();
        return !(hotel == null);
    }


    public boolean addService( String name,String price,String contence){
       if(judgeServiceInfo(name, price, contence)){
           serviceDao = DaoFactory.getServiceDao();
           return serviceDao.insert(name, Long.parseLong(price), contence,
                   new MangerStorage().getUser().getHotel().getId());
       }
        return false;
    }

}
