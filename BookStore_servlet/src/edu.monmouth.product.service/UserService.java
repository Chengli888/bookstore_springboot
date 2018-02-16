package edu.monmouth.product.service;

import edu.monmouth.product.dao.UserDao;
import edu.monmouth.product.domain.User;
import edu.monmouth.product.exception.UserException;
import edu.monmouth.product.util.SendMail;

import java.sql.SQLException;

public class UserService {
    UserDao ud = new UserDao();
    public void regist(User user)throws UserException{
        try {
            ud.addUser(user);
            //String emailMsg = "Register successfully,Please click <a href='http:www.product.com/activeServlet?activeCode="+user.getActivecode()+"'>" +
                   // "here</a>to active";
            //SendMail.sendMail(user.getEmail(),emailMsg);
        }

    catch(SQLException e){
        e.printStackTrace();
        throw  new UserException("some problems happened in your register process  please try again");
    }
}
public  void  activeUser(String activecode) throws UserException {
    try {
        User user = ud.findUserByActiveCode(activecode);
        if(user!=null){
            ud.activeCode(activecode);
            return;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw  new UserException("fail to active");
    }

}

    public User login(String username, String password) throws UserException {
        User user = null;
        try {
            user  = ud.findUserByNameAndPassword(username,password);
            if(user == null){
                throw new UserException("username or password is error");
            }
            if(user.getState() == 0){
                throw new UserException("you did not active your account");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("username or password is error");
        }
        return user;
    }

    public User findUserById(String id) throws SQLException {
       return ud.findUserById(id);
    }

    public void ModifyUserInfo(User user) throws SQLException {
        ud.ModifyUserInfo(user);
    }
}
