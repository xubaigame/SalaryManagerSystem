package dao;

import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DBUtils;

import java.sql.SQLException;

public class UserDao {
    public boolean IdentifyAdministrator(User user) throws SQLException {
        QueryRunner runner= new QueryRunner(DBUtils.getDataSource());
        String sql="select * from administrator where username=? and password=?";
        User temp=runner.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
        if(temp!=null)
            return true;
        else
            return false;
    }
}
