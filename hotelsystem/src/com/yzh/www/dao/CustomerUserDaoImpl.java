package com.yzh.www.dao;

import com.yzh.www.entity.Customer;
import com.yzh.www.entity.User;
import com.yzh.www.util.JDBCUtil;

import java.sql.*;

public class CustomerUserDaoImpl implements UserDao {

    public CustomerUserDaoImpl() {
        super();
    }

    @Override
    public boolean add(User user)  {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="insert into customer(accont,password) values(?,?)";
        try{
            conn=JDBCUtil.getConnection();
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

    @Override
    public boolean update(User user)  {
        Connection conn=null;
        PreparedStatement ps=null;
        String sql="update customer set username=?,idcard=?,password=?,phonenumber=?  where accont=?";
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


    public void delete(int id)  {

    }

    @Override
    public User findByAccont(int accont)  {
        Customer customer =null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from customer where accont=? ";
        Connection con= null;
        try {
            con = JDBCUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,accont);
            rs=ps.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt(1));
                customer.setUserName(rs.getString(2));
                customer.setIdCard(rs.getString(3));
                customer.setAccont(rs.getInt(4));
                customer.setPassword(rs.getString(5));
                customer.setPhoneNumber(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.close(rs,ps,con);
        }
        return customer;
    }

    public Customer findByid(int id)  {
        Customer customer =null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from customer where id=? ";
        Connection con= null;
        try {
            con = JDBCUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt(1));
                customer.setUserName(rs.getString(2));
                customer.setIdCard(rs.getString(3));
                customer.setAccont(rs.getInt(4));
                customer.setPassword(rs.getString(5));
                customer.setPhoneNumber(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.close(rs,ps,con);
        }
        return customer;
    }

}

