package com.yzh.www.service;

import com.yzh.www.entity.Hotel;

import java.util.ArrayList;

/**
 * 一个定义的管理员的逻辑方法的接口
 */

public interface AdministratorService {
    /**
     * 找到所有酒店
     * @return  返回包含所有酒店的集合
     */
     ArrayList findAllHotel();

    /**
     * 改变酒店的信息，包括增删改
     * @param hotel  要操作的酒店对象
     * @param choice 要进行操作的选择  1代表修改，2代表删除，3代表增加
     * @return  返回操作是否成功的布尔值
     */
     int changeHotelInfo(Hotel hotel, int choice);

    /**
     * 为酒店添加管理员
     * @param  id  管理员的id，
     *  @param  hotel     要添加管理员的酒店对象
     */
     void addHoteltoMana(int id ,Hotel hotel);

    /**
     *判断管理员用户是否绑定酒店
     * @param managerId  管理员的id
     * @return 判断管理员是否有自己的酒店
     */
     boolean isManaHasHotel(int managerId);

    /**
     *当删除酒店时，移除酒店的所有服务
     * @param hotelid 要移除的酒店的id
     */
     void removeService(int hotelid);

    /**
     *当删除酒店时，移除酒店的所有房间
     * @param hotelid  要移除的酒店的id
     * @return 返回移除是否成功的布尔值
     */
     boolean removeRoom(int hotelid);

    /**
     *当删除房间时，用于移除管理员
     * @param managerId 管理员id
     */
     void removeMana(int managerId);
}
