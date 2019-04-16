package com.yzh.www.dao;

import com.yzh.www.entity.Account;
import com.yzh.www.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 对账单的增删查改
 */
public class AccountDaoImpl implements AccountDao{
    /**
     * 存储账单的名 ，因为有管理员和顾客账单
     */
    private static String formName;

    public  void setFormName(String formName){
        AccountDaoImpl.formName = formName;
    }

    /**
     * 通过顾客找账单
     * @param customerId 顾客的id
     * @return  返回包含账单的集合
     */
    public ArrayList findByCustomer(int customerId){
        ArrayList arrayList = null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from "+formName+" where customerid =?";
        Connection con= null;
        try {
            con = JDBCUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,customerId);
            rs=ps.executeQuery();
            arrayList = new ArrayList();
            while (rs.next()) {
                arrayList.add(new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3)
                        , rs.getInt(4), rs.getDate(5), rs.getInt(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.close(rs,ps,con);
        }
        return arrayList;
    }

    /**
     * 通过酒店找账单
     * @param hotelId 酒店的id
     * @return 返回包含账单的集合
     */
    public ArrayList findByHotel(int hotelId){
        ArrayList arrayList = null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from "+formName +" where hotelid =? ";
        Connection con= null;
        try {
            con = JDBCUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,hotelId);
            rs=ps.executeQuery();
            arrayList = new ArrayList();
            while (rs.next()) {
                arrayList.add(new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3)
                        , rs.getInt(4), rs.getDate(5), rs.getInt(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.close(rs,ps,con);
        }
        return arrayList;
    }

    /**
     *  添加一条账单
     * @param hotelId 酒店的id
     * @param roomId  房间的id
     * @param customerId  顾客的id
     * @param date  创建账单的时间
     * @param money  订单的总钱数
     * @return  返回创建是否成功的布尔值
     */

    public Boolean insert(int hotelId, int roomId, int customerId, Date date, int money) {
        Connection conn=null;
        PreparedStatement ps=null;
        String sql="insert into "+formName +" (hotelid,roomid ,customerid ,date,money) values(?,?,?,?,?)";
        try {
            conn=JDBCUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,hotelId);
            ps.setInt(2, roomId);
            ps.setInt(3, customerId);
            ps.setDate(4, date);
            ps.setInt(5, money);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,conn);
        }
        return false;
    }

    /**
     * 通过顾客的id和房间的id删除一个账单
     * @param roomId  房间的id
     * @param customerId  顾客的id
     * @return  返回是否删除的布尔值
     */
    public boolean delete(int roomId,int customerId){
        String sql = "delete from "+formName +" where roomid=? and customerid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roomId);
            ps.setInt(2,customerId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }
        return false;
    }

    public boolean delete(int hotelid){
        String sql = "delete from "+formName +" where hotelid=? ";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, hotelid);
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
