package com.yzh.www.dao;

import com.yzh.www.entity.Administrator;
import com.yzh.www.entity.User;
import com.yzh.www.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdministratorUserDaoImpl implements UserDao {
    @Override
    public boolean add(User user) {
        return false;
    }


    @Override
    public User findByAccont(int accont)  {
        Administrator administrator =null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from administrator where accont=? ";
        Connection con= null;
        try {
            con = JDBCUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,accont);
            rs=ps.executeQuery();
            while (rs.next()) {

                administrator = new Administrator();
                administrator.setId(rs.getInt(1));
                administrator.setUserName(rs.getString(2));
                administrator.setIdCard(rs.getString(3));
                administrator.setAccont(rs.getInt(4));
                administrator.setPassword(rs.getString(5));
                administrator.setPhoneNumber(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.close(rs,ps,con);
        }
        return administrator;
    }
    @Override
    public boolean update(User user)  {
        Connection conn=null;
        PreparedStatement ps=null;
        String sql="update administrator set username=?,idcard=?,password=?,phonenumber=?  where accont=?";
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

    @Override
    public User findByid(int id) {
        return null;
    }
}
