<%--
  Created by IntelliJ IDEA.
  User: sheep
  Date: 2020/3/29
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<form method="post" action="/user/login">
    <div>用户名：<input type="text" name="userName"></div>
    <div>用户密码：<input type="text" name="passWord"></div>
    <div><input type="submit" value="提交"></div>
</form>
</body>
</html>
