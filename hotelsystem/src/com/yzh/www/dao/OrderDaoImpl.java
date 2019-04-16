package com.yzh.www.dao;


import com.yzh.www.entity.Order;
import com.yzh.www.util.JDBCUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDaoImpl implements  OrderDao{
    public ArrayList findByCustomerId(int customerId){
        String sql = "select * from `order` where customerid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList arrayList = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            arrayList = new ArrayList();
            while (rs.next()){
                arrayList.add(new Order(rs.getInt(1),rs.getInt(2),rs.getInt(3),
                        rs.getDate(4),rs.getDate(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }
    public ArrayList findByRoomId(int roomId){
        String sql = "select * from `order` where roomid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList arrayList = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roomId);
            rs = ps.executeQuery();
            arrayList = new ArrayList();
            while (rs.next()){
                arrayList.add(new Order(rs.getInt(1),rs.getInt(2),rs.getInt(3),
                        rs.getDate(4),rs.getDate(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }
    public boolean insertOrder( int customerId,int roomId, java.sql.Date dateFrom, Date dateTo){
        String sql = "insert into `order` (customerid ,roomid , datefrom ,dateto) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, roomId);
            ps.setDate(3,dateFrom);
            ps.setDate(4,dateTo);
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
        String sql = "delete from `order` where id=?";
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
}
