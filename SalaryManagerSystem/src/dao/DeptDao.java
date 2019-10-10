package dao;

import model.Dept;
import model.Staff;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DBUtils;

import java.sql.SQLException;
import java.util.List;

public class DeptDao {
    public List<Dept> getAllDept() throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from dept";
        return runner.query(sql,new BeanListHandler<Dept>(Dept.class));
    }
    public boolean deleteDept(int deptID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from staff where deptID=?";
        List<Staff> staffList=runner.query(sql,new BeanListHandler<Staff>(Staff.class),deptID);
        for(int i=0;i<staffList.size();i++)
        {
            sql="update staff set deptID=? where staffID=?";
            runner.execute(sql,1,staffList.get(i).getStaffID());
        }
        sql="delete from dept where deptID=?";
        int result = runner.execute(sql,deptID);
        return result>=1?true:false;
    }
    public Dept getDeptByDeptID(int deptID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from dept where deptID=?";
        return runner.query(sql,new BeanHandler<Dept>(Dept.class),deptID);
    }
    public boolean createDept(Dept dept) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="insert into dept(deptName) values(?)";
        int result = runner.execute(sql,dept.getDeptName());
        return result>=1?true:false;
    }
    public boolean updateDept(Dept dept) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="update dept set deptName=? where deptID=?";
        int result = runner.execute(sql,dept.getDeptName(),dept.getDeptID());
        return result>=1?true:false;
    }
}
