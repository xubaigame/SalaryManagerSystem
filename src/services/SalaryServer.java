package services;

import dao.SalaryDao;
import model.Salary;
import model.Staff;

import java.sql.SQLException;
import java.util.List;

public class SalaryServer {
    private SalaryDao salaryDao=new SalaryDao();
    public boolean inputSalesInfo(List<Salary> salaryList)
    {
        boolean result=false;
        try {
            result=salaryDao.inputSalesInfo(salaryList);
        } catch (SQLException e) {
            System.out.println("SalaryServer-inputSalesInfo Error:" +e);
        }
        return result;
    }
    public boolean calaAllStaffMonthSalary(List<Staff> staffs,int year,int month) throws SQLException {
        for(int i=0;i<staffs.size();i++)
        {
            salaryDao.CalaStaffMonthSalary(staffs.get(i), year, month);
        }
        return true;
    }
    public List<Salary> queryStaffSalaryList(int staffID)
    {
        try {
            return salaryDao.getAllSalaryByStaffID(staffID);
        } catch (SQLException e) {
            System.out.println("SalaryServer-queryStaffSalaryList Error:" +e);
            return null;
        }
    }
    public boolean calaStaffSalary(Staff staff)
    {
        try {
            return salaryDao.calaStaffSalaryByStaffID(staff);
        } catch (SQLException e) {
            System.out.println("SalaryServer-calaStaffSalary Error:" +e);
            return false;
        }
    }
    public List<List<Salary>> getStaffTableOfDept(List<Staff> staffList)
    {
        try {
            return salaryDao.getStaffSalaryByStaffList(staffList);
        } catch (SQLException e) {
            System.out.println("SalaryServer-getStaffTableOfDept Error:" +e);
            return null;
        }
    }
    public boolean CalaDeptSalary(List<Staff> staffList)
    {
        for(int i=0;i<staffList.size();i++)
        {
            try {
                boolean result= salaryDao.calaStaffSalaryByStaffID(staffList.get(i));
                if(!result)
                    return false;
            } catch (SQLException e) {
                System.out.println("SalaryServer-CalaDeptSalary Error:" +e);
                return false;
            }
        }
        return true;
    }
    public boolean updateYearSalary(Staff staff,int salaryYear)
    {
        try {
            return salaryDao.updateYearSalary(staff,salaryYear);
        } catch (SQLException e) {
            System.out.println("SalaryServer-updateYearSalary Error:" +e);
            return false;
        }
    }
    public List<Salary> getAllStaffSaleSalarySalary(int salaryYear,int salaryMonth)
    {
        try {
            return salaryDao.getAllStaffSaleSalarySalary(salaryYear, salaryMonth);
        } catch (SQLException e) {
            System.out.println("SalaryServer-getStaffTableOfDept Error:" +e);
            return null;
        }
    }
}
