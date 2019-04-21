package com.yzh.www.service;

import com.yzh.www.entity.Account;
import com.yzh.www.entity.Hotel;
import com.yzh.www.entity.Order;
import com.yzh.www.entity.Room;
import com.yzh.www.view.CalendarView;

import java.util.ArrayList;

/**
 * 一个定义了通用的逻辑方法接口
 */
public interface BaseService {
    /**
     * 查找某酒店
     * @param id 酒店的id
     * @return 返回查找的结果
     */
    Hotel findHotel(int id);

    /**
     * 通过酒店查找其所有酒店并对这些酒店进一步初始化为能够在tableview上显示做准备
     * @param hotelId 酒店的id
     * @return 返回继续初始化的酒店集合
     */
    ArrayList<Room> findAllRoom(int hotelId);

    /**
     * 当生成订单时，生成相对于顾客是支出，酒店是收入的账单
     * @param cv 预约房间的界面的对象
     */
   void creatAccountOut(CalendarView cv);

    /**
     * 当取消订单时，生成相对于顾客是收入，酒店是支持的账单
     * @param order 订单的对象
     */
     void creatAccontin(Order order);

    /**
     * 找到酒店或者顾客的账单
     * @param choice 用于选择是酒店还是顾客账单，1是顾客，2是酒店
     * @return 返回查找包含账单的集合
     */
    ArrayList<Account> findAccount(int choice);
}
