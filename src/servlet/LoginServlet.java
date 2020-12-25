package servlet;

import model.User;
import org.apache.commons.beanutils.BeanUtils;
import services.UserServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserServer userServer=new UserServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User();
        try {
            BeanUtils.copyProperties(user, request.getParameterMap());
            boolean result=userServer.Login(user);
            if(result)
            {
                request.getSession().setAttribute("user", user);
                response.sendRedirect("/index.jsp");
            }
            else {
                request.setAttribute("error", "用户名或密码错误！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (IllegalAccessException e) {
            request.setAttribute("error", "未知错误！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            request.setAttribute("error", "未知错误！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
