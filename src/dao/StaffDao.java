package dao;

import model.Staff;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    public List<Staff> getAllStaff() throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from staff";
        return runner.query(sql,new BeanListHandler<Staff>(Staff.class));
    }
    public Staff getStaffByStaffID(int staffID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from staff where staffID=?";
        return runner.query(sql,new BeanHandler<Staff>(Staff.class),staffID);
    }
    public boolean insertStaff(Staff staff) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="insert into staff(staffName,staffGrade,deptID,basicSalary,safeSalary) values(?,?,?,?,?)";
        int result=runner.execute(sql,staff.getStaffName(),staff.getStaffGrade(),staff.getDeptID(),staff.getBasicSalary(),staff.getSafeSalary());
        return result>=1?true:false;
    }
    public boolean deleteStaff(int staffID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="delete from staff where staffID=?";
        int result=runner.execute(sql,staffID);
        return result>=1?true:false;
    }
    public boolean updateStaff(Staff staff) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="update staff set staffName=?,staffGrade=?,deptID=?,basicSalary=?,safeSalary=? where staffID=?";
        int result=runner.execute(sql,staff.getStaffName(),staff.getStaffGrade(),staff.getDeptID(),staff.getBasicSalary(),staff.getSafeSalary(),staff.getStaffID());
        return result>=1?true:false;
    }
    public List<Staff> getAllStaffBydeptID(int deptID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from staff where deptID=?";
        return runner.query(sql,new BeanListHandler<Staff>(Staff.class),deptID);
    }
    public String getStaffNameByStaffID(int staffID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select staffName from staff where staffID=?";
        return runner.query(sql,new ScalarHandler<String>(),staffID);
    }
}
