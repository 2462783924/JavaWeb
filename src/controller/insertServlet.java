package controller;

import Dao.PersonDao;
import beans.Person;
import cn.hutool.crypto.digest.DigestUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "insertServlet", value = "/insertServlet")
public class insertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PersonDao dao = new PersonDao();
        //判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
        if (ServletFileUpload.isMultipartContent(request)) {
            //创建FileItemFactory工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类ServeltFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            //解析上传的数据，得到每一个表单项FileItem
            try {
                List<FileItem> list = servletFileUpload.parseRequest(request);

                String passwordMD5 = DigestUtil.md5Hex(list.get(1).getString("UTF-8"));

                Person ps = new Person(0, list.get(0).getString("UTF-8"), passwordMD5, list.get(2).getString("UTF-8"), list.get(3).getString("UTF-8"), list.get(4).getString("UTF-8"), list.get(5).getString("UTF-8"), list.get(6).getString("UTF-8"), list.get(7).getString("UTF-8"), list.get(8).getName(), list.get(9).getString("UTF-8"));

                List<Person> psList = new PersonDao().searchPassword(list.get(0).getString("UTF-8"));
                String msg = null;
                if (psList.isEmpty()) {
                    String s = String.valueOf(dao.insertStudent(ps));
                    //response.getWriter().write("<script>alert('数据添加成功!');</script>");
                    list.get(8).write(new File("D:\\IDEA\\IdeaProjects\\实验十三 综合实验\\web\\file\\" + list.get(7).getName()));
                    msg="数据添加成功!";
                    System.out.println("数据添加成功");
                } else {
                    System.out.println("昵称重复");
                    msg="数据添加失败,昵称重复!";
                }
                Gson gson = new Gson();
                String stuStr = gson.toJson(msg);
                response.getWriter().write(stuStr);
            } catch (Exception e) {
                Gson gson = new Gson();
                String stuStr = gson.toJson("数据添加失败！");
                response.getWriter().write(stuStr);
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
