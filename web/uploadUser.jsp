<%--
  Created by IntelliJ IDEA.
  User: 離傷
  Date: 2022/11/20
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="uploadServlet" enctype="multipart/form-data">
    <input type="file" name="filename">
    <input type="submit" value="上传">
    <%
        Object message = session.getAttribute("message");
        if(message!=null && !"".equals(message)){
    %>
    <script type="text/javascript">
        alert("<%=message%>")
    </script>
    <%
        }
    %>
</form>
</body>
</html>
