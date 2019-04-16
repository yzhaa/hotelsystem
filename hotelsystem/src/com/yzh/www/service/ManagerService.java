package com.yzh.www.service;

import com.yzh.www.entity.Manager;
import com.yzh.www.entity.Room;
import com.yzh.www.entity.Service;

import java.util.ArrayList;

public interface ManagerService {
    /**
     * 对房间的增删修改
     * @param room 房间的对象
     * @param choice 1 代表修改，2代表删除，3代表生成
     * @return
     */
    public boolean changeRoomInfo(Room room, int choice);

    /**
     * 获取酒店的订单
     * @return  返回包含酒店订单的Order集合
     */
    public ArrayList findOrderByHotel();

    /**
     * 获取评论
     * @return 包含所有Comment对象的集合
     */
    public ArrayList findComment();

    /**
     * 获取管理员的对象
     * @param accont 管理员的帐号
     * @return 有则返回管理员所对应的对象，反之返回null
     */
    public Manager findManaByAccont(int accont);

    /**
     * 获取管理员的对象
     * @param id 管理员的id
     * @return  有则返回管理员所对应的对象，反之返回null
     */
    public Manager findManaById(int id);

    /**
     * 获取该酒店的所有服务
     * @return 返回包含Service对象的集合
     */
    public ArrayList findService();

    /**
     * 修改服务信息
     * @param name 所要修改的名称
     * @param price 所要修改的价格
     * @param contence  所要修改的内容
     * @param id  所要修改的服务的id
     * @return  返回修改是否成功
     */
    public boolean changeService(String name,String price,String contence,int id);

    /**
     *  删除服务
     * @param service  包含所要删除服务信息的对象
     * @return
     */
    public boolean deleteService(Service service);

    /**
     *  判断对修改和添加服务所输入的信息是否符合规格
     * @param name 判断对修改和添加服务所输入的名称
     * @param price 判断对修改和添加服务所输入的价格
     * @param contence 判断对修改和添加服务所输入的内容
     * @return
     */
    public boolean judgeServiceInfo(String name,String price,String contence);

    /**
     * 检查管理员是否有绑定酒店
     * @return  有则返回true 否则反之
     */
    public boolean cheakHotel();

    /**
     * 添加一个服务
     * @param name  服务的名称
     * @param price  服务的价格
     * @param contence   服务的内容
     * @return  返回添加是否成功
     */
    public boolean addService( String name,String price,String contence);
}
