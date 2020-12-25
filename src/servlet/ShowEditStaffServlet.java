package servlet;

import model.Dept;
import model.Staff;
import services.DeptServer;
import services.StaffServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowEditStaffServlet",urlPatterns = "/showEditStaff")
public class ShowEditStaffServlet extends HttpServlet {
    private StaffServer staffServer=new StaffServer();
    private DeptServer deptServer=new DeptServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int staffID=Integer.parseInt(request.getParameter("staffID").toString());
        List<Dept> deptList= deptServer.getAllDept();
        if(deptList==null)
        {
            response.sendRedirect("/staffinfoManager.jsp");
        }
        request.setAttribute("deptList",deptList);
        if(staffID==-1)
        {
            Staff staff=new Staff();
            staff.setStaffID(-1);
            request.setAttribute("staff",staff);
            request.getRequestDispatcher("staffEdit.jsp").forward(request,response);
        }
        else
        {
            Staff staff=staffServer.queryStaffByStaffID(staffID);
            if(staff!=null)
            {
                request.setAttribute("staff",staff);
                request.getRequestDispatcher("staffEdit.jsp").forward(request,response);
            }
            else {
                response.sendRedirect("staffinfoManager.jsp");
            }
        }

    }
}
