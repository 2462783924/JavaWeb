<%@ page import="Dao.PersonDao" %>
<%@ page import="beans.PersonInfo" %>
<%@ page import="beans.Person" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 離傷
  Date: 2022/10/9
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script>
    $(function () {
        //禁用“确认重新提交表单”
        window.history.replaceState(null, null, window.location.href);
    })
</script>
<head>
    <style type="text/css">
        fieldset {
            margin: 30px auto;
            width: 300px;
            height: 150px;
            padding: 20px;
            color: blue;
            text-align: center;
        }

        label {
            display: inline-block;
            width: 100px;
            text-align: justify;
            text-align-last: justify;
            margin-right: 10px;
        }
    </style>

    <title>Login</title>
    <script type="text/javascript">
        /*
        用户验证方法：账号密码一致且验证码正确则登陆成功，否则登陆失败
         */

        var check = function () {
            var name = document.getElementsByName("name")[0].value;
            var password = document.getElementsByName("password")[0].value;
            var code = document.getElementsByName("code")[0].value;
            if (name != "" && password != "" && code != "") {
                return true;
            } else {
                alert("请输入相关信息");
                return false;
            }
        }
    </script>
</head>
<body>
<form style="text-align: justify" action="loginServlet" method="post">
    <%
        if(request.getAttribute("login")==null){
            request.setAttribute("login","欢迎登陆");
        }
    %>
    <h1 align="center"><%=request.getAttribute("login")%></h1>
    <fieldset>
        <label>name</label><input type="text" class="a" name="name"/><br>
        <label>password</label><input type="password" class="a" name="password"/><br>
        <label>code</label><input type="text" class="a" name="code"/><br><br>
        <img src="GetAuthCodeServlet"/><br><br>
        <input type="submit" onclick="return check()" value="登录"/>
        <a href="insert.jsp" target="_blank"><input type="button"onclick="<%PersonInfo personInfo = new PersonInfo();
        personInfo.setAccount("nick");
        personInfo.setPassword("password");
        request.getSession().setAttribute("personInfo", personInfo);%>" value="注册"/></a>
    </fieldset>
</form>
</body>
</html>
