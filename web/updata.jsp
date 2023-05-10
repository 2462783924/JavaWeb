<%@ page import="Dao.PersonDao" %>
<%@ page import="beans.Person" %><%--
  Created by IntelliJ IDEA.
  User: 離傷
  Date: 2022/10/31
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        int id = Integer.parseInt(request.getParameter("id"));
        PersonDao psd = new PersonDao();
        Person ps = psd.findByID(id).get(0);
    %>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery.min.js"></script>
    <script type="text/javascript">
        flagnick = false;
        flagpassword = false;
        flagage = false;
        flagphone = false;
        flagemail = false;

        function check() {
            if (flagnick && flagpassword && flagage && flagphone && flagemail) {
                ajkx();
                return true;
            } else {
                alert("请正确填写相关信息");
                return false;
            }
        }

        function ajkx() {
            var xmlhttprequest = new XMLHttpRequest();
            var form = document.getElementsByName("form")[0];
            var formData = new FormData(form);
            xmlhttprequest.open("POST", "http://localhost:8080/jspPJ/modifyServlet", true);
            xmlhttprequest.send(formData);

            xmlhttprequest.onreadystatechange = function () {
                if (xmlhttprequest.readyState == 4) {
                    var s = xmlhttprequest.responseText
                    alert(s)
                    if (s == "\"数据修改成功!\"") {
                        flagupdate = false;
                        window.opener=null;window.close();
                    } else {
                        update1();
                        flagupdate = true;
                    }
                }

            }
        }
    </script>

</head>
<body>

<form name="form" method="post" enctype="multipart/form-data">
    <input type="hidden" name="oldnick" value="<%=ps.getNick()%>">
    id:<%=ps.getId()%><br>
    <input type="hidden" name="id" value="<%=ps.getId()%>">
    昵称:<input name="nick" id="nick" placeholder="<%=ps.getNick()%>">
    <div style="display: inline-block;color: gray;" name="nickInfo" id="nickInfo">请输入昵称，长度不超过10位</div>
    <br>
    密码:<input name="password" id="password" placeholder="<%=ps.getPassword()%>">
    <div id="passwordInfo" style="display: inline-block;color:gray;">请输入密码，包含大小写和符号</div>
    <br>
    学号:<input name="stuno" placeholder="<%=ps.getStuno()%>"><br>
    姓名:<input name="stuname" placeholder="<%=ps.getStuname()%>"><br>
    性别:
    <%--    <input name="sex" placeholder="<%=ps.getSex()%>"><br>--%>
    <input type="radio" name="sex" id="boy" value="男">
    <div name="boy" style="display: inline-block;">男</div>
    <input type="radio" name="sex" id="girl" value="女">
    <div name="girl" style="display: inline-block;">女</div>
    <br>
    年龄:<input name="age" id="age_input" placeholder="<%=ps.getAge()%>">
    <div id="ageInfo" style="display: inline-block;color:gray;">请输入年龄，非负</div>
    <br>
    电话:<input name="phone" id="phone_input" placeholder="<%=ps.getPhone()%>">
    <div id="phoneInfo" style="display: inline-block;color:gray;">请输入正确的电话格式</div>
    <br>
    邮箱:<input name="email" id="email_input" placeholder="<%=ps.getEmail()%>">
    <div id="emailInfo" style="display: inline-block;color: gray;">请输入正确的邮箱格式</div>
    <br>
    照片:<img src="file/<%=ps.getPic()%>" width="200" height="200" border="0" alt=""/>
    <input type="file" name="pic"><br>
    介绍(INTPODUCE):<input name="intpoduce" placeholder="<%=ps.getIntpoduce()%>"><br>
    <input type="button" onclick="check()" value="确认修改">
</form>

<script>
    $(document).ready(function () {
        //昵称校验
        $('#nick').blur(function () {
            let nick = $("#nick").val();
            if (nick.length > 10 || nick.length === 0) {
                flagnick = false;
                $("#nickInfo").text("昵称的长度限制1到10位");
                $("#nickInfo").css("color", "red");
            } else {
                flagnick = true;
                $("#nickInfo").text("昵称合法");
                $("#nickInfo").css("color", "green");
            }
        });

        //密码校验
        $('#password').blur(function () {
            let password = $("#password").val();
            const pattern = /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、])[\da-zA-Z~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]{8,16}$/;
            console.log(pattern.test(password));
            if (pattern.test(password)) {
                flagpassword = true;
                $("#passwordInfo").text("密码合法");
                $("#passwordInfo").css("color", "green");
            } else {
                flagpassword = false;
                $("#passwordInfo").text("密码必须包含大小写字符、数字、特殊符号，并且长度在8到16位");
                $("#passwordInfo").css("color", "red");
            }
        });

        //年龄校验
        $("#age_input").blur(function () {
            let age = $("#age_input").val();
            if (age < 0) {
                flagage = false;
                $("#ageInfo").text("年龄不能为负数");
                $("#ageInfo").css("color", "red");
            } else {
                flagage = true
                $("#ageInfo").text("年龄合法");
                $("#ageInfo").css("color", "green");
            }
        });

        //电话校验
        $("#phone_input").blur(function () {
            let phone = $("#phone_input").val();
            const pattern = /^[1][3,4,5,7,8][0-9]{9}$/;
            if (!pattern.test(phone)) {
                flagphone = false;
                $("#phoneInfo").text("电话不合法");
                $("#phoneInfo").css("color", "red");
            } else {
                flagphone = true;
                $("#phoneInfo").text("电话合法");
                $("#phoneInfo").css("color", "green");
            }
        });

        //邮箱校验
        $("#email_input").blur(function () {
            let email = $("#email_input").val();
            const pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            if (!pattern.test(email)) {
                flagemail = false;
                $("#emailInfo").text("邮箱不合法");
                $("#emailInfo").css("color", "red");
            } else {
                flagemail = true;
                $("#emailInfo").text("邮箱合法");
                $("#emailInfo").css("color", "green");
            }
        });

    })
</script>
</body>
</html>
