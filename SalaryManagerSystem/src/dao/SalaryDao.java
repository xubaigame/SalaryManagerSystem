package dao;

import model.Salary;
import model.Staff;
import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBUtils;

import javax.management.Query;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDao {
    public boolean inputSalesInfo(List<Salary> salaryList) throws SQLException
    {
        Connection connection=null;
        PreparedStatement preparedStatement;
        boolean result=false;
        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false);
            String sql="insert into salary(staffID,saleSalary,salaryYear,salaryMonth,yearItem,flag) values(?,?,?,?,?,?)";
            for (int i=0;i<salaryList.size();i++)
            {
                Salary salary=salaryList.get(i);
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,salary.getStaffID());
                preparedStatement.setInt(2,salary.getSaleSalary());
                preparedStatement.setInt(3,salary.getSalaryYear());
                preparedStatement.setInt(4,salary.getSalaryMonth());
                preparedStatement.setInt(5,salary.getYearItem());
                preparedStatement.setInt(6,salary.getFlag());
                preparedStatement.execute();
            }
            connection.commit();
            result=true;
        }
        catch (Exception e)
        {
            System.out.println("SalaryDao-inputSalesInfo Error: "+e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println("SalaryDao-inputSalesInfo Error: "+e);
            }
            result=false;
        }
        return result;
    }
    public boolean CalaStaffMonthSalary(Staff staff,int year, int month) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        //String sql="select * from salary where staffID=? and salaryYear=? and salaryMonth=?";
        String sql="select * from salary where staffID=? and salaryYear=? and salaryMonth=? and flag=0";
        Salary salary= (Salary) runner.query(sql,new BeanHandler<Salary>(Salary.class),staff.getStaffID(),year,month);
        if(salary.getFlag()==1)
            return false;
        if(staff.getStaffGrade()=="0")
        {
            salary.setSalaryTotal((int)(salary.getSaleSalary()*0.05)+staff.getBasicSalary()-staff.getSafeSalary());

        }
        else if(staff.getStaffGrade()=="1")
        {
            //salary.setSalaryTotal((int)(getAllStaffSaleSalarySalary(staff.getDeptID())*0.01)+staff.getBasicSalary()-staff.getSafeSalary());
        }
        salary.setFlag(1);
        return updata(salary);
    }

    public boolean updata(Salary salary) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="update salary set totalsalary =?,flag =? where staffid=?";
        int result=runner.execute(sql,salary.getSalaryTotal(),salary.getFlag(),salary.getStaffID());
        return result==1?true:false;
    }
    public int getAllStaffSaleSalarySalary(int deptid,int salaryYear,int salaryMonth) throws SQLException {
        QueryRunner runner =new QueryRunner(DBUtils.getDataSource());
        String sql="select sum(saleSalary) from salary,staff where salary.staffid=staff.staffid and staff.deptID=? and salary.salaryYear=? and salary.salaryMonth=?";
        return Integer.parseInt(runner.query(sql,new ScalarHandler<BigDecimal>(),deptid,salaryYear,salaryMonth).toString());
    }
    public List<Salary> getAllStaffSaleSalarySalary(int salaryYear,int salaryMonth) throws SQLException {
        QueryRunner runner =new QueryRunner(DBUtils.getDataSource());
        String sql="select * from salary,staff where salary.staffid=staff.staffid and salary.salaryYear=? and salary.salaryMonth=?";
        return runner.query(sql,new BeanListHandler<Salary>(Salary.class),salaryYear,salaryMonth);
    }
    public List<Salary> getAllSalaryByStaffID(int staffID) throws SQLException {
        QueryRunner runner =new QueryRunner(DBUtils.getDataSource());
        String sql="select * from salary where staffID=?";
        return runner.query(sql,new BeanListHandler<Salary>(Salary.class),staffID);
    }
    public List<Salary> getAllSalaryByStaffID(int staffID,int salaryYear) throws SQLException {
        QueryRunner runner =new QueryRunner(DBUtils.getDataSource());
        String sql="select * from salary where staffID=? and salaryYear=?";
        return runner.query(sql,new BeanListHandler<Salary>(Salary.class),staffID,salaryYear);
    }
    public List<Salary> getAllSalaryByStaffIDAndFlag(int staffID,int flag) throws SQLException {
        QueryRunner runner =new QueryRunner(DBUtils.getDataSource());
        String sql="select * from salary where staffID=? and flag=?";
        return runner.query(sql,new BeanListHandler<Salary>(Salary.class),staffID,flag);
    }
    public boolean calaStaffSalaryByStaffID(Staff staff) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        boolean result=true;
        String sql;
        List<Salary> salaryList=getAllSalaryByStaffIDAndFlag(staff.getStaffID(),0);

        for(int i=0;i<salaryList.size();i++)
        {
            int yearSalary=0;
            int tempYearSalary=0;
            Salary salary=salaryList.get(i);

            if(salary.getSalaryMonth()==12)
            {

                List<Salary> temp=getAllSalaryByStaffID(staff.getStaffID(),salary.getSalaryYear());
                for(int j=0;j<temp.size();j++)
                {
                    tempYearSalary+=temp.get(j).getSaleSalary();
                }
            }
            yearSalary=(int)(tempYearSalary*0.01);
            if(staff.getStaffGrade().equals("0"))
            {
                salary.setBonus((int)(salary.getSaleSalary()*0.05)+yearSalary);
            }
            else if(staff.getStaffGrade().equals("1"))
            {
                salary.setBonus((int)(getAllStaffSaleSalarySalary(staff.getDeptID(),salary.getSalaryYear(),salary.getSalaryMonth())*0.01)+yearSalary);
            }
            salary.setSalaryTotal(salary.getSaleSalary()+salary.getBonus()+staff.getBasicSalary()-staff.getSafeSalary());
            salary.setFlag(1);
            sql="update salary set bonus=?,salaryTotal=?,flag=? where staffID=? and salaryYear=? and salaryMonth=?";
            int temp=runner.execute(sql,salary.getBonus(),salary.getSalaryTotal(),salary.getFlag(),salary.getStaffID(),salary.getSalaryYear(),salary.getSalaryMonth());
            if(temp==0)
            {
                result=false;
                break;
            }
        }
        return result;
    }
    public List<List<Salary>> getStaffSalaryByStaffList(List<Staff> staffList) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql;
        List<List<Salary>> salaryStaffList=new ArrayList<>();
        for(int i=0;i<staffList.size();i++)
        {
            sql="select * from salary,staff where staff.staffID=salary.staffID and staff.staffID=?";
            List<Salary> salaryList= runner.query(sql,new BeanListHandler<Salary>(Salary.class),staffList.get(i).getStaffID());
            salaryStaffList.add(salaryList);
        }
        return salaryStaffList;
    }
    public Salary getSalaryOfAStaff(int staffID,int salaryYear) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from salary where staffID=? and salaryYear=? and salaryMonth=12";
        return runner.query(sql,new BeanHandler<Salary>(Salary.class),staffID,salaryYear);
    }
    public boolean updateYearSalary(Staff staff,int salaryYear) throws SQLException {
        int yearSalary=0;
        int tempYearSalary=0;
        Salary salary=getSalaryOfAStaff(staff.getStaffID(),salaryYear);
        if(salary==null)
            return false;
        List<Salary> temp=getAllSalaryByStaffID(staff.getStaffID(),salaryYear);
        for(int j=0;j<temp.size();j++)
        {
            tempYearSalary+=temp.get(j).getSaleSalary();
        }
        yearSalary=(int)(tempYearSalary*0.01);
        if(staff.getStaffGrade().equals("0"))
        {
            salary.setBonus((int)(salary.getSaleSalary()*0.05)+yearSalary);
        }
        else if(staff.getStaffGrade().equals("1"))
        {
            salary.setBonus((int)(getAllStaffSaleSalarySalary(staff.getDeptID(),salary.getSalaryYear(),salary.getSalaryMonth())*0.01)+yearSalary);
        }
        salary.setSalaryTotal(salary.getSaleSalary()+salary.getBonus()+staff.getBasicSalary()-staff.getSafeSalary());
        salary.setFlag(1);
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="update salary set bonus=?,salaryTotal=?,flag=? where staffID=? and salaryYear=? and salaryMonth=?";
        int result=runner.execute(sql,salary.getBonus(),salary.getSalaryTotal(),salary.getFlag(),salary.getStaffID(),salary.getSalaryYear(),salary.getSalaryMonth());
        return result>=1?true:false;
    }
}
