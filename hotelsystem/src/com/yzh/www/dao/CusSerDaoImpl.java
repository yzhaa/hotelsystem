package com.yzh.www.dao;

import com.yzh.www.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CusSerDaoImpl  implements CusSerDao{

    public boolean isService(int serviceId){
        String sql="select serviceid from cus_ser where serviceid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,serviceId);
            rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return false;
    }


    public ArrayList findAll(int roomid,int customerid){
        String sql="select serviceid from cus_ser where roomid=? and customerid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ArrayList arrayList = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,roomid);
            ps.setInt(2,customerid);
            rs=ps.executeQuery();
            arrayList = new ArrayList();
            while (rs.next()){
                arrayList.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }

    public boolean insert(int customerId,int roomId,int serviceId ){
        String sql = "insert into cus_ser (customerid ,roomid ,serviceid ) values(?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, roomId);
            ps.setInt(3,serviceId);
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
        String sql = "delete from cus_ser where id=?";
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

    public boolean delete(int roomId,int customerId){
        String sql = "delete from cus_ser where roomid=? and customerid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,roomId);
            ps.setInt(2, customerId);
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
