package com.yzh.www.serviceImpl;

import com.yzh.www.dao.*;
import com.yzh.www.daoImpl.CusSerDaoImpl;
import com.yzh.www.daoImpl.RoomDaoImpl;
import com.yzh.www.entity.*;
import com.yzh.www.factory.DaoFactory;
import com.yzh.www.service.CustomService;
import com.yzh.www.util.Constant;
import Storage.CustomerStorage;
import Storage.HotelStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class CustomServiceImpl implements CustomService {

    private HotelDao hotelDao;
    private RoomDao roomDao;
    private ServiceDao serviceDao;
    private CusSerDao cusSerDao;
    private CommentDao commentDao;
    private OrderDao orderDao;

    public ArrayList<Hotel> findHotel(String choice, String hotelName) {
        hotelDao = DaoFactory.getHotelDao();
        ArrayList<Hotel> al;
        if (hotelName.equals("")) {
            al = hotelDao.findBytype(choice);
        } else {
            ArrayList<Hotel> a2 = hotelDao.findByName(hotelName);
            al = new ArrayList<>(a2);
            a2.removeAll(hotelDao.findBytype(choice));
            al.removeAll(a2);
        }
        return al;
    }

    public ArrayList<Hotel> findHotel(String hotelName) {
        hotelDao = DaoFactory.getHotelDao();
        return hotelDao.findByName(hotelName);
    }


    public Hotel findHotelByRoom(int id) {
        hotelDao = DaoFactory.getHotelDao();
        return hotelDao.findById(new RoomDaoImpl().findHotelIdById(id));
    }

    public ArrayList<Room> findEnptyRoom(int hotelId) {
        roomDao = DaoFactory.getRoomDao();
        ArrayList<Room> arrayList = roomDao.findAllEmpty(hotelId);
        for (Room room:arrayList) {
            room.continueInit();
        }
        return arrayList;
    }

    public Room findRoom(int roomId) {
        roomDao = DaoFactory.getRoomDao();
        return roomDao.findById(roomId);
    }

    public ArrayList<Service> findAllService(int hotelId) {
        serviceDao = DaoFactory.getServiceDao();
        return serviceDao.findAllByHotelId(hotelId);
    }

    public Service findService(int id) {
        serviceDao = DaoFactory.getServiceDao();
        return serviceDao.findById(id);
    }


    public String[] getServiceNames() {
        ArrayList arrayList = findAllService(new HotelStorage().getHotel().getId());
        String[] serviceNames = new String[arrayList.size()];
        int i = 0;
        for (Iterator ite = arrayList.iterator(); ite.hasNext(); i++) {
            serviceNames[i] = ite.next().toString();
        }
        return serviceNames;
    }


    public void setRoomState(int roomId) {
        roomDao = DaoFactory.getRoomDao();
        roomDao.insertCustomer(new CustomerStorage().getUser().getId(), roomId);
    }


    public Customer findCustomerById(int id) {
        return DaoFactory.getCustomerUserDao().findByid(id);
    }

    public boolean removeCustomer(Order order) {
        if (order != null) {
            roomDao = DaoFactory.getRoomDao();
            return roomDao.removeCustomer(order.getRoom().getId());
        }
        return false;
    }

    public void creatOrder(Date date, String duration, int roomId, String[] serviceStr) {
        orderDao = DaoFactory.getOrderDao();
        int customerId = new CustomerStorage().getUser().getId();
        long sdf = date.getTime();
        orderDao.insertOrder(customerId, roomId, new java.sql.Date(sdf),
                new java.sql.Date(sdf + Integer.parseInt(duration) * Constant.DAYTIME));
        creatCusSer(customerId, roomId, serviceStr);
    }

    public int creatComment(String contence, String point, Order order) {
        if (order == null) {
            return 1;
        } else if (point.equals("")) {
            return 2;
        } else {
            commentDao = DaoFactory.getCommentDao();
            commentDao.insert(contence, Integer.parseInt(point), order.getHotel().getId());
            return 3;
        }
    }

    public void creatCusSer(int customerId, int roomId, String[] serviceStr) {
        serviceDao = DaoFactory.getServiceDao();
        cusSerDao = DaoFactory.getCusSerDao();
        for (String serviceName : serviceStr) {
            if (serviceName != null) {
                cusSerDao.insert(customerId, roomId, serviceDao.findOneByName(serviceName, "id"));
            }
        }
    }


    public void deleteCusSer(int roomId, int customerId) {
        cusSerDao = DaoFactory.getCusSerDao();
        cusSerDao.delete(roomId, customerId);
    }


    public ArrayList<String> creatServiceInfo(int hotelId) {
        serviceDao = DaoFactory.getServiceDao();
        StringBuilder sb = new StringBuilder();
        ArrayList<Service> al = serviceDao.findAllByHotelId(hotelId);
        ArrayList<String> arrayList = new ArrayList<>();
        for (Service service : al) {
            sb.append("服务名称" + service.getName() + "\t价格：" + service.getPrice() + "\n内容：" +
                    service.getContent() + "\n\n");
        }
        arrayList.add(sb.toString());
        return arrayList;
    }

    public ArrayList<String> creatCommentInfo(int hotelId) {
        commentDao = DaoFactory.getCommentDao();
        StringBuilder sb = new StringBuilder();
        ArrayList<Comment> al = commentDao.findAll(hotelId);
        ArrayList<String> arrayList = new ArrayList<>();
        for (Comment comment:al ) {
            sb.append(comment.toString());
        }
        arrayList.add(sb.toString());
        return arrayList;
    }

    public ArrayList findCusSer(int roomId, int customerId) {
        return new CusSerDaoImpl().findAll(roomId, customerId);
    }

    public void deleteOrder(int id) {
        orderDao = DaoFactory.getOrderDao();
        orderDao.delete(id);
    }

    public ArrayList<Order> findAllOrder() {
        orderDao = DaoFactory.getOrderDao();
        ArrayList<Order> arrayList = orderDao.findByCustomerId(new CustomerStorage().getUser().getId());
        for (Order order:arrayList ) {
            order.continueInit();
        }
        return arrayList;
    }
}
