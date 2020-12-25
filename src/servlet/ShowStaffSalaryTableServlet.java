package servlet;

import model.Salary;
import services.SalaryServer;
import services.StaffServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowStaffSalaryTableServlet",urlPatterns = "/showStaffSalaryTable")
public class ShowStaffSalaryTableServlet extends HttpServlet {
    private SalaryServer salaryServer=new SalaryServer();
    private StaffServer staffServer=new StaffServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int staffID=Integer.parseInt(request.getParameter("staffID").toString());
        List<Salary> salaryList= salaryServer.queryStaffSalaryList(staffID);
        String staffName=staffServer.queryStaffName(staffID);
        if(salaryList!=null&&staffName!=null)
        {
            request.setAttribute("staffName",staffName);
            request.setAttribute("salaryList", salaryList);
            request.getRequestDispatcher("ShowStaffSalaryTable.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("error", "未查询到该员工的工资信息");
            request.getRequestDispatcher("ShowStaffSalaryTable.jsp").forward(request, response);
        }
    }
}
