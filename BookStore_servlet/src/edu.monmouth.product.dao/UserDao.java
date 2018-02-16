package edu.monmouth.product.dao;

import edu.monmouth.product.domain.User;
import edu.monmouth.product.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    public void addUser(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        String sql ="INSERT into user(username,password,gender,email,telephone,introduce,activecode,state,role)\n" +
                "               VALUES(?,?,?,?,?,?,?,?,?)";
        qr.update(sql, user.getUsername(), user.getPassword(),
                user.getGender(), user.getEmail(), user.getTelephone(),
                user.getIntroduce(), user.getActivecode(), user.getState(),user.getRole());
    }
    public User findUserByActiveCode(String activecode) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from user where activecode=?",new BeanHandler<User>(User.class),activecode);
    }
    public void activeCode(String activecode) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("update user set state=1 where activecode=?",activecode);
    }
    public User findUserByNameAndPassword(String username, String password) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from user where username=? and password=?",new BeanHandler<User>(User.class),username,password);
    }

    public User findUserById(String id) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from user where id=?",new BeanHandler<User>(User.class),id);
    }

    public void ModifyUserInfo(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("update user set password=?,gender=?,telephone=?",user.getPassword(),user.getGender(),user.getTelephone());
    }
}
