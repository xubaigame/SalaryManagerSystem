package services;

import dao.UserDao;
import model.User;

import java.sql.SQLException;

public class UserServer {
    private UserDao userDao=new UserDao();
    public boolean Login(User user)
    {
        try {
            boolean result=userDao.IdentifyAdministrator(user);
            return result;
        } catch (SQLException e) {
            System.out.println("UserServer-Login Error:"+e);
            return false;
        }
    }
}
