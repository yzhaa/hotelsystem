package com.yzh.www.daoImpl;
import com.yzh.www.util.JDBCUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.sql.*;
import java.util.ArrayList;

public class  BaseDao {

    public <T> ArrayList<T> getList(Class<T> tClass, String sql, Object... objects) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<T> arrayList = null;
        try {
            ps = conn.prepareStatement(sql);
            if (objects != null) {
                for (int i = 0; i < objects.length && i < ps.getParameterMetaData().getParameterCount(); i++) {
                    ps.setObject(1 + i, objects[i]);
                }
            }
            rs = ps.executeQuery();
            Constructor[] cons =  tClass.getConstructors();
            java.sql.ResultSetMetaData data = rs.getMetaData();
            Constructor<T> con = null;
            for (Constructor<T> c : cons) {
                if (c.getParameterCount() == data.getColumnCount()) {
                    con = c;
                }
            }
            arrayList = new ArrayList<>();
            if(con!=null){
                while (rs.next()) {
                    Object[] objs = new Object[data.getColumnCount()];
                    for (int i = 0; i < data.getColumnCount(); i++) {
                        objs[i] = rs.getObject(data.getColumnName(i + 1));
                        if (con.getParameterTypes()[i] == int.class && objs[i] == null) {
                            throw new IllegalArgumentException("基本数据类型传入了null");
                        }
                    }
                    arrayList.add(con.newInstance(objs));
                }
            } else throw new NullPointerException("没有找到与表对应的构造函数");
            return arrayList;
        } catch (SQLException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return arrayList;
    }

    ArrayList<Integer> executeQuery(String sql,Object... elems) {

        PreparedStatement ps = null;

        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> arrayList = null;
        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement(sql);
            for(int i=0;i<elems.length;i++){
                ps.setObject(i+1,elems[i]);
            }
            rs = ps.executeQuery();
            arrayList = new ArrayList<>();
            while (rs.next()) {
                arrayList.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, con);
        }
        return arrayList;
    }

    public boolean update(String sql, Object... objects) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(1 + i, objects[i]);
            }
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(ps, conn);
        }
        return false;
    }

    public boolean delete(String sql, Object... objects) {
        return update(sql, objects);
    }

    public boolean insert(String sql, Object... objects) {
        return update(sql, objects);
    }

}

