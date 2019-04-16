package com.yzh.www.dao;

import com.yzh.www.entity.Manager;
import com.yzh.www.entity.User;
import com.yzh.www.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerUserDaoImpl implements UserDao {
    public ManagerUserDaoImpl() {
        super();
    }


    public boolean deleteHotelId(int id){
        String sql = "update manager set hotelid=(NULL) where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,conn);
        }
        return false;
    }

    @Override
    public boolean add(User user) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="insert into manager(accont,password) values(?,?)";
        try{
            conn= JDBCUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,user.getAccont());
            ps.setString(2,user.getPassword());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            JDBCUtil.close(rs,ps,conn);
        }
        return false;
    }


    public boolean addHotel(int id,int hotelId) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="update  manager set hotelid=?  where id=? ";
        try{
            conn= JDBCUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,hotelId);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            JDBCUtil.close(rs,ps,conn);
        }
        return false;
    }


    @Override
    public boolean update(User user) {
        Connection conn=null;
        PreparedStatement ps=null;
        String sql="update manager set username=?,idcard=?,password=?,phonenumber=?  where accont=?";
        try {
            conn=JDBCUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getIdCard());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getPhoneNumber());
            ps.setInt(5,user.getAccont());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,conn);
        }
        return false;
    }

    public void delete(int id) {

    }

    @Override
    public User findByAccont(int accont) {
        Manager manager =null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from manager where accont=? ";
        Connection con= null;
        try {
            con = JDBCUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,accont);
            rs=ps.executeQuery();
            while (rs.next()) {
                manager = new Manager(rs.getInt(1),rs.getString(2),rs.getString(3)
                        ,rs.getInt(4),rs.getString(5)
                        , rs.getString(6),rs.getInt(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.close(rs,ps,con);
        }
        return manager;
    }

    public User findByid(int id) {
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from manager where id=? ";
        Connection con= null;
        try {
            con = JDBCUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()) {
                 return new Manager(rs.getInt(1),rs.getString(2),rs.getString(3)
                        ,rs.getInt(4),rs.getString(5)
                        , rs.getString(6),rs.getInt(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.close(rs,ps,con);
        }
        return null;
    }

    public boolean isHasHotel(int id){
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select hotelid from manager where id=? ";
        Connection con= null;
        try {
            con = JDBCUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()) {
                if(rs.getInt(1)==0){
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.close(rs,ps,con);
        }
        return true;
    }

}
