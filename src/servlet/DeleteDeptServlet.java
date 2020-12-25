package servlet;

import services.DeptServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteDeptServlet",urlPatterns = "/deleteDept")
public class DeleteDeptServlet extends HttpServlet {
    private DeptServer deptServer=new DeptServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deptID=Integer.parseInt(request.getParameter("deptID").toString());
        boolean result=deptServer.deleteDept(deptID);
        response.sendRedirect("/showAllDeptList");
    }
}
