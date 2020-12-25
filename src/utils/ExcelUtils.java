package utils;
import dao.SalaryDao;
import model.Salary;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.SalaryServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static SalaryServer salaryServer=new SalaryServer();
    public static List<Salary> readExcel(String path) throws Exception {
        List<Salary> salaryList=new ArrayList<>();
        //1.读取Excel文档对象
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook(new FileInputStream(path));
        //2.获取要解析的表格（第一个表格）
        XSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        //获得最后一行的行号
        XSSFRow row = sheet.getRow(0);
        int year,month;
        row.getCell(1).setCellType(CellType.STRING);
        year=Integer.parseInt(row.getCell(1).getStringCellValue()) ;
        row.getCell(3).setCellType(CellType.STRING);
        month=Integer.parseInt(row.getCell(3).getStringCellValue()) ;
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 2; i <= lastRowNum; i++) {//遍历每一行
            Salary salary=new Salary();
            //3.获得要解析的行
            row = sheet.getRow(i);
            //4.获得每个单元格中的内容（String）

            row.getCell(0).setCellType(CellType.STRING);
            salary.setStaffID(Integer.parseInt(row.getCell(0).getStringCellValue()));
            row.getCell(2).setCellType(CellType.STRING);
            salary.setSaleSalary(Integer.parseInt(row.getCell(2).getStringCellValue()));
            row.getCell(1).setCellType(CellType.STRING);
            String name = row.getCell(1).getStringCellValue();
            System.out.println(salary.getStaffID()+"--"+name+"--"+salary.getSaleSalary());
            salary.setSalaryYear(year);
            salary.setSalaryMonth(month);
            salary.setYearItem(0);
            salary.setFlag(0);
            salaryList.add(salary);

        }
        return salaryList;
    }
    public static void WriteExcvel(List<Salary> salaryList,String path,String name) throws Exception {
        XSSFWorkbook xssfWorkbook =new XSSFWorkbook();
        XSSFSheet sheet=xssfWorkbook.createSheet("员工工资表");
        XSSFRow row=sheet.createRow(0);
        row.createCell(0).setCellValue("年份");
        row.createCell(1).setCellValue(salaryList.get(0).getSalaryYear());
        row.createCell(2).setCellValue("月份");
        row.createCell(3).setCellValue(salaryList.get(0).getSalaryMonth());
        row=sheet.createRow(1);
        row.createCell(0).setCellValue("员工编号");
        row.createCell(1).setCellValue("员工姓名");
        row.createCell(2).setCellValue("销售金额");
        row.createCell(3).setCellValue("奖金");
        row.createCell(4).setCellValue("工资总额");
        for(int i=0;i<salaryList.size();i++)
        {
            Salary salary=salaryList.get(i);
            row=sheet.createRow(i+2);
            row.createCell(0).setCellValue(salary.getStaffID());
            row.createCell(1).setCellValue(salary.getStaffName());
            row.createCell(2).setCellValue(salary.getSaleSalary());
            row.createCell(3).setCellValue(salary.getBonus());
            row.createCell(4).setCellValue(salary.getSalaryTotal());

        }
        OutputStream outputStream= new FileOutputStream(path+name);
        xssfWorkbook.write(outputStream);
        outputStream.close();
    }
}
