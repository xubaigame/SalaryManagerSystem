package servlet;

import model.Salary;
import org.omg.CORBA.FREE_MEM;
import services.SalaryServer;
import utils.ExcelUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet(name = "OutputSalaryInfoServlet",urlPatterns = "/outputSalaryInfo")
public class OutputSalaryInfoServlet extends HttpServlet {
    private SalaryServer salaryServer=new SalaryServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int year=Integer.parseInt(request.getParameter("salaryYear"));
        int month=Integer.parseInt(request.getParameter("salaryMonth"));
        List<Salary> salaryList=salaryServer.getAllStaffSaleSalarySalary(year,  month);
        if(salaryList!=null&&salaryList.size()>0)
        {
            String path = this.getServletContext().getRealPath("/salaryTable")+"/";
            String name = salaryList.get(0).getSalaryYear()+"-"+salaryList.get(0).getSalaryMonth()+"_SalaryTable.xlsx";
            try {
                ExcelUtils.WriteExcvel(salaryList, path, name);
                File file=new File(path+name);
                InputStream inputStream=new FileInputStream(file);
                response.setCharacterEncoding("utf-8");
                response.setContentType("multipart/form-data");
                //为文件重新设置名字，采用数据库内存储的文件名称
                System.err.println(name);
                response.addHeader("Content-Disposition", "attachment; filename=\"" + name);
                OutputStream out = response.getOutputStream();
                //读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = inputStream.read(buffer)) != -1){
                    out.write(buffer,0,len);
                }
                out.flush();
                out.close();
            } catch (Exception e) {
                System.out.println("导出工资表发生错误:"+e);
                response.sendRedirect("salaryinfoManager.jsp");
            }
        }
        else
        {
            response.sendRedirect("salaryinfoManager.jsp");
        }
    }
}
