<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/18
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <%
        String contextPath = application.getContextPath();
    %>
    <form action="<%= contextPath %>/jsplogin/dologin" method="post">
        用户名:<input name="username"/><br/>
        密码:<input name="password"><br/>
        <button>登录</button><br/>
        <span>密码:123456</span>
    </form>
</body>
</html>
