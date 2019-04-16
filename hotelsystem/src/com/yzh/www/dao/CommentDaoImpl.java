package com.yzh.www.dao;

import com.yzh.www.entity.Comment;
import com.yzh.www.util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentDaoImpl implements CommentDao{

    public boolean insert(String contence,int point,int roomid,int hotelid){
        String sql ="insert into comment (contence,point,roomid,hotelid) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, contence);
            ps.setInt(2, point);
            ps.setInt(3, roomid);
            ps.setInt(4, hotelid);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return false;
    }

    public boolean delete(int hotelid){
        String sql = "delete  from comment where hotelid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,hotelid);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }
        return false;
    }

    public ArrayList findAll(int hotelid){
        String sql = "select * from comment where hotelid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList arrayList=null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,hotelid);
            rs = ps.executeQuery();
            arrayList = new ArrayList();
            while (rs.next()) {
                arrayList.add(new Comment(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4),rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }

}
