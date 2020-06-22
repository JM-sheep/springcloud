<%--
  Created by IntelliJ IDEA.
  User: sheep
  Date: 2020/3/18
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
    <title>首页</title>
    <style>
        div{
            text-align: center;
            padding-top: 200px;
        }
    </style>
</head>
<body>
<div>
    <h1>欢迎来到部门管理系统，请点击下面的连接</h1><br>
    <a href="select.jsp">查询所有部门</a>
    <a href="test.jsp">测试文件提交</a>
    <div>
        <shiro:hasRole name="管理员">欢迎您，管理员</shiro:hasRole>
        <shiro:hasRole name="人事主管">欢迎您，人事主管</shiro:hasRole>
        <shiro:hasRole name="库管">欢迎您，库管</shiro:hasRole>
        <br><shiro:authenticated>状态：已登录</shiro:authenticated>
        <div>
            <shiro:hasRole name="管理员">欢迎您，管理员</shiro:hasRole>
            <shiro:hasRole name="人事主管">欢迎您，人事主管</shiro:hasRole>
            <shiro:hasRole name="库管">欢迎您，库管</shiro:hasRole>
            <br><shiro:authenticated>状态：已登录</shiro:authenticated>
            <div>
                <shiro:hasRole name="管理员">欢迎您，管理员</shiro:hasRole>
                <shiro:hasRole name="人事主管">欢迎您，人事主管</shiro:hasRole>
                <shiro:hasRole name="库管">欢迎您，库管</shiro:hasRole>
                <br><shiro:authenticated>状态：已登录</shiro:authenticated>
                <a href="/logout">注销</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
