package servlet;

import model.Salary;
import model.Staff;
import services.SalaryServer;
import services.StaffServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowDeptSalaryTableServlet",urlPatterns = "/showDeptSalaryTable")
public class ShowDeptSalaryTableServlet extends HttpServlet {
    private StaffServer staffServer=new StaffServer();
    private SalaryServer salaryServer=new SalaryServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deptID=Integer.parseInt(request.getParameter("deptID").toString());
        List<Staff> staffList=staffServer.getAllStaffBydeptID(deptID);
        if(staffList!=null)
        {
            List<List<Salary>> salaryStaffList=salaryServer.getStaffTableOfDept(staffList);
            for(int i=0;i<salaryStaffList.size();i++)
            {
                if(salaryStaffList.get(i).size()<=0)
                    salaryStaffList.remove(i);
            }
            if(salaryStaffList!=null)
            {
                request.setAttribute("deptID",deptID);
                request.setAttribute("salaryStaffList",salaryStaffList);
                request.getRequestDispatcher("ShowDeptSalaryTable.jsp").forward(request,response);
            }
            else
            {
                request.setAttribute("error","未查询到部门下的员工的工资信息！");
                request.getRequestDispatcher("ShowDeptSalaryTable.jsp").forward(request,response);
            }
        }
        else
        {
            request.setAttribute("error","未查询到部门下的员工信息！");
            request.getRequestDispatcher("ShowDeptSalaryTable.jsp").forward(request,response);
        }
    }
}
