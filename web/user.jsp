<%@ page import="beans.Person" %>
import beans.Person;
<%--
  Created by IntelliJ IDEA.
  User: 離傷
  Date: 2022/11/25
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>
    <%--    <script src="jquery/jquery.min.js"></script>--%>
    <script src="http://code.jquery.com/jquery.min.js"></script>
    <script>
        $(function () {
            //禁用“确认重新提交表单”
            window.history.replaceState(null, null, window.location.href);
        })
    </script>
    <%
        Person ps = (Person) request.getAttribute("psd");
    %>

    <script type="text/javascript">
        flagupdate = false;
        flagnick = false;
        flagpassword = false;
        flagage = false;
        flagphone = false;
        flagemail = false;
        var update1 = function () {
            flagupdate=true;
            alert("请修改相关信息");
            var input = document.getElementsByName("nick")[0];
            input.setAttribute("placeholder", input.value);
            input.setAttribute("value", "");
            input.setAttribute("onfocus", "");
            var div = document.getElementById("nickInfo");
            div.innerHTML = "请输入昵称，长度不超过10位";

            var input = document.getElementsByName("password")[0];
            input.setAttribute("placeholder", input.value);
            input.setAttribute("value", "");
            input.setAttribute("onfocus", "");
            var div = document.getElementById("passwordInfo");
            div.innerHTML = "请输入密码，包含大小写和符号";

            var input = document.getElementsByName("stuname")[0];
            input.setAttribute("placeholder", input.value);
            input.setAttribute("value", "");
            input.setAttribute("onfocus", "");

            var radio = document.getElementsByName("sex");
            radio[0].setAttribute("type", "radio");
            radio[1].setAttribute("type", "radio");
            var s = document.getElementsByName("s")[0];
            s.innerHTML = "";
            var div = document.getElementsByName("boy")[0];
            div.innerHTML = "男";
            var div = document.getElementsByName("girl")[0];
            div.innerHTML = "女";

            var input = document.getElementsByName("age")[0];
            input.setAttribute("placeholder", input.value);
            input.setAttribute("value", "");
            input.setAttribute("onfocus", "");
            var div = document.getElementById("ageInfo");
            div.innerHTML = "请输入年龄，非负";

            var input = document.getElementsByName("phone")[0];
            input.setAttribute("placeholder", input.value);
            input.setAttribute("value", "");
            input.setAttribute("onfocus", "");
            var div = document.getElementById("phoneInfo");
            div.innerHTML = "请输入正确的电话格式";

            var input = document.getElementsByName("email")[0];
            input.setAttribute("placeholder", input.value);
            input.setAttribute("value", "");
            input.setAttribute("onfocus", "");
            var div = document.getElementById("emailInfo");
            div.innerHTML = "请输入正确的邮箱格式";

            var input = document.getElementsByName("pic")[0];
            input.setAttribute("type", "file");
            var input = document.getElementsByName("introduce")[0];
            input.setAttribute("placeholder", input.value);
            input.setAttribute("value", "");
            input.setAttribute("onfocus", "");

            var input = document.getElementsByName("update")[0];
            input.setAttribute("type", "hidden");
            var input = document.getElementsByName("save")[0];
            input.setAttribute("type", "button");
            var form = document.getElementsByName("form")[0];
            form.setAttribute("enctype", "multipart/form-data");
        }

        function check() {
            if (flagnick && flagpassword && flagage && flagphone && flagemail) {
                ajkx()
            } else {
                alert("请正确填写相关信息");
            }
        }

        function ajkx() {
            var xmlhttprequest = new XMLHttpRequest();
            var form = document.getElementsByName("form")[0];
            var formData = new FormData(form);
            xmlhttprequest.open("POST", "http://localhost:8080/jspPJ/modifyServlet", true);

            xmlhttprequest.onreadystatechange = function () {
                if (xmlhttprequest.readyState == 4) {
                    var s = xmlhttprequest.responseText
                    alert(s)
                    if(s=="\"数据修改成功!\""){
                        location.reload ();
                        flagupdate=false;
                    }else{
                        update1();
                        flagupdate=true;
                    }
                }
            }
            xmlhttprequest.send(formData);
        }
    </script>
</head>
<body>

<form method="post" name="form">
    <input type="hidden" name="oldnick" value="<%=ps.getNick()%>">
    <input value="<%=ps.getId()%>" type="hidden" name="id">
    昵称：<input value="<%=ps.getNick()%>" onfocus="this.blur()" name="nick" id="nick">
    <div style="display: inline-block;color: gray;" name="nickInfo" id="nickInfo"></div>
    <br>
    密码：<input value="<%=ps.getPassword()%>" onfocus="this.blur()" name="password" id="password">
    <div id="passwordInfo" style="display: inline-block;color:gray;"></div>
    <br>
    学号：<input value="<%=ps.getStuno()%>" onfocus="this.blur()" name="stuno"><br>
    姓名：<input value="<%=ps.getStuname()%>" onfocus="this.blur()" name="stuname"><br>
    性别：
    <%
        if (ps.getSex().equals("男")) {
    %>
    <div style="display: inline-block;" name="s">男</div>
    <%
    } else {
    %>
    <div style="display: inline-block;" name="s">女</div>
    <%
        }
    %>
    <input type="hidden" name="sex" id="boy" value="男">
    <div name="boy" style="display: inline-block;"></div>
    <input type="hidden" name="sex" id="girl" value="女">
    <div name="girl" style="display: inline-block;"></div>
    <br>
    年龄：<input value="<%=ps.getAge()%>" onfocus="this.blur()" name="age" id="age_input">
    <div id="ageInfo" style="display: inline-block;color:gray;"></div>
    <br>
    电话：<input value="<%=ps.getPhone()%>" onfocus="this.blur()" name="phone" id="phone_input">
    <div id="phoneInfo" style="display: inline-block;color:gray;"></div>
    <br>
    邮箱：<input value="<%=ps.getEmail()%>" onfocus="this.blur()" name="email" id="email_input">
    <div id="emailInfo" style="display: inline-block;color: gray;"></div>
    <br>
    照片：<img src="file/<%=ps.getPic()%>" width="200" height="200" border="0" alt=""/>
    <input type="hidden" name="pic" name="pic"><br>
    个人介绍：<input value="<%=ps.getIntpoduce()%>" onfocus="this.blur()" name="introduce"><br>
    <input type="button" value="编辑" onclick="update1()" name="update">
    <input type="hidden" value="保存" onclick="check()" name="save">
</form>

<script>
    $(document).ready(function () {
        //昵称校验
        $('#nick').blur(function () {
            let nick = $("#nick").val();
            if(flagupdate)
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
            if(flagupdate)
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
            if(flagupdate)
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
            if(flagupdate)
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
            if(flagupdate)
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
