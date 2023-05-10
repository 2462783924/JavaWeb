package controller;

import Dao.PersonDao;
import beans.Person;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "searchServlet", value = "/searchServlet")
public class searchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String key = request.getParameter("key");
        String seindex = request.getParameter("se");
        String flag = request.getParameter("flag");
        PersonDao dao = new PersonDao();
        List<Person> list = null;
        if (flag.equals("1")) {
            int rowCount = dao.getRowCount();
            //每页设置显示3条记录
            int size = 3;
            int page = dao.getPage(size);
            list = dao.getStudentsByPage(Integer.parseInt(seindex), size);
        }else{
            list = dao.fuzzyQuery(key);
        }
        Gson gson = new Gson();
        String stuStr = gson.toJson(list);
        response.getWriter().write(stuStr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
