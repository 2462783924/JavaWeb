package Listener;
import beans.PersonInfo;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class LoginSessionListener implements HttpSessionAttributeListener {

    //用于保存全部session的map
    Map<String, HttpSession> map = new HashMap<>();

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        //登陆
        if (name.equals("personInfo")) {
            PersonInfo PersonInfo = (PersonInfo) httpSessionBindingEvent.getValue();
            if (map.get(PersonInfo.getAccount()) != null) {
                //map中有记录，表明在其他机器中登陆过,将以前的登陆失效
                HttpSession session = map.get(PersonInfo.getAccount());
                PersonInfo oldInfo = (PersonInfo) session.getAttribute("personInfo");
                session.removeAttribute("personInfo");
                session.setAttribute("msg", "您的账号在其他机器上登录，您被迫下线");
            }
            //将Session以用户名为索引，放入map中
            map.put(PersonInfo.getAccount(), httpSessionBindingEvent.getSession());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        //注销
        if (name.equals("personInfo")) {
            //将该session从map移除
            PersonInfo PersonInfo = (PersonInfo) httpSessionBindingEvent.getValue();
            map.remove(PersonInfo.getAccount());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        //没有注销的情况下用另一个账号登陆
        if (name.equals("personInfo")) {
            //移除旧的登陆信息
            PersonInfo oldInfo = (PersonInfo) httpSessionBindingEvent.getValue();
            map.remove(oldInfo.getAccount());
            //新的登陆信息
            PersonInfo PersonInfo = (PersonInfo) httpSessionBindingEvent.getSession().getAttribute("personInfo");
            //也要检查新登录的账号是否在别的机器上登陆过
            if (map.get(PersonInfo.getAccount()) != null) {
                //map中有记录，表明在其他机器中登陆过,将以前的登陆失效
                HttpSession session = map.get(PersonInfo.getAccount());
                session.removeAttribute("personInfo");
                session.setAttribute("msg", "您的账号在其他机器上登录，您被迫下线");
            }

            //将Session以用户名为索引，放入map中
            map.put(PersonInfo.getAccount(), httpSessionBindingEvent.getSession());

        }
    }
}