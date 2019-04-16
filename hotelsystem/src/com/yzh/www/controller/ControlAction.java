package com.yzh.www.controller;

import com.yzh.www.entity.Hotel;
import com.yzh.www.entity.Order;
import com.yzh.www.entity.Room;
import com.yzh.www.entity.Service;
import com.yzh.www.view.CalendarView;
import com.yzh.www.view.ChangeInfoView;
import com.yzh.www.view.OrderView;
import com.yzh.www.view.RegistView;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * 逻辑接口
 */


public interface ControlAction {
    /**
     * 完成登陆，以及界面跳转功能
     * @param ：登入用户类型，帐号，密码，以及主界面容器，是否选择了保存密码
     * @return :登陆是否成功
     * 登入
     */

    public boolean login(int choiceUser, String userName, String passWord, Stage stage, boolean isSelcet);

    /**
     * 注册用户
     * @param ；一个注册界面的对象，里面包含输入的帐号，密码，以及注册类型的选择
     * @return :注册是否成功
     */
    public boolean enroll(RegistView rv);

    /**
     *用于在管理员界面对服务的删除
     * @param ：一个服务对象
     * @return :返回删除是否成功
     */
    public boolean deleteService(Service service);

    /**
     * 提供用户和管理员界面加载服务所需的数据
     * @return 包含服务的容器
     */

    public ArrayList loadService();

    /**
     * 提供用户和管理员界面加载评论所需的数据
     * @return：包含服务的集合
     */
    public ArrayList loadComment();

    /**
     *为管理员提供改变房间的方法
     * @param room
     * @param choice
     * @return
     */
    public boolean changeRoom(Room room,int choice);

    /**
     *提供账单的数据
     * @param choice 是一个用于选择提供用户或者酒店账单
     * @return
     */

    public ArrayList loadAccount(int choice);

    /**
     * 用于用户和管理员删除订单
     * @param order 所要删除的订单对象
     */

    public boolean deleteOrder(Order order);

    /**
     *用于评论框和服务框提供方法
     * @param lvs 一个展示服务的listview
     * @param lvm  一个展示评论的listview
     */


    public void loadInfo(ListView lvs,ListView lvm);

    /**
     * 对酒店的增删改提供方法
     * @param hotel 所要操作的酒店对象
     * @param choice 操作的选择：增 还是 删 还是 改
     * @return
     */

    public boolean changeHotel(Hotel hotel, int choice);

    /**
     * 用于获取登陆的信息，然后根据选择是否保存帐号密码
     * @param textField  输入帐号的TextField控件
     * @param passwordField 输入密码的TextField控件
     * @param radioButton  是否保存密码的RadioButton控件
     */

    public void getLoginInfo(TextField textField, PasswordField passwordField, RadioButton radioButton);

    /**
     * 对用户信息进行完善或者改变
     * @param changeInfoView  一个changeInfoView的对象，里面包含了要改变的信息
     * @return 返回对信息的改变是否成功
     */
    public boolean changeInfo(ChangeInfoView changeInfoView);

    /**
     * 为客户生成订单
     * @param calendarView  一个calendarView 对象，包含的订单的各种信息
     * @return 返回生成订单是否成功
     */

    public boolean creatOrder(CalendarView calendarView);

    /**
     *为用户在查询订单界面提供删除订单的方法
     * @param orderView 一个orderView的对象，包含所要删除订单的信息
     */

    public boolean deleteOrder(OrderView orderView);

    /**
     * 按用户的要求筛选酒店
     * @param choice  用户所选酒店的类型
     * @param tf  用户输入的名称
     * @return 返回酒店的数据
     */

    public ArrayList loadHotel(Object choice,TextField tf);

    /**
     * 用于用户界面加载空房间
     * @return 一个包含所选酒店的所有空房间的数据
     */

    public ArrayList<Room> loadEmptyRoom();

    /**
     * 为管理员界面提供该酒店房间的信息
     * @return 一个包含该酒店的所有房间的数据
     */

    public ArrayList<Room> loadRoomByManager();

    /**
     * 生成用户对已经预定的房间的评论
     * @param contence 用户评论的内容
     * @param point 用户评论的分数
     * @param order  用户评论的对象
     */
    public void creatComment(String contence,String point ,Order order);



    public boolean changeService(String name, String price, String contence, int choice, Service service);


    public ArrayList loadOrder();

}
