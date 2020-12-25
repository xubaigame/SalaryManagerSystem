package services;

import dao.StaffDao;
import model.Staff;
import org.apache.commons.compress.utils.ByteUtils;

import java.sql.SQLException;
import java.util.List;

public class StaffServer {
    private StaffDao staffDao=new StaffDao();
    public List<Staff> getAllStaff()
    {
        try {
            return staffDao.getAllStaff();
        } catch (SQLException e) {
            System.out.println("StaffServer-getAllStaff Error:"+e);
            return null;
        }
    }
    public boolean createStaff(Staff staff)
    {
        try {
            return staffDao.insertStaff(staff);
        } catch (SQLException e) {
            System.out.println("StaffServer-createStaff Error:"+e);
            return false;
        }
    }
    public boolean deleteStaff(int staffID)
    {
        try {
            return staffDao.deleteStaff(staffID);
        } catch (SQLException e) {
            System.out.println("StaffServer-deleteStaff Error:"+e);
            return false;
        }
    }
    public Staff queryStaffByStaffID(int staffID)
    {
        try {
            return staffDao.getStaffByStaffID(staffID);
        } catch (SQLException e) {
            System.out.println("StaffServer-queryStaffByStaffID Error:"+e);
            return null;
        }
    }
    public boolean updateStaff(Staff staff)
    {
        try {
            return staffDao.updateStaff(staff);
        } catch (SQLException e) {
            System.out.println("StaffServer-deleteStaff Error:"+e);
            return false;
        }
    }
    public List<Staff> getAllStaffBydeptID(int deptID)
    {
        try {
            return staffDao.getAllStaffBydeptID(deptID);
        } catch (SQLException e) {
            System.out.println("StaffServer-getAllStaffBydeptID Error:"+e);
            return null;
        }
    }
    public String queryStaffName(int staffID)
    {
        try {
            return staffDao.getStaffNameByStaffID(staffID);
        } catch (SQLException e) {
            System.out.println("StaffServer-queryStaffName Error:"+e);
            return null;
        }
    }
}
