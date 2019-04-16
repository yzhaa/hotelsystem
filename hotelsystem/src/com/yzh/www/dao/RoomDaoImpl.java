package com.yzh.www.dao;

import com.yzh.www.entity.Room;
import com.yzh.www.util.JDBCUtil;
import Storage.HotelStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RoomDaoImpl implements RoomDao{

    public ArrayList<Room> findByHotelId(int id){
        String sql = "select * from room where hotelid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Room> arrayList=new ArrayList<Room>();
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                arrayList.add(new Room(rs.getInt(1),new HotelStorage().getHotel(),rs.getInt(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6),rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }

    public Room findById(int id){
        String sql = "select * from room where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Room room = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                room = new Room(rs.getInt(1),new HotelStorage().getHotel(),rs.getInt(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return room;
    }

    public ArrayList<Room> findAllEmpty(int hotelid){
        String sql = "select * from room where ISNULL(customerid) and hotelid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Room> arrayList=new ArrayList<Room>();
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,hotelid);
            rs=ps.executeQuery();
            while (rs.next()){
                arrayList.add(new Room(rs.getInt(1),new HotelStorage().getHotel(),rs.getInt(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6),rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }


    public boolean isEmpty(int id){
        String sql = "select customerid from room where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Room room = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                if(rs.getInt(1)==0){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return false;
    }

    public boolean insertCustomer(int customerId,int roomId){
        String sql = "update room set customerid=? where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,customerId);
            ps.setInt(2, roomId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }
        return false;
    }
    public boolean removeCustomer(int id){
        String sql = "update room set customerid=(NULL) where id=?";
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

    public int findHotelIdById(int id){
        String sql = "select hotelid from room where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
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
    public boolean updata(Room room){
        String sql = "update room set name=? ,type=? ,info=?,price=? where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, room.getName());
            ps.setString(2, room.getType());
            ps.setString(3, room.getInfo());
            ps.setInt(4, room.getPrice());
            ps.setInt(5, room.getId());
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
        String sql = "delete from room where id=?";
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

    public boolean deleteByHotelId(int hotelId){
        String sql = "delete from room where hotelid=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,hotelId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }
        return false;
    }



    public boolean creat(Room room){
        String sql = "insert into room (name,type,info,price,hotelid) values(?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, room.getName());
            ps.setString(2, room.getType());
            ps.setString(3, room.getInfo());
            ps.setInt(4, room.getPrice());
            ps.setInt(5,room.getHotel().getId());
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
