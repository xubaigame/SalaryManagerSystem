package services;

import dao.DeptDao;
import model.Dept;

import java.sql.SQLException;
import java.util.List;

public class DeptServer {
    private DeptDao deptDao=new DeptDao();
    public List<Dept> getAllDept()
    {
        try {
            return deptDao.getAllDept();
        } catch (SQLException e) {
            System.out.println("DeptServer-getAllDept Error:"+e);
            return null;
        }
    }
    public boolean deleteDept(int deptID)
    {
        try {
            return deptDao.deleteDept(deptID);
        } catch (SQLException e) {
            System.out.println("DeptServer-deleteDept Error:"+e);
            return false;
        }
    }
    public Dept queryDeptByDeptID(int deptID)
    {
        try {
            return deptDao.getDeptByDeptID(deptID);
        } catch (SQLException e) {
            System.out.println("DeptServer-queryDeptByDeptID Error:"+e);
            return null;
        }
    }
    public boolean createDept(Dept dept)
    {
        try {
            return deptDao.createDept(dept);
        } catch (SQLException e) {
            System.out.println("DeptServer-createDept Error:"+e);
            return false;
        }
    }
    public boolean updateDept(Dept dept)
    {
        try {
            return deptDao.updateDept(dept);
        } catch (SQLException e) {
            System.out.println("DeptServer-updateDept Error:"+e);
            return false;
        }
    }
}
