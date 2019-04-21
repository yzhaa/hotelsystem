package com.yzh.www.serviceImpl;

import com.yzh.www.dao.*;
import com.yzh.www.entity.Hotel;
import com.yzh.www.factory.DaoFactory;
import com.yzh.www.service.AdministratorService;
import com.yzh.www.util.Constant;

import java.util.ArrayList;


public class AdministratorServiceImpl  implements AdministratorService {

    public ArrayList<Hotel> findAllHotel() {
        ArrayList<Hotel> arrayList= DaoFactory.getHotelDao().findAll();
        for(Hotel hotel:arrayList){
            hotel.continueInit();
        }
        return arrayList;
    }

    public int changeHotelInfo(Hotel hotel, int choice){
        HotelDao hd = DaoFactory.getHotelDao();
      int result=judgeHotelInfo(hotel);
        if(result==3) {
            switch (choice) {
                case 1:
                    if(hotel.getManager()==null){
                        return 3;
                    }
                    hd.updata(hotel);
                    return 4;
                case 2:
                   if( removeRoom(hotel.getId())){
                       removeConment(hotel.getId());
                       removeService(hotel.getId());
                       removeMana(hotel.getManagerId());
                       removeHotelAcoont(hotel.getId());
                       hd.delete(hotel.getId());
                       return 5;
                   }
                    return 6;
                case 3:
                    if(hotel.getManager()==null){
                        return 7;
                    }
                    if(isManaHasHotel(hotel.getManagerId())){
                        return 8;
                    }
                    hd.creat(hotel);
                    addHoteltoMana(hotel.getManagerId(), hotel);
                    return 9;
            }
        }
        return result;
    }

    public void addHoteltoMana(int id ,Hotel hotel){
        DaoFactory.getManagerUserDao().addHotel(id, hotel.getId());
    }

    public boolean isManaHasHotel(int managerId){
        return DaoFactory.getManagerUserDao().isHasHotel(managerId);
    }

    private void removeConment(int hotelid){
        DaoFactory.getCommentDao().delete(hotelid);
    }

    public void removeService(int hotelid){
        DaoFactory.getServiceDao().deleteByhotelId(hotelid);
    }

    public boolean removeRoom(int hotelid){
        RoomDao rd = DaoFactory.getRoomDao();
        ArrayList arrayList = rd.findAllEmpty(hotelid);
        ArrayList arrayList1=rd.findByHotelId(hotelid);
        if(arrayList.size()!=arrayList1.size()){
            return false;
        }
        return rd.deleteByHotelId(hotelid);
    }

    private void removeHotelAcoont(int hotelid){
        DaoFactory.getHotelAccountDao().delete(hotelid);
    }


    private int judgeHotelInfo(Hotel hotel){
        if (hotel.getName() == null || hotel.getInfo() == null || hotel.getType() == null ||
                hotel.getName().equals("") || hotel.getInfo().equals("") || hotel.getType().equals("")) {
            return 1;
        }

        int i=0;
        String[] strs = {Constant.FIVESTARS, Constant.FOURSTARS, Constant.THREESTARS, Constant.CHEAPSTARS};
        for(;i<4;i++){
            if(strs[i].equals(hotel.getType())){
                break;
            }
        }
        if(i>=4){
            return 2;
        }
        return 3;
    }

    public void removeMana(int managerId){
        DaoFactory.getManagerUserDao().deleteHotelId(managerId);
    }

}

