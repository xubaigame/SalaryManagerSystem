package servlet;

import model.Dept;
import org.apache.commons.beanutils.BeanUtils;
import services.DeptServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "EditDeptServlet",urlPatterns = "/editDept")
public class EditDeptServlet extends HttpServlet {
    private DeptServer deptServer=new DeptServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Dept dept=new Dept();
        try {
            BeanUtils.copyProperties(dept, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(dept.getDeptID()==-1)
        {
            boolean result=deptServer.createDept(dept);
            if(result)
            {
                response.sendRedirect("/showAllDeptList");
            }
            else {
                request.setAttribute("error","添加失败!");
                request.getRequestDispatcher("deptEdit.jsp");
            }
        }
        else
        {
            boolean result=deptServer.updateDept(dept);
            if(result)
            {
                response.sendRedirect("/showAllDeptList");
            }
            else {
                request.setAttribute("error","修改失败!");
                request.getRequestDispatcher("deptEdit.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
