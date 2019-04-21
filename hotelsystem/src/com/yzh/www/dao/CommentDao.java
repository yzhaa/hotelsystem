package com.yzh.www.dao;

import com.yzh.www.entity.Comment;

import java.util.ArrayList;

public interface CommentDao {

    /**
     * 往数据库中存储一条新评论
     *
     * @param contence 评论的内容0
     * @param hotelid  所评论的酒店
     * @return 返回新增是否成功
     */
    boolean insert(String contence, int point,int hotelid);

    /**
     * 从数据库中找出指定酒店的所有评论
     *
     * @param hotelid 酒店的id
     * @return 返回包含该酒店的所有评论的集合，集合的元素是Comment的对象
     */
    ArrayList<Comment> findAll(int hotelid);

    boolean delete(int hotelid);

}
