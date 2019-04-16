package com.yzh.www.dao;

import com.yzh.www.entity.User;

public interface UserDao {

    /**
     * 往数据库中存储一个新用户
     * @param user  包含新用户信息的User对象
     * @return  返回是否新增成功
     */
    public boolean add(User user);

    /**
     * 对用户修改信息存储到数据库
     * @param user User对象，包含着要修改的信息
     * @return 返回是否存储成功
     */

    public boolean update(User user);

    /**
     * 通过accont查找数据库中对应的用户信息
     * @param accont 用户的帐号
     * @return 返回一个User对象，包含着要查找的信息
     */

    public User findByAccont(int accont) ;

    /**
     * 通过id查找数据库中对应的用户信息
     * @param id  用户的id
     * @return  返回一个User对象，包含着要查找的信息
     */
    public User findByid(int id);

}


