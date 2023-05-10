package Filter;

import beans.PersonInfo;
import cn.hutool.crypto.digest.DigestUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginCheckFilter",value = {"*.jsp"})
public class LoginCheckFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        //PersonInfo user = (PersonInfo) session.getAttribute("personInfo");
        PersonInfo user = (PersonInfo) session.getAttribute("personInfo");
        // 如果等于 null，说明还没有登录
        if (user == null) {
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
            return;
        } else {
            // 让程序继续往下访问用户的目标资源
            chain.doFilter(request, response);
        }
    }
}
