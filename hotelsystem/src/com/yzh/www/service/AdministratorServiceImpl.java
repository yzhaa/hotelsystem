package com.yzh.www.service;

import com.yzh.www.dao.*;
import com.yzh.www.entity.Hotel;
import com.yzh.www.util.Constant;
import com.yzh.www.util.MyAlert;

import java.util.ArrayList;
import java.util.Iterator;

public class AdministratorServiceImpl  implements  AdministratorService{
    private HotelDao hotelDao;
    private AccountDao accountDao;
    private ServiceDao serviceDao;
    private RoomDao roomDao;
    private CommentDao commentDao;

    public ArrayList findAllHotel() {
        ArrayList arrayList=new HotelDaoImpl().findAll();
        for(Iterator ite=arrayList.iterator();ite.hasNext();){
            ((Hotel) ite.next()).continueInit();
        }
        return arrayList;
    }

    public boolean changeHotelInfo(Hotel hotel, int choice){
       hotelDao = new HotelDaoImpl();
        if(judgeHotelInfo(hotel)) {
            switch (choice) {
                case 1:
                    if(hotel.getManager()==null){
                        MyAlert.setAlert("无此管理员账户",0);
                        return false;
                    }
                    return hotelDao.updata(hotel);
                case 2:
                   if( removeRoom(hotel.getId())){
                       removeConment(hotel.getId());
                       removeService(hotel.getId());
                       removeMana(hotel.getManagerId());
                       removeHotelAcoont(hotel.getId());
                       return hotelDao.delete(hotel.getId());
                   }break;
                case 3:
                    if(hotel.getManager()==null){
                        MyAlert.setAlert("无此管理员", 0);
                        return false;
                    }
                    if(isManaHasHotel(hotel.getManagerId())){
                        MyAlert.setAlert("该管理员有酒店了",0);
                        return false;
                    }
                    hotelDao.creat(hotel);
                   return addHoteltoMana(hotel.getManagerId(), hotel);
            }
        }
        return false;
    }

    public boolean addHoteltoMana(int id ,Hotel hotel){
      return   new ManagerUserDaoImpl().addHotel(id, hotel.getId());
    }

    public boolean isManaHasHotel(int managerId){
        return new ManagerUserDaoImpl().isHasHotel(managerId);
    }

    public boolean removeConment(int hotelid){
        commentDao = new CommentDaoImpl();
       return commentDao.delete(hotelid);
    }

    public boolean removeService(int hotelid){
        serviceDao = new ServiceDaoImpl();
        return serviceDao.deleteByhotelId(hotelid);
    }

    public boolean removeRoom(int hotelid){
        roomDao = new RoomDaoImpl();
        ArrayList arrayList = roomDao.findAllEmpty(hotelid);
        ArrayList arrayList1=roomDao.findByHotelId(hotelid);
        if(arrayList.size()!=arrayList1.size()){
            MyAlert.setAlert("该酒店还有人住\n请先删除顾客的订单",0);
            return false;
        }
        return roomDao.deleteByHotelId(hotelid);
    }

    public boolean removeHotelAcoont(int hotelid){
        accountDao = new HotelAccountDaoI();
       return  ((HotelAccountDaoI) accountDao).delete(hotelid);
    }


    public boolean judgeHotelInfo(Hotel hotel){
        if (hotel.getName() == null || hotel.getInfo() == null || hotel.getType() == null ||
                hotel.getName().equals("") || hotel.getInfo().equals("") || hotel.getType().equals("")) {
            MyAlert.setAlert("请填入完整信息并且酒店类型需为：\n" + Constant.FIVESTARS
                    + "\\" + Constant.FOURSTARS + "\\" + Constant.THREESTARS + "\\" + Constant.CHEAPSTARS, 0);
            return false;
        }

        int i=0;
        String[] strs = {Constant.FIVESTARS, Constant.FOURSTARS, Constant.THREESTARS, Constant.CHEAPSTARS};
        for(;i<4;i++){
            if(strs[i].equals(hotel.getType())){
                break;
            }
        }
        if(i>=4){
            MyAlert.setAlert("酒店类型需为：\n" + Constant.FIVESTARS + "\\" + Constant.FOURSTARS + "\\" +
                    Constant.THREESTARS + "\\" + Constant.CHEAPSTARS, 0);
            return false;
        }
        return true;
    }

    public boolean removeMana(int managerId){
       return new ManagerUserDaoImpl().deleteHotelId(managerId);
    }

}

