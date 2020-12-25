package servlet;

import model.Dept;
import services.DeptServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowEditDeptServlet",urlPatterns = "/showEditDept")
public class ShowEditDeptServlet extends HttpServlet {
    private DeptServer deptServer=new DeptServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deptID=Integer.parseInt(request.getParameter("deptID").toString());
        if(deptID==-1)
        {
            Dept dept=new Dept();
            dept.setDeptID(-1);
            request.setAttribute("dept", dept);
            request.getRequestDispatcher("deptEdit.jsp").forward(request,response);
        }
        else
        {
            Dept dept=deptServer.queryDeptByDeptID(deptID);
            if(dept!=null)
            {
                request.setAttribute("dept", dept);
                request.getRequestDispatcher("deptEdit.jsp").forward(request,response);
            }
            else
            {
                response.sendRedirect("deptinfoManage.jsp");
            }
        }
    }
}
