package com.yzh.www.dao;

import java.util.ArrayList;

/**
 * 用于操作存储顾客所预定的服务的表的类
 */

public interface CusSerDao {
    /**
     * 用于判断是否有该服务被顾客所预定
     * @param serviceId   要判断的服务的id
     * @return  如果有返回true 否则相反
     */

    public boolean isService(int serviceId);

    /**
     *查找指定顾客在指定房间所预定的服务
     * @param roomid 房间的id
     * @param customerid 顾客的id
     * @return  返回查找的结果集合，集合的元素为Service对象
     */

    public ArrayList findAll(int roomid, int customerid);

    /**
     *添加顾客所预定的服务
     * @param customerId 顾客的id
     * @param roomId    房间的id
     * @param serviceId  所预定服务的id
     * @return  返回新增是否成功的布尔值
     */

    public boolean insert(int customerId,int roomId,int serviceId );

    /**
     *通过顾客所预定服务的id删除该预定服务
     * @param id  所预定服务的id
     * @return  返回删除是否成功
     */

    public boolean delete(int id);

    public boolean delete(int roomId,int customerId);
}
