package servlet;

import model.Staff;
import services.SalaryServer;
import services.StaffServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateYearSalaryServlet",urlPatterns = "/updateYearSalary")
public class UpdateYearSalaryServlet extends HttpServlet {
    private StaffServer staffServer=new StaffServer();
    private SalaryServer salaryServer=new SalaryServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int staffID=Integer.parseInt(request.getParameter("staffID").toString());
        int salaryYear=Integer.parseInt(request.getParameter("salaryYear").toString());
        int type=Integer.parseInt(request.getParameter("type").toString());
        Staff staff=staffServer.queryStaffByStaffID(staffID);
        boolean result=false;
        if(staff!=null)
        {
            result=salaryServer.updateYearSalary(staff,salaryYear);
            if(result)
            {
                result=true;
            }
        }
        if(type==1)
        {
            response.sendRedirect("/showStaffSalaryTable?staffID="+staff.getStaffID());
        }
        else
        {
            response.sendRedirect("/showDeptSalaryTable?deptID="+staff.getDeptID());
        }
    }
}
