package mymvc.dao;

import mymvc.DBUtils;
import mymvc.entity.SystemAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SystemAdminDao {
    public int insert(SystemAdmin systemAdmin){
        Connection connection = DBUtils.getConnection();
        String sql = "insert into system_admin (name,username,password) values (?,?,?)";
        int i = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,systemAdmin.getName());
            preparedStatement.setString(2,systemAdmin.getUsername());
            preparedStatement.setString(3,systemAdmin.getPassword());
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(null,preparedStatement,null);
        }
        return i;
    }
    public int update(SystemAdmin systemAdmin){
        Connection connection = DBUtils.getConnection();
        String sql = "update system_admin set name = ?,username = ?,password = ? where id = ?";
        int i = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,systemAdmin.getName());
            preparedStatement.setString(2,systemAdmin.getUsername());
            preparedStatement.setString(3,systemAdmin.getPassword());
            preparedStatement.setInt(4,systemAdmin.getId());
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(null,preparedStatement,null);
        }
        return i;
    }
    public int delete(int id){
        Connection connection = DBUtils.getConnection();
        String sql = "delete from system_admin where id = ?";
        int i = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(null,preparedStatement,null);
        }
        return i;
    }
    public SystemAdmin select(int id){
        Connection connection = DBUtils.getConnection();
        String sql = "select * from system_admin where id = ?";
        PreparedStatement preparedStatement = null;
        SystemAdmin systemAdmin = new SystemAdmin();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                systemAdmin.setId(resultSet.getInt("id"));
                systemAdmin.setName(resultSet.getString("name"));
                systemAdmin.setUsername(resultSet.getString("username"));
                systemAdmin.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(null,preparedStatement,null);
        }
        return systemAdmin;
    }
    public List<SystemAdmin> selectAll(){
        Connection connection = DBUtils.getConnection();
        String sql = "select * from system_admin";
        PreparedStatement preparedStatement = null;
        ArrayList<SystemAdmin> systemAdmins = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                SystemAdmin systemAdmin = new SystemAdmin();
                systemAdmin.setId(resultSet.getInt("id"));
                systemAdmin.setName(resultSet.getString("name"));
                systemAdmin.setUsername(resultSet.getString("username"));
                systemAdmin.setPassword(resultSet.getString("password"));
                systemAdmins.add(systemAdmin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(null,preparedStatement,null);
        }
        return systemAdmins;
    }
}
