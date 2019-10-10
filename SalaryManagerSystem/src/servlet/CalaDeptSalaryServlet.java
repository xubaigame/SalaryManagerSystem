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
import java.util.List;

@WebServlet(name = "CalaDeptSalaryServlet",urlPatterns = "/calaDeptSalary")
public class CalaDeptSalaryServlet extends HttpServlet {
    private StaffServer staffServer=new StaffServer();
    private SalaryServer salaryServer=new SalaryServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deptID=Integer.parseInt(request.getParameter("deptID").toString());
        List<Staff> staffList=staffServer.getAllStaffBydeptID(deptID);
        if(staffList!=null)
        {
            boolean result= salaryServer.CalaDeptSalary(staffList);
            if(result)
            {
                response.sendRedirect("/showDeptSalaryTable?deptID="+deptID);
            }
            else
            {
                response.sendRedirect("/showDeptSalaryTable?deptID="+deptID);
            }

        }
        else
        {
            response.sendRedirect("/showDeptSalaryTable?deptID="+deptID);
        }
    }
}
