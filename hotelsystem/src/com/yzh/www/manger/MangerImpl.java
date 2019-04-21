package com.yzh.www.manger;

import com.yzh.www.entity.*;
import com.yzh.www.factory.ServiceFactory;
import com.yzh.www.service.*;
import com.yzh.www.serviceImpl.BaseServiceImpl;
import com.yzh.www.view.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Storage.HotelStorage;
import Storage.MangerStorage;

import java.util.ArrayList;



public class MangerImpl implements Manger {
    private FirstService firstService;
    private BaseService baseService;
    private ManagerService managerService;
    private CustomService customService;


    @Override
    public int login(int choiceUser, String userName, String passWord, Stage stage,boolean isSelcet) {
        firstService = ServiceFactory.getFirstService();
        boolean cheack = firstService.cheakInfo(choiceUser, userName, passWord);
        if(cheack){
            firstService.retainUserInfo(userName,passWord,isSelcet);
            return firstService.updataScene(choiceUser, stage);
        }
        return 0;
    }

    @Override
    public int enroll(RegistView rv) {
        firstService = ServiceFactory.getFirstService();
        return firstService.addUser(rv);
    }

    @Override
    public int deleteService(Service service){
        managerService = ServiceFactory.getManagerService();
        return managerService.deleteService(service);
    }

    public boolean changeService(String name,String price,String contence,int choice,Service service) {
        managerService = ServiceFactory.getManagerService();
        if (choice == 1){
            return managerService.changeService(name, price, contence,service.getId());
        }
        if(choice==2){
            return managerService.addService(name, price, contence);
        }
        return false;
    }

    public ArrayList loadService(int choice,int hotelid){
        if(choice==1){
            managerService = ServiceFactory.getManagerService();
            return managerService.findService();
        }
        else{
            customService = ServiceFactory.getCustomService();
            return customService.creatServiceInfo(hotelid);
        }
    }


    @Override
    public ArrayList loadComment(int choice,int hotelid ){
        if(choice==1){
            managerService = ServiceFactory.getManagerService();
            return managerService.findComment();
        }
        else {
            customService = ServiceFactory.getCustomService();
            return customService.creatCommentInfo(hotelid);
        }
    }


    public ArrayList<Order> loadOrder() {
        managerService = ServiceFactory.getManagerService();
        return managerService.findOrderByHotel();
    }

    @Override
    public void getLoginInfo(TextField textField, PasswordField passwordField, RadioButton radioButton) {
        firstService = ServiceFactory.getFirstService();
        firstService.geLoginInfo(textField, passwordField, radioButton);
    }

    @Override
    public int changeInfo(ChangeInfoView changeInfoView) {
        firstService = ServiceFactory.getFirstService();
        return firstService.changeUserInfo(changeInfoView);
    }
    public int changeHotel(Hotel hotel,int choice){
       return ServiceFactory.getAdministratorService().changeHotelInfo( hotel,choice);
    }

    @Override
    public int changeRoom(Room room,int choice){
        managerService = ServiceFactory.getManagerService();
       return managerService.changeRoomInfo(room,choice);
    }

    @Override
    public ArrayList<Account> loadAccount(int choice){
        baseService = ServiceFactory.getBaseService();
        return baseService.findAccount(choice);
    }

    @Override
    public boolean creatOrder( CalendarView cv) {
        customService = ServiceFactory.getCustomService();
        baseService = ServiceFactory.getBaseService();
        customService.creatOrder(cv.getDate(), cv.getDuration(), cv.getRoomId(),cv.getSelectStr());
        customService.setRoomState(cv.getRoomId());
        baseService.creatAccountOut(cv);
        cv.getTableView().setItems(FXCollections.observableList(customService.findEnptyRoom(new HotelStorage().getHotel().getId())));
        return true;
    }

    @Override
    public boolean deleteOrder(Order order) {
        customService = ServiceFactory.getCustomService();
        if (customService.removeCustomer(order)) {
            customService.deleteCusSer(order.getRoom().getId(), order.getCustomer().getId());
            customService.deleteOrder(order.getId());
            new BaseServiceImpl().creatAccontin(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteOrder(OrderView orderView) {
        Order order = (Order) orderView.getListView().getSelectionModel().getSelectedItem();
        if (deleteOrder(order)) {
            orderView.update();
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Hotel> loadHotel(Object choice,TextField tf) {
        customService = ServiceFactory.getCustomService();
        if(choice!=null){
           return customService.findHotel((String) choice, tf.getText());
        }
        else {
            return customService.findHotel(tf.getText());
        }
    }

    @Override
    public int creatComment(String contence,String point ,Order order){
        customService = ServiceFactory.getCustomService();
       return customService.creatComment(contence, point, order);
    }

    @Override
    public ArrayList<Room> loadEmptyRoom() {
        customService = ServiceFactory.getCustomService();
        return customService.findEnptyRoom(new HotelStorage().getHotel().getId());
    }

    @Override
    public ArrayList<Room> loadRoomByManager(){
        baseService = ServiceFactory.getBaseService();
        return baseService.findAllRoom(new MangerStorage().getUser().getHotel().getId());
    }

}
