package mymvc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    public static Properties properties = new Properties();

    private static ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<>();
    static {
        try {
            InputStream resourceAsStream = DBUtils.class.getResourceAsStream("/db.properties");
            DBUtils.properties.load(resourceAsStream);
            Class.forName(DBUtils.properties.getProperty("driver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = threadLocalConnection.get();
        if(connection == null){
            try {
                connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
                threadLocalConnection.set(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
                threadLocalConnection.remove();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void startTrans(){
        try {
            Connection connection = DBUtils.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void rollback(){
        try {
            Connection connection = DBUtils.getConnection();
            connection.rollback();
            DBUtils.closeAll(connection,null,null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void commit(){
        try {
            Connection connection = DBUtils.getConnection();
            connection.commit();
            DBUtils.closeAll(connection,null,null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
