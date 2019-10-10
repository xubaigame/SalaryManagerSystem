package servlet;

import model.Dept;
import services.DeptServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowDeptListServlet",urlPatterns = "/showAllDeptList")
public class ShowDeptListServlet extends HttpServlet {
    private DeptServer deptServer=new DeptServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Dept> deptList=deptServer.getAllDept();
        if(deptList!=null)
        {
            request.setAttribute("deptList",deptList);
        }
        else
        {
            request.setAttribute("error","没有查询到部门信息");
        }
        request.getRequestDispatcher("/deptinfoManager.jsp").forward(request,response);
    }
}
