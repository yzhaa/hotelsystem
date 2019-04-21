package com.yzh.www.daoImpl;

import com.yzh.www.dao.CommentDao;
import com.yzh.www.entity.Comment;
import java.util.ArrayList;

public class CommentDaoImpl implements CommentDao {
    private BaseDao baseDao = new BaseDao();


    public boolean insert(String contence,int point,int hotelid){
        Object[] objects = {contence, point, hotelid};
        return baseDao.insert("insert into comment (contence,point,hotelid) values(?,?,?)",objects);
    }

    public boolean delete(int hotelid){
        Object[] objects = {hotelid};
        return baseDao.delete("delete  from comment where hotelid=?", objects);
    }

    public ArrayList<Comment> findAll(int hotelid){
        Object[] objects = {hotelid};
        return baseDao.getList(Comment.class, objects, "select * from comment where hotelid=?");
    }

}
