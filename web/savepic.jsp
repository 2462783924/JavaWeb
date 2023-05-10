<%--
  Created by IntelliJ IDEA.
  User: 離傷
  Date: 2022/11/19
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <img src="file/<%=request.getParameter("pic").replace("\"","")%>" width="200" height="200" border="0" alt=""/>
    <br>
    <a href="savepicServlet?fileName=<%=request.getParameter("pic").replace("\"","")%>"><button>保存图片</button></a>
</body>
</html>
