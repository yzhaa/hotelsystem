package com.yzh.www.dao;

import com.yzh.www.entity.Service;
import com.yzh.www.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiceDaoImpl implements ServiceDao{


    public ArrayList<Service> findAllByHotelId(int hotelid){
        String sql="select * from service where hotelid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ArrayList arrayList = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,hotelid);
            rs=ps.executeQuery();
            arrayList = new ArrayList();
            while (rs.next()){
                arrayList.add(new Service(rs.getInt(1),rs.getInt(5),
                        rs.getString(2),rs.getInt(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }

    public int findOneByName(String name, String element){
        String sql="select "+element+ " from service where name=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            rs=ps.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return -1;
    }

    public boolean update(String name,int price,String contence,int id){
        String sql = "update service set name=?,price=?,content=?  where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setString(3, contence);
            ps.setInt(4, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }
        return false;
    }

    public Service findById(int id){
        String sql="select * from service where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                   return  new Service(rs.getInt(1),rs.getInt(5),
                        rs.getString(2),rs.getInt(3), rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return null;
    }

    public Service findByName(String name){
        String sql="select * from service where name=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            rs=ps.executeQuery();
            while (rs.next()){
                return  new Service(rs.getInt(1),rs.getInt(5),
                        rs.getString(2),rs.getInt(3), rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return null;
    }

    public boolean delete(int id){
        String sql = "delete from service where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }
        return false;
    }

    public boolean deleteByhotelId(int hotelId){
        String sql = "delete from service where hotelid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, hotelId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }
        return false;
    }



    public boolean insert(String name,int price,String contece,int hotelid){
        String sql = "insert into service (name,price,content,hotelid) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setString(3, contece);
            ps.setInt(4, hotelid);
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
