package com.yzh.www.db;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

public class MyDatabasePool {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static LinkedList<Connection> connPool = new LinkedList<>();

    static {
        try {
            FileInputStream fis=new FileInputStream("resoures\\mysql.properties");
            Properties properties=new Properties();
            properties.load(fis);
            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            user=properties.getProperty("user");
            password=properties.getProperty("password");
            Class.forName(driver);
            connPool = new LinkedList<>();
        }
        catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDatabasePool() {
        for (int i = 0; i < 10; i++) {
            connPool.addLast(creatConnection());
        }
    }

    private Connection creatConnection() {
        Connection conn = null;
        try {
            if(url==null||user==null||password==null){
                throw new NullPointerException("数据库连接的地址，用户名，密码中存在空指针");
            }
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Connection getConnection() {
        if(connPool.size()>0){
            return connPool.removeFirst();
        } else throw new RuntimeException("数据库连接池中没有数据库连接");
}
    public void FreeConnection (Connection conn){
        connPool.addLast(conn);
    }
}

