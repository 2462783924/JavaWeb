package controller;

import Dao.PersonDao;
import beans.Person;
import cn.hutool.crypto.digest.DigestUtil;
import com.google.gson.Gson;
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

@WebServlet(name = "modifyServlet", value = "/modifyServlet")
public class modifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PersonDao dao = new PersonDao();
        if (ServletFileUpload.isMultipartContent(request)) {
            //创建FileItemFactory工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类ServeltFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            //解析上传的数据，得到每一个表单项FileItem
            try {
                List<FileItem> list = servletFileUpload.parseRequest(request);
                String oldnick = list.get(0).getString("UTF-8");
                String newnick = list.get(2).getString("UTF-8");

                String passwordMD5 = DigestUtil.md5Hex(list.get(3).getString("UTF-8"));
                Person st = new Person(Integer.parseInt(list.get(1).getString("UTF-8")), list.get(2).getString("UTF-8"), passwordMD5, list.get(4).getString("UTF-8"), list.get(5).getString("UTF-8"), list.get(6).getString("UTF-8"), list.get(7).getString("UTF-8"), list.get(8).getString("UTF-8"), list.get(9).getString("UTF-8"), list.get(10).getName(), list.get(11).getString("UTF-8"));

                List<Person> psList = dao.searchPassword(newnick);

//                System.out.println(psList.isEmpty());
//                System.out.println(oldnick);
//                System.out.println(newnick);
//                System.out.println(newnick.equals(oldnick));

                String msg = null;
                if (psList.isEmpty()) {
                    dao.updateStudent(st);
                    list.get(9).write(new File("D:\\IDEA\\IdeaProjects\\实验十三 综合实验\\web\\file\\" + list.get(8).getName()));
                    msg = "数据修改成功!";
                    System.out.println("数据修改成功!");
                } else {
                    if (newnick.equals(oldnick)) {
                        dao.updateStudent(st);
                        list.get(9).write(new File("D:\\IDEA\\IdeaProjects\\实验十三 综合实验\\web\\file\\" + list.get(8).getName()));
                        msg = "数据修改成功!";
                        System.out.println("数据修改成功");
                    } else {
                        System.out.println("昵称重复");
                        msg = "数据修改失败,昵称重复!";
                    }
                }

                Gson gson = new Gson();
                String stuStr = gson.toJson(msg);
                response.getWriter().write(stuStr);

            } catch (Exception e) {
                Gson gson = new Gson();
                String stuStr = gson.toJson("数据修改失败！");
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
