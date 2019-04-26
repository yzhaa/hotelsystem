package com.yzh.www.util;


import java.sql.*;


/**
 * 用于对数据库的连接，以及关闭数据库
 */
public class JDBCUtil {
    private static MyDatabasePool myDatabasePool=new MyDatabasePool();

    public JDBCUtil() {
        super();
    }

    /**
     * 数据库的连接
     *
     * @return 对数据库连接的 Connection对象
     */

    public static Connection getConnection() {
        Connection con = myDatabasePool.getConnection();
        if(con==null){
            throw new NullPointerException("空的数据库连接对象");
        }
        return con;
    }


    public static void close( ResultSet rs,PreparedStatement ps,Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(conn!=null){
                myDatabasePool.FreeConnection(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public static void close(PreparedStatement ps,Connection conn){
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn != null) {
            myDatabasePool.FreeConnection(conn);
        }
    }


}
