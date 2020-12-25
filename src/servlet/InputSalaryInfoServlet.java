package servlet;

import model.Salary;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.SalaryServer;
import utils.ExcelUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@WebServlet(name = "InputSalaryInfoServlet",urlPatterns = "/inputSalaryFile")
public class InputSalaryInfoServlet extends HttpServlet {
    private SalaryServer salaryServer=new SalaryServer();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        boolean reulst=false;
        try {
            List<FileItem> list = upload.parseRequest(request);
            String year;
            String month;
            int pageNumber =1;
            int type=0;
            for(FileItem item:list) {
                if(item.isFormField()) {
                    switch(item.getFieldName()) {
                        case "salaryYear":
                            year= item.getString("utf-8");
                            break;
                        case "salaryMonth":
                            month= item.getString("utf-8");
                            break;
                    }
                }else {
                    if(item.getInputStream().available()<=0)continue;
                    String fileName = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = "/"+new Date().getTime()+fileName;
                    String path = this.getServletContext().getRealPath("/salaryTable")+fileName;
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] buffer = new byte[1024];
                    int len=0;
                    while( (len=in.read(buffer))>0 ) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    List<Salary> salaryList= ExcelUtils.readExcel(path);
                    reulst= salaryServer.inputSalesInfo(salaryList);

                }
            }
            if(reulst)
            {
                request.setAttribute("success", "录入成功！");
                request.getRequestDispatcher("salaryinfoManager.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("error", "录入失败，请重试！");
                request.getRequestDispatcher("salaryinfoManager.jsp").forward(request, response);;
            }
        } catch (FileUploadException e) {
            request.setAttribute("error", "发生异常，请重试！"+e);
            request.getRequestDispatcher("salaryinfoManager.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "发生异常，请重试！"+e);
            request.getRequestDispatcher("salaryinfoManager.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
