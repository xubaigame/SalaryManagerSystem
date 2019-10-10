package servlet;

import model.Dept;
import model.Staff;
import org.apache.commons.beanutils.BeanUtils;
import services.DeptServer;
import services.StaffServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet(name = "EditStaffServlet",urlPatterns = "/editStaff")
public class EditStaffServlet extends HttpServlet {
    private StaffServer staffServer= new StaffServer();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Staff staff=new Staff();
        try {
            BeanUtils.copyProperties(staff, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(staff.getStaffID()==-1)
        {
            boolean result=staffServer.createStaff(staff);
            if(result)
            {
                response.sendRedirect("/showAllStaffList");
            }
            else {
                request.setAttribute("error","添加失败!");
                request.getRequestDispatcher("/staffEdit.jsp");
            }
        }
        else
        {
            boolean result=staffServer.updateStaff(staff);
            if(result)
            {
                response.sendRedirect("/showAllStaffList");
            }
            else {
                request.setAttribute("error","修改失败!");
                request.getRequestDispatcher("/staffEdit.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
