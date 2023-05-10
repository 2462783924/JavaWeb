<%@ page import="Dao.PersonDao" %>
<%@ page import="beans.PersonInfo" %><%--
  Created by IntelliJ IDEA.
  User: 離傷
  Date: 2022/11/13
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script>
        $(function () {
            //禁用“确认重新提交表单”
            window.history.replaceState(null, null, window.location.href);
        })
    </script>
    <script type="text/javascript">
        count = 0;
        flag = 2;
        page = 0;

        function button1() {
            flag = 1;
            ajkxRequest();
        }

        function button2() {
            flag = 2;
            ajkxRequest();
        }

        function after() {
            var se = document.getElementById("se");
            if (page == 0) {
                button1();
            } else {
                se[page - 1].selected = true;
                button1();
            }
        }

        function next() {
            var se = document.getElementById("se");
            if (page == <%=new PersonDao().getPage(3)%>) {
                button1();
            } else {
                se[page + 1].selected = true;
                button1();
            }
        }

        function ajkxRequest() {

            var xmlhttprequest = new XMLHttpRequest();
            var key = document.getElementsByName("key")[0].value;
            var se = document.getElementById("se");
            var index = se.selectedIndex + 1;
            page = index - 1;
            xmlhttprequest.open("GET", "http://localhost:8080/jspPJ/searchServlet?key=" + key + "&se=" + index + "&flag=" + flag, true);
            xmlhttprequest.onreadystatechange = function () {
                if (xmlhttprequest.readyState == 2) {
                    document.body.innerHTML = "";
                    var form = document.createElement("form");
                    var intext = document.createElement("input");
                    var but = document.createElement("input");
                    intext.setAttribute("type", "text");
                    intext.setAttribute("name", "key");
                    but.setAttribute("type", "button")
                    but.setAttribute("onclick", "button2()");
                    but.setAttribute("value", "搜索");
                    form.appendChild(intext);
                    form.appendChild(but);
                    var select = document.createElement("select");
                    select.setAttribute("id", "se");
                    //每个page显示3条记录
                    for (var x = 1; x <=<%=new PersonDao().getPage(3)%>; x++) {
                        var option = document.createElement("option");
                        option.innerHTML = x.toString();
                        if (x == index) {
                            option.setAttribute("selected", "selected");
                        }
                        select.appendChild(option);
                    }
                    var after = document.createElement("input");
                    var next = document.createElement("input");
                    after.setAttribute("type", "button");
                    next.setAttribute("type", "button");
                    after.setAttribute("onclick", "after()");
                    next.setAttribute("onclick", "next()");
                    after.setAttribute("value", "上一页");
                    next.setAttribute("value", "下一页");
                    var sebu = document.createElement("input");
                    sebu.setAttribute("type", "button");
                    sebu.setAttribute("onclick", "button1()");
                    sebu.setAttribute("value", "确定");
                    var br = document.createElement("br");
                    form.appendChild(br);
                    form.appendChild(after);
                    form.appendChild(next);
                    form.appendChild(select);
                    form.appendChild(sebu);
                    document.body.appendChild(form);
                }
                if (xmlhttprequest.readyState == 4) {

                    let jsonObj = JSON.parse(xmlhttprequest.responseText);
                    var table = document.createElement("table");
                    table.setAttribute("border", "1");

                    var tr = document.createElement("tr");

                    var td = document.createElement("td");
                    td.innerHTML = "ID";
                    tr.appendChild(td);
                    var td = document.createElement("td");
                    td.innerHTML = "昵称";
                    tr.appendChild(td);
                    var td = document.createElement("td");
                    td.innerHTML = "密码";
                    tr.appendChild(td);
                    var td = document.createElement("td");
                    td.innerHTML = "学号";
                    tr.appendChild(td);
                    var td = document.createElement("td");
                    td.innerHTML = "姓名";
                    tr.appendChild(td);
                    var td = document.createElement("td");
                    td.innerHTML = "性别";
                    tr.appendChild(td);
                    var td = document.createElement("td");
                    td.innerHTML = "年龄";
                    tr.appendChild(td);
                    var td = document.createElement("td");
                    td.innerHTML = "电话";
                    tr.appendChild(td);
                    var td = document.createElement("td");
                    td.innerHTML = "邮箱";
                    tr.appendChild(td);
                    var td = document.createElement("td");
                    td.innerHTML = "照片";
                    tr.appendChild(td);
                    var td = document.createElement("td");
                    td.innerHTML = "介绍";
                    tr.appendChild(td);

                    table.appendChild(tr);

                    for (var i = 0; i < jsonObj.length; i++) {
                        var tr = document.createElement("tr");

                        var td = document.createElement("td");
                        td.innerHTML = jsonObj[i].id;
                        tr.appendChild(td);
                        var td = document.createElement("td");
                        td.innerHTML = jsonObj[i].nick;
                        tr.appendChild(td);
                        var td = document.createElement("td");
                        td.innerHTML = jsonObj[i].password;
                        tr.appendChild(td);
                        var td = document.createElement("td");
                        td.innerHTML = jsonObj[i].stuno;
                        tr.appendChild(td);
                        var td = document.createElement("td");
                        td.innerHTML = jsonObj[i].stuname;
                        tr.appendChild(td);
                        var td = document.createElement("td");
                        td.innerHTML = jsonObj[i].sex;
                        tr.appendChild(td);
                        var td = document.createElement("td");
                        td.innerHTML = jsonObj[i].age;
                        tr.appendChild(td);
                        var td = document.createElement("td");
                        td.innerHTML = jsonObj[i].phone;
                        tr.appendChild(td);
                        var td = document.createElement("td");
                        td.innerHTML = jsonObj[i].email;
                        tr.appendChild(td);

                        var td = document.createElement("td");
                        var a = document.createElement("a");
                        a.innerHTML = jsonObj[i].pic;
                        a.setAttribute("href", "savepic.jsp?pic=\"" + jsonObj[i].pic + "\"");
                        a.setAttribute("target", "_blank");
                        td.appendChild(a);
                        tr.appendChild(td);
                        var td = document.createElement("td");
                        td.innerHTML = jsonObj[i].intpoduce;
                        tr.appendChild(td);

                        var td = document.createElement("td");
                        var a1 = document.createElement("a");
                        var a2 = document.createElement("a");
                        a1.innerHTML = "修改";
                        a2.innerHTML = "删除";
                        a1.setAttribute("href", "updata.jsp?id=" + jsonObj[i].id);
                        a1.setAttribute("target", "_blank");
                        a2.setAttribute("href", "deleteServlet?id=" + jsonObj[i].id);
                        td.appendChild(a1);
                        td.append(" ");
                        td.appendChild(a2);
                        tr.appendChild(td);

                        table.appendChild(tr);
                    }

                    document.body.append("搜索次数：" + count);
                    document.body.appendChild(table);
                    count++;

                    var add = document.createElement("a");
                    add.setAttribute("href", "insert.jsp");
                    add.setAttribute("target", "_blank");
                    var Add = document.createElement("button");
                    Add.innerHTML = "添加";
                    add.appendChild(Add);
                    document.body.appendChild(add);

                    var upload = document.createElement("a");
                    upload.setAttribute("href", "uploadUser.jsp");
                    upload.setAttribute("target", "_blank");
                    var Upload = document.createElement("button");
                    Upload.innerHTML = "导入";
                    upload.appendChild(Upload);
                    document.body.appendChild(upload);

                    var download = document.createElement("a");
                    download.setAttribute("href", "downloadServlet");
                    download.setAttribute("target", "_blank");
                    var Download = document.createElement("button");
                    Download.innerHTML = "导出";
                    download.appendChild(Download);
                    document.body.appendChild(download);
                }
            }

            xmlhttprequest.send();
        }
    </script>
</head>

<body onload="ajkxRequest()">
<form>
    <input type="text" name="key"/>
    <input type="button" onclick="button2()" value="搜索"/>
    <br>
    <input type="button" onclick="after()" value="上一页">
    <input type="button" onclick="next()" value="下一页">
    <select id="se">
        <%int x = 1;%>
        <c:forEach begin="1" end="<%=new PersonDao().getPage(3)%>">
            <option><%=x%>
            </option>
            <%x++;%>
        </c:forEach>
    </select>
    <input type="button" value="确定" onclick="button1()">
</form>
</body>
</html>
