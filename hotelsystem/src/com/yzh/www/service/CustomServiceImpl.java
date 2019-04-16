package com.yzh.www.service;

import com.yzh.www.dao.*;
import com.yzh.www.entity.*;
import com.yzh.www.util.MyAlert;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
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
    private UserDao userDao;

    public ArrayList<Hotel> findHotel(String choice, String hotelName) {
        hotelDao = new HotelDaoImpl();
        ArrayList<Hotel> al = null;
        if (hotelName.equals("")) {
            al = hotelDao.findBytype(choice);
        } else {
            al = new ArrayList();
            ArrayList a2 = hotelDao.findByName(hotelName);
            al.addAll(a2);
            a2.removeAll(hotelDao.findBytype(choice));
            al.removeAll(a2);
        }
        return al;
    }

    public ArrayList<Hotel> findHotel(String hotelName) {
        hotelDao = new HotelDaoImpl();
        return hotelDao.findByName(hotelName);
    }


    public Hotel findHotelByRoom(int id) {
        hotelDao = new HotelDaoImpl();
        return hotelDao.findById(new RoomDaoImpl().findHotelIdById(id));
    }

    public ArrayList<Room> findEnptyRoom(int hotelId) {
        roomDao = new RoomDaoImpl();
        ArrayList arrayList = roomDao.findAllEmpty(hotelId);
        for (Iterator ite = arrayList.iterator(); ite.hasNext(); ) {
            ((Room) ite.next()).continueInit();
        }
        return arrayList;
    }

    public Room findRoom(int roomId) {
        roomDao = new RoomDaoImpl();
        return roomDao.findById(roomId);
    }

    public ArrayList<Service> findAllService(int hotelId) {
        serviceDao = new ServiceDaoImpl();
        return serviceDao.findAllByHotelId(hotelId);
    }

    public Service findService(int id) {
        serviceDao = new ServiceDaoImpl();
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


    public boolean setRoomState(int roomId) {
        roomDao = new RoomDaoImpl();
        boolean state = roomDao.insertCustomer(new CustomerStorage().getUser().getId(), roomId);
        return state;
    }


    public Customer findCustomerById(int id) {
        userDao = new CustomerUserDaoImpl();
        return (Customer) userDao.findByid(id);
    }

    public boolean removeCustomer(Order order) {
        if (order != null) {
            roomDao = new RoomDaoImpl();
            return roomDao.removeCustomer(order.getRoom().getId());
        }
        MyAlert.setAlert("请选择要删除的订单", 0);
        return false;
    }

    public void creatOrder(Date date, String duration, int roomId, String[] serviceStr) {
        orderDao = new OrderDaoImpl();
        int customerId = new CustomerStorage().getUser().getId();
        long sdf = date.getTime();
        orderDao.insertOrder(customerId, roomId, new java.sql.Date(sdf),
                new java.sql.Date(sdf + Integer.parseInt(duration) * 86400000));
        creatCusSer(customerId, roomId, serviceStr);
    }

    public void creatComment(String contence, String point, Order order) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (order == null) {
            alert.setHeaderText("选择要评价的订单");
        } else if (point.equals("")) {
            alert.setHeaderText("请输入评价的分数");
        } else {
            commentDao = new CommentDaoImpl();
            commentDao.insert(contence, Integer.parseInt(point), order.getRoom().getId(), order.getHotel().getId());
            alert.setHeaderText("评价成功");
            alert.setAlertType(Alert.AlertType.INFORMATION);
        }
        alert.showAndWait();
    }

    public void creatCusSer(int customerId, int roomId, String[] serviceStr) {
        serviceDao = new ServiceDaoImpl();
        cusSerDao = new CusSerDaoImpl();
        for (String serviceName : serviceStr) {
            if (serviceName != null) {
                cusSerDao.insert(customerId, roomId, serviceDao.findOneByName(serviceName, "id"));
            }
        }
    }


    public void deleteCusSer(int roomId, int customerId) {
        cusSerDao = new CusSerDaoImpl();
        cusSerDao.delete(roomId, customerId);
    }


    public ArrayList creatServiceInfo(int hotelId) {
        serviceDao = new ServiceDaoImpl();
        TextArea ta = new TextArea();
        StringBuilder sb = new StringBuilder();
        ArrayList al = serviceDao.findAllByHotelId(hotelId);
        ArrayList alsI = new ArrayList();
        ta.setWrapText(true);
        ta.setEditable(false);
        ta.setPrefWidth(180);
        ta.setPrefHeight(380);
        for (Iterator ite = al.iterator(); ite.hasNext(); ) {
            Service service = (Service) ite.next();
            sb.append("服务名称" + service.getName() + "\t价格：" + service.getPrice() + "\n内容：" +
                    service.getContent() + "\n\n");
        }
        ta.setText(sb.toString());
        alsI.add(ta);
        return alsI;
    }

    public ArrayList creatCommentInfo(int hotelId) {
        commentDao = new CommentDaoImpl();
        TextArea ta = new TextArea();
        StringBuilder sb = new StringBuilder();
        ArrayList al = commentDao.findAll(hotelId);
        ta.setEditable(false);
        ArrayList alsI = new ArrayList();
        ta.setWrapText(true);
        ta.setPrefWidth(180);
        ta.setPrefHeight(250);
        for (Iterator ite = al.iterator(); ite.hasNext(); ) {
            Comment comment = (Comment) ite.next();
            sb.append(comment.toString());
        }
        ta.setText(sb.toString());
        alsI.add(ta);
        return alsI;
    }

    public ArrayList findCusSer(int roomId, int customerId) {
        return new CusSerDaoImpl().findAll(roomId, customerId);
    }

    public void deleteOrder(int id) {
        orderDao = new OrderDaoImpl();
        orderDao.delete(id);
    }

    public ArrayList findAllOrder() {
        orderDao = new OrderDaoImpl();
        ArrayList arrayList = orderDao.findByCustomerId(new CustomerStorage().getUser().getId());
        for (Iterator ite = arrayList.iterator(); ite.hasNext(); ) {
            ((Order) ite.next()).continueInit();
        }
        return arrayList;
    }
}
