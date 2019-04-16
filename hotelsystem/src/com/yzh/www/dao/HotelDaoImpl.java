package com.yzh.www.dao;

import com.yzh.www.entity.Hotel;
import com.yzh.www.util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HotelDaoImpl implements HotelDao {

    public ArrayList<Hotel> findByName(String name){
        String sql = "select * from hotel where name like ?";
        Hotel hotel=null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Hotel> arrayList=new ArrayList<Hotel>();
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+name+"%");
            rs=ps.executeQuery();
            while (rs.next()){
                arrayList.add(new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }

    public ArrayList<Hotel> findBytype(String type){
        String sql = "select * from hotel where type=?";
        Hotel hotel=null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Hotel> arrayList=new ArrayList<Hotel>();
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,type);
            rs=ps.executeQuery();
            while (rs.next()){
                arrayList.add(new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }

    public ArrayList<Hotel> findAll(){
        String sql = "select * from hotel";
        Hotel hotel=null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Hotel> arrayList=new ArrayList<Hotel>();
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                arrayList.add(new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }

    public Hotel findById(int id){
        String sql = "select * from hotel where id=?";
        Hotel hotel=null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Hotel hotel1 = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                hotel=new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return hotel;
    }

    public boolean updata(Hotel hotel){
        String sql = "update hotel set name=? ,type=? ,info=?,managerid=? where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getType());
            ps.setString(3,hotel.getInfo());
            ps.setInt(4, hotel.getManager().getId());
            ps.setInt(5,hotel.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }
        return false;
    }
    public boolean delete(int id){
        String sql = "delete from hotel where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }
        return false;
    }
    public boolean creat(Hotel hotel){
        String sql = "insert into hotel (id,name,type,info,managerid) values(?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,hotel.getId());
            ps.setString(2, hotel.getName());
            ps.setString(3, hotel.getType());
            ps.setString(4, hotel.getInfo());
            ps.setInt(5, hotel.getManager().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }
        return false;
    }

}
