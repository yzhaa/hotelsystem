package com.yzh.www.daoImpl;

import com.yzh.www.dao.CommentDao;
import com.yzh.www.entity.Comment;
import com.yzh.www.factory.DaoFactory;

import java.util.ArrayList;

public class CommentDaoImpl implements CommentDao {
    private BaseDao baseDao = DaoFactory.getBaseDao();


    public boolean insert(String contence,int point,int hotelid){
        return baseDao.insert("insert into comment (contence,point,hotelid) values(?,?,?)",contence, point, hotelid);
    }

    public boolean delete(int hotelid){
        return baseDao.delete("delete  from comment where hotelid=?", hotelid);
    }

    public ArrayList<Comment> findAll(int hotelid){
        return baseDao.getList(Comment.class, "select * from comment where hotelid=?",hotelid );
    }

}
