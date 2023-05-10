package controller;


import Dao.PersonDao;
import beans.Person;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "downloadServlet", value = "/downloadServlet")
public class downloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           WritableWorkbook wwb = null;

           //创建可写入的Excel工作簿
           String fileName ="D:\\IDEA\\IdeaProjects\\实验十三 综合实验\\web\\outport\\student.xls";
           File file = new File(fileName);
           if (!file.exists()) {
               file.createNewFile();
           }
           //以fileName为文件名来创建一个Workbook
           wwb = Workbook.createWorkbook(file);

           // 创建工作表
           WritableSheet ws = wwb.createSheet("Test Shee 1", 0);

           //查询数据库中所有的数据
           PersonDao std = new PersonDao();
           List<Person> list= std.findByKey("");
           //要插入到的Excel表格的行号，默认从0开始
           Label labelId= new Label(0, 0, "编号(ID)");
           Label labelNick = new Label(1,0,"昵称(NICK)");
           Label labelPassword= new Label(2, 0, "密码(PASSWROD)");
           Label labelStuno= new Label(3, 0, "学号(STUNO)");
           Label labelStuname= new Label(4, 0, "姓名(STUNAME)");
           Label labelSex= new Label(5, 0, "性别(SEX)");
           Label labelAge= new Label(6, 0, "年龄(AGE)");
           Label labelEmail= new Label(7, 0, "邮箱(EMAIL)");
           Label labelPhone= new Label(8, 0, "电话(PHONE)");
           Label labelInrtoduce= new Label(9, 0, "介绍(INTRODUCE)");

           ws.addCell(labelId);
           ws.addCell(labelNick);
           ws.addCell(labelPassword);
           ws.addCell(labelStuno);
           ws.addCell(labelStuname);
           ws.addCell(labelSex);
           ws.addCell(labelAge);
           ws.addCell(labelEmail);
           ws.addCell(labelPhone);
           ws.addCell(labelInrtoduce);

           for (int i = 0; i < list.size(); i++) {

               Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
               Label labelNick_i= new Label(1, i+1, list.get(i).getNick());
               Label labelPassword_i= new Label(2, i+1, list.get(i).getPassword());
               Label labelStuno_i= new Label(3, i+1, list.get(i).getStuno()+"");
               Label labelStuname_i= new Label(4, i+1, list.get(i).getStuname()+"");
               Label labelSex_i= new Label(5, i+1, list.get(i).getSex()+"");
               Label labelAge_i= new Label(6, i+1, list.get(i).getAge()+"");
               Label labelPhone_i= new Label(7, i+1, list.get(i).getPhone()+"");
               Label labelEmail_i= new Label(8, i+1, list.get(i).getEmail()+"");
               Label labelIntpoduce_i= new Label(9, i+1, list.get(i).getIntpoduce()+"");
               ws.addCell(labelId_i);
               ws.addCell(labelNick_i);
               ws.addCell(labelPassword_i);
               ws.addCell(labelStuno_i);
               ws.addCell(labelStuname_i);
               ws.addCell(labelSex_i);
               ws.addCell(labelAge_i);
               ws.addCell(labelPhone_i);
               ws.addCell(labelEmail_i);
               ws.addCell(labelIntpoduce_i);
           }

           //写进文档
           wwb.write();
           // 关闭Excel工作簿对象
           System.out.println("数据导出成功!");
           wwb.close();
           response.setContentType("text/html;charset=UTF-8");//中文需设置编码
           response.getWriter().write("<script>alert('数据导出成功!');</script>");
           response.getWriter().write("<script language=javascript>window.close()</script>");
       }catch (Exception e){
           e.printStackTrace();
           response.sendRedirect("admin.jsp");
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
