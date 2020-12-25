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
import java.io.*;
import java.util.List;

@WebServlet(name = "ShowStaffListServlet",urlPatterns = "/showAllStaffList")
public class ShowStaffListServlet extends HttpServlet {
    private StaffServer staffServer=new StaffServer();
    private DeptServer deptServer=new DeptServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String path = this.getServletContext().getRealPath("/salaryTable")+"/1559033864715.xlsx";
//        File file=new File(path);
//        InputStream inputStream=new FileInputStream(file);
//        response.setContentType("multipart/form-data");
//        //为文件重新设置名字，采用数据库内存储的文件名称
//        response.addHeader("Content-Disposition", "attachment; filename=\"" + new String("aaa.xlsx"));
//        OutputStream out = response.getOutputStream();
//        //读取文件流
//        int len = 0;
//        byte[] buffer = new byte[1024 * 10];
//        while ((len = inputStream.read(buffer)) != -1){
//            out.write(buffer,0,len);
//        }
//        out.flush();
//        out.close();
//        return;

        List<Dept> deptList=deptServer.getAllDept();
        if(deptList!=null)
        {
            List<Staff> staffList= staffServer.getAllStaff();
            if(staffList!=null)
            {
                for(int i=0;i<deptList.size();i++)
                {
                    for (int j=0;j<staffList.size();j++)
                    {
                        if(staffList.get(j).getDeptID()==deptList.get(i).getDeptID())
                            staffList.get(j).setDeptName(deptList.get(i).getDeptName());
                    }
                }
                request.setAttribute("staffList",staffList);
            }
            else
            {
                request.setAttribute("error","没有查询到员工信息");
            }
        }
        else
        {
            request.setAttribute("error","没有查询到部门信息");
        }
        request.getRequestDispatcher("/staffinfoManager.jsp").forward(request,response);
    }
}
