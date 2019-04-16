package com.yzh.www.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;
import java.util.Properties;

/**
 * 用于对数据库的连接，以及关闭数据库
 */
public class JDBCUtil {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static Connection conn=null;

    public JDBCUtil() {
        super();
    }

    /**
     * 数据库的连接
     * @return 对数据库连接的 Connection对象
     * @throws ConnectException
     */
    public static Connection getConnection ()throws ConnectException {
        try {
            FileInputStream fis=new FileInputStream("resoures\\mysql.properties");
            Properties properties=new Properties();
            properties.load(fis);
            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            user=properties.getProperty("user");
            password=properties.getProperty("password");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        try{
            Class.forName(driver);
            conn= DriverManager.getConnection(url,user,password);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("数据库连接失败");
        }
        return conn;
    }

    /**
     *关闭数据库连接以及数据库访问的相关东西
     * @param rs
     * @param ps
     * @param conn
     */

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
        try{
            if(rs!=null){
                rs.close();
            }
            if (ps!=null){
                ps.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
