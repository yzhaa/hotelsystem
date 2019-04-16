package com.yzh.www.controller;

import com.yzh.www.entity.*;
import com.yzh.www.service.*;
import com.yzh.www.util.MyAlert;
import com.yzh.www.view.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Storage.HotelStorage;
import Storage.MangerStorage;
import java.util.ArrayList;



public class ControlActionImpl implements ControlAction {
    private FirstService firstService;
    private BaseService baseService;
    private ManagerService managerService;
    private CustomService customService;
    private AdministratorService administratorService;


    @Override
    public boolean login(int choiceUser, String userName, String passWord, Stage stage,boolean isSelcet) {
        firstService = new FirstServiceImpl();
        boolean cheack = firstService.cheakInfo(choiceUser, userName, passWord);
        if(cheack){
            firstService.retainUserInfo(userName,passWord,isSelcet);
            firstService.updataScene(choiceUser,stage);
            return true;
        }
        MyAlert.setAlert("帐号或密码不正确或没无该类型帐号！",0);
        return cheack;
    }

    @Override
    public boolean enroll(RegistView rv) {
        firstService=new FirstServiceImpl();
        return firstService.addUser(rv);
    }

    @Override
    public boolean deleteService(Service service){
        managerService = new ManagerServiceImpl();
        return managerService.deleteService(service);
    }

    public boolean changeService(String name,String price,String contence,int choice,Service service) {
        managerService = new ManagerServiceImpl();
        if (choice == 1){
            return managerService.changeService(name, price, contence,service.getId());
        }
        if(choice==2){
            return managerService.addService(name, price, contence);
        }
        return false;
    }

    @Override
    public ArrayList loadService(){
        managerService = new ManagerServiceImpl();
       return managerService.findService();
    }

    @Override
    public void loadInfo(ListView lvs,ListView lvm) {
        customService = new CustomServiceImpl();
        Hotel  hotel= new HotelStorage().getHotel();
        if(hotel!=null){
            int hotelId = hotel.getId();
            if(lvs!=null){
                lvs.setItems(FXCollections.observableList(customService.creatServiceInfo(hotelId)));
            }
            if(lvm!=null){
                lvm.setItems(FXCollections.observableList(customService.creatCommentInfo(hotelId)));
            }
        }
    }

    @Override
    public ArrayList loadComment(){
        managerService = new ManagerServiceImpl();
        return managerService.findComment();
    }


    public ArrayList loadOrder() {
        managerService = new ManagerServiceImpl();
        return managerService.findOrderByHotel();
    }

    @Override
    public void getLoginInfo(TextField textField, PasswordField passwordField, RadioButton radioButton) {
        firstService=new FirstServiceImpl();
        firstService.geLoginInfo(textField, passwordField, radioButton);
    }

    @Override
    public boolean changeInfo(ChangeInfoView changeInfoView) {
        firstService=new FirstServiceImpl();
        return firstService.changeUserInfo(changeInfoView);
    }
    public boolean changeHotel(Hotel hotel,int choice){
        administratorService = new AdministratorServiceImpl();
        return administratorService.changeHotelInfo( hotel,choice);
    }

    @Override
    public boolean changeRoom(Room room,int choice){
        managerService = new ManagerServiceImpl();
       return managerService.changeRoomInfo(room,choice);
    }

    @Override
    public ArrayList loadAccount(int choice){
        baseService = new BaseServiceImpl();
        return baseService.findAccount(choice);

    }

    @Override
    public boolean creatOrder( CalendarView cv) {
        customService = new CustomServiceImpl();
        baseService = new BaseServiceImpl();
        customService.creatOrder(cv.getDate(), cv.getDuration(), cv.getRoomId(),cv.getSelectStr());
        customService.setRoomState(cv.getRoomId());
        baseService.creatAccountOut(cv);
        cv.getTableView().setItems(FXCollections.observableList(customService.findEnptyRoom(new HotelStorage().getHotel().getId())));
        return true;
    }

    @Override
    public boolean deleteOrder(Order order) {
        customService = new CustomServiceImpl();
            if(customService.removeCustomer(order)){
            customService.deleteCusSer(order.getRoom().getId(),order.getCustomer().getId());
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
    public ArrayList loadHotel(Object choice,TextField tf) {
        customService = new CustomServiceImpl();
        if(choice!=null){
           return customService.findHotel((String) choice, tf.getText());
        }
        else {
            return customService.findHotel(tf.getText());
        }
    }

    @Override
    public void creatComment(String contence,String point ,Order order){
        customService = new CustomServiceImpl();
        customService.creatComment(contence, point, order);

    }

    @Override
    public ArrayList<Room> loadEmptyRoom() {
        customService = new CustomServiceImpl();
        return customService.findEnptyRoom(new HotelStorage().getHotel().getId());
    }

    @Override
    public ArrayList<Room> loadRoomByManager(){
        baseService = new BaseServiceImpl();
        return baseService.findAllRoom(new MangerStorage().getUser().getHotel().getId());
    }

}
