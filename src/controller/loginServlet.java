package controller;

import Dao.PersonDao;
import beans.PersonInfo;
import beans.Person;
import cn.hutool.crypto.digest.DigestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String nick = request.getParameter("name");
        //String password = request.getParameter("password");
        String password = DigestUtil.md5Hex(request.getParameter("password"));
        System.out.println(password);
        String code = request.getParameter("code");

        PersonInfo personInfo = new PersonInfo();
        personInfo.setAccount(nick);
        personInfo.setPassword(password);
        request.getSession().setAttribute("personInfo", personInfo);

        PersonDao psd = new PersonDao();
        List<Person> list = psd.searchPassword(nick);
        if(list.isEmpty()){
            request.setAttribute("login","账号不存在，请注册新用户");
            request.getRequestDispatcher("Login.jsp").forward(request,response);
        }
        Person ps = list.get(0);
        if(password.equals(ps.getPassword())&&code.equals(request.getSession().getAttribute("authCode"))){
            request.setAttribute("login","欢迎登陆");
            if(nick.equals("admin")){
                response.sendRedirect("admin.jsp");
            }else{
                request.setAttribute("psd",ps);
                System.out.println(nick);
                request.getRequestDispatcher("user.jsp").forward(request,response);
            }
        }else{
            request.setAttribute("login","信息配对失败，请重试");
            request.getRequestDispatcher("Login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
