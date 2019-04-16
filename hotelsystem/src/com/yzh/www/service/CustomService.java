package com.yzh.www.service;

import com.yzh.www.entity.*;

import java.util.ArrayList;
import java.util.Date;

public interface CustomService {
    /**
     * 通过酒店类型和名称 获取酒店
     * @param choice  获取酒店的类型
     * @param hotelName 获取酒店的名称
     * @return 返回酒店的集合
     */
    public ArrayList<Hotel> findHotel(String choice, String hotelName);

    /**
     *通过酒店名称获取酒店
     * @param hotelName
     * @return 返回酒店的集合
     */
    public ArrayList<Hotel> findHotel(String hotelName);

    /**
     *获取酒店通过房间id
     * @param id 房间的id
     * @return 返回酒店
     */
    public Hotel findHotelByRoom(int id);

    /**
     *获取酒店空房间
     * @param hotelId 酒店的id
     * @return 返回包含Room对象的集合
     */
    public ArrayList<Room> findEnptyRoom(int hotelId);

    /**
     *获取房间
     * @param roomId 房间id
     * @return 返回房间
     */
    public Room findRoom(int roomId);

    /**
     *获取酒店所有服务
     * @param hotelId 酒店Id
     * @return  返回包含Service对象的集合
     */
    public ArrayList<Service> findAllService(int hotelId);

    /**
     *获取服务
     * @param id 服务的id
     * @return 返回服务对象
     */
    public Service findService(int id);

    /**
     *得到所有服务的名称
     * @return   返回服务名称的数组
     */
    public String[] getServiceNames();

    /**
     *设置房间的状态，是否有人
     * @param roomId 房间的id
     * @return 返回设置状态是否成功
     */
    public boolean setRoomState(int roomId);

    /**
     *获取顾客
     * @param id 顾客id
     * @return 返回顾客对象
     */
    public Customer findCustomerById(int id);

    /**
     *删除订单后移除顾客
     * @param order 需要删除的订单
     * @return 返回移除是否成功
     */
    public boolean removeCustomer(Order order);

    /**
     *生成订单
     * @param date  预定房间的日期
     * @param duration   预定房间的天数
     * @param roomId     预定房间的id
     * @param serviceStr  预定的服务名称数组
     */
    public void creatOrder(Date date, String duration, int roomId, String[] serviceStr);

    /**
     *生成评论
     * @param contence  评论的内容
     * @param point  评论的分数
     * @param order  评论的订单
     */
    public void creatComment(String contence, String point, Order order);

    /**
     * 生成顾客所预定的服务
     * @param customerId 顾客的id
     * @param roomId   顾客预定的房间id
     * @param serviceStr  顾客预定服务的名称数组
     */
    public void creatCusSer(int customerId, int roomId, String[] serviceStr);

    /**
     *删除顾客所预定的服务
     * @param roomId 房间的id
     * @param customerId 顾客的id
     */
    public void deleteCusSer(int roomId, int customerId);

    /**
     * 查找酒店中的服务
     * @param hotelId 酒店的id
     * @return  返回包含该酒店所有服务信息的集合
     */
    public ArrayList creatServiceInfo(int hotelId);

    /**
     *查找酒店的评论信息
     * @param hotelId 酒店的id
     * @return  返回包含该酒店所有评论信息的集合
     */
    public ArrayList creatCommentInfo(int hotelId);

    /**
     *通过房间和顾客查找对应被预定的服务
     * @param roomId 房间的id
     * @param customerId 顾客的id
     * @return
     */
    public ArrayList findCusSer(int roomId, int customerId);

    /**
     *删除订单
     * @param id 订单的id
     */
    public void deleteOrder(int id);

    /**
     *获取所有的订单
     * @return 返回包含所有订单的集合
     */
    public ArrayList findAllOrder();

}
