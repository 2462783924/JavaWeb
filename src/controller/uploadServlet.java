package controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.jspsmart.upload.SmartUpload;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "uploadServlet", value = "/uploadServlet")
public class uploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        SmartUpload su = new SmartUpload();
        Connection con = null;
        PreparedStatement pstat = null;
        try {
            su.initialize(this.getServletConfig(), request, response);
            su.upload();
            //获取根目录下upload绝对路径
            String path = getServletContext().getRealPath("/") + "upload\\";
            System.out.println(path);
            su.save(path);
            //获取上传文件名
            String filename = su.getFiles().getFile(0).getFileName();
            Workbook book = Workbook.getWorkbook(new File(path + filename));
            Sheet sheet = book.getSheet(0);
            int rowCount = sheet.getRows();
            int inputCount = 0;
            for (int i = 1; i < rowCount; i++) {
                Cell ID = sheet.getCell(0, i);
                int id = Integer.parseInt(ID.getContents());
                Cell Nick = sheet.getCell(1, i);
                String nick = Nick.getContents().toString();
                Cell Password = sheet.getCell(2, i);
                String password = Password.getContents().toString();
                //String passwordMD5 = DigestUtil.md5Hex(password);
                Cell Stuno = sheet.getCell(3, i);
                String stuno = Stuno.getContents().toString();
                Cell Stuname = sheet.getCell(4, i);
                String stuname = Stuname.getContents().toString();
                Cell Sex = sheet.getCell(5, i);
                String sex = Sex.getContents().toString();
                Cell Age = sheet.getCell(6, i);
                String age = Age.getContents().toString();
                Cell Phone = sheet.getCell(7, i);
                String phone = Phone.getContents().toString();
                Cell Email = sheet.getCell(8,i);
                String email = Email.getContents().toString();
                Cell Pic = sheet.getCell(9, i);
                String pic = Pic.getContents().toString();
                Cell Intpoduce = sheet.getCell(10, i);
                String intpoduce = Intpoduce.getContents().toString();
                if (String.valueOf(id).length() > 0) {
                    String sql = "INSERT INTO stuinfo ( `NICK`, `PASSWORD`, `STUNO`, `STUNAME`, `SEX`, `AGE`, `PHONE`, `EMAIL`, `PIC`, `INTPODUCE`) VALUES (?,?,?,?,?,?,?,?,?,?)";
                    con = DBConnection.getConnection();
                    pstat = con.prepareStatement(sql);
                    pstat.setString(1, nick);
                    pstat.setString(2, DigestUtil.md5Hex(password));
                    pstat.setString(3, stuno);
                    pstat.setString(4, stuname);
                    pstat.setString(5, sex);
                    pstat.setString(6, age);
                    pstat.setString(7, phone);
                    pstat.setString(8, email);
                    pstat.setString(9, pic);
                    pstat.setString(10, intpoduce);
                    int count = pstat.executeUpdate();
                    inputCount = inputCount + count;
                }
            }
            book.close();
            HttpSession session = request.getSession();
            String msg = "上传文件" + filename + "成功，导入" + inputCount + "条数据";
            session.setAttribute("message",msg);
            response.sendRedirect("uploadUser.jsp");
            System.out.println("upload success");
        } catch (Exception e) {
            System.out.println("upload fail");
            e.printStackTrace();
        } finally {
            DBConnection.free(con, null, null);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
