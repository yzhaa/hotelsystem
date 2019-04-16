package com.yzh.www.service;


import com.yzh.www.dao.*;
import com.yzh.www.entity.*;
import com.yzh.www.util.MyAlert;

import Storage.MangerStorage;

import java.util.ArrayList;
import java.util.Iterator;

public class ManagerServiceImpl implements ManagerService{
    private RoomDao roomDao;
    private OrderDao orderDao;
    private CommentDao commentDao;
    private UserDao userDao;
    private HotelDao hotelDao;
    private ServiceDao serviceDao;

    public boolean changeRoomInfo(Room room,int choice) {

        if(room==null){
            MyAlert.setAlert("请选择所要删除的目标",0);
            return false;
        }
        roomDao= new RoomDaoImpl();
        switch (choice){
            case 1:
                if(room.getName().equals("")||room.getInfo().equals("")||room.getType().equals("")||room.getPrice()==0){
                    MyAlert.setAlert("请修改完整",0);
                    return false;
                }
                return roomDao.updata(room);
            case 2:
                if(roomDao.isEmpty(room.getId())){
                    MyAlert.setAlert("删除成功",1);
                    return roomDao.delete(room.getId());
                } else {
                    MyAlert.setAlert("该房间有人住",0);
                    return false;
                }
            case 3:
                if(room.getName().equals("")||room.getInfo().equals("")||room.getType().equals("")||room.getPrice()==0){
                    MyAlert.setAlert("请修改完整",0);
                    return false;
                }
                return roomDao.creat(room);
        }
        return false;
    }


    public ArrayList findOrderByHotel() {
        ArrayList arrayList = new ArrayList();
        orderDao= new OrderDaoImpl();
        ArrayList alroom = new BaseServiceImpl().findAllRoom(new MangerStorage().getUser().getHotel().getId());
        for (Iterator ite = alroom.iterator(); ite.hasNext(); ) {
            arrayList.addAll(orderDao.findByRoomId(((Room) ite.next()).getId()));
        }
        for(Iterator ite=arrayList.iterator();ite.hasNext();){
            ((Order) ite.next()).continueInit();
        }
        return arrayList;
    }
    public ArrayList findComment(){
        commentDao = new CommentDaoImpl();
        return commentDao.findAll(new MangerStorage().getUser().getHotel().getId());
    }
    public Manager findManaByAccont(int accont){
        userDao = new ManagerUserDaoImpl();
        return (Manager) userDao.findByAccont(accont);
    }

    public Manager findManaById(int id){
        userDao = new ManagerUserDaoImpl();
        return (Manager) userDao.findByid(id);
    }

    public ArrayList findService(){
        serviceDao =new  ServiceDaoImpl();
        return serviceDao.findAllByHotelId(new MangerStorage().getUser().getHotel().getId());
    }

    public boolean changeService(String name,String price,String contence,int id){
        if(judgeServiceInfo( name, price, contence)){
            serviceDao = new ServiceDaoImpl();
            MyAlert.setAlert("修改成功",1);
            return serviceDao.update(name, Integer.parseInt(price), contence, id);
        }
        return false;
    }

    public boolean deleteService(Service service){
        if(service==null){
            MyAlert.setAlert("请选择要删除的服务",0);
            return false;
        }
        if(!new CusSerDaoImpl().isService(service.getId())){
            serviceDao = new ServiceDaoImpl();
           return serviceDao.delete(service.getId());
        }
        else {
            MyAlert.setAlert("该服务有客户在使用",0);
            return false;
        }
    }

    public boolean judgeServiceInfo(String name,String price,String contence) {
        if(name.equals("")||price.equals("")||contence.equals("")){
            MyAlert.setAlert("请填完整信息", 0);
            return false;
        }
        return true;
    }

    public boolean cheakHotel() {
        Hotel hotel = new MangerStorage().getUser().getHotel();
        if (hotel == null) {
            MyAlert.setAlert("该酒店管理员没有对应酒店\n需登陆管理员进行设置", 0);
            return false;
        }
        return true;
    }


    public boolean addService( String name,String price,String contence){
       if(judgeServiceInfo(name, price, contence)){
           serviceDao = new ServiceDaoImpl();
           MyAlert.setAlert("添加成功",1);
           return serviceDao.insert(name, Integer.parseInt(price), contence,
                   new MangerStorage().getUser().getHotel().getId());
       }
        return false;
    }

}
