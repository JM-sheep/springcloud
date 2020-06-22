<%--
  Created by IntelliJ IDEA.
  User: sheep
  Date: 2020/3/19
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            width: 800px;
            margin: auto;
            border: 1px solid #999;
            border-collapse: collapse;
        }
        table th,td{
            text-align: center;
            padding: 10px;
            border: solid #999;
        }
        #div{
            border: solid 1px black;
            width: 500px;
            height: 150px;
            text-align: center;
            padding-top: 25px;
            margin-left: 500px;
        }
    </style>
</head>
<body>
<div id="div">
    <form id="myForm">
        部门管理<br>
        部门名称:<input type="text" name="dname" id="dname"/><br>
        部门位置:<input type="text" name="loc" id="loc"/><br>
        <input type="button" value="提交" id="btn" onclick="saveOrUpdate()" style="margin-top: 10px">
    </form>
</div>
<table id="tab">
    <caption>
        <h2>部门管理</h2>
    </caption>
    <tr>
        <td>部门编号</td>
        <td>部门名称</td>
        <td>部门位置</td>
        <td>编辑</td>
        <td>删除</td>
    </tr>
</table>
<button onclick="test()">测试</button>
</body>
<script src="static/jquery-1.12.4.js"></script>
<script>
    //页面加载时
    $(function(){
        listAll();
    });

    //查询所有数据
    function listAll() {
        $.ajax({
            type: "POST",   //提交的方式
            url: "/dept/listAll",    //提交的地址
            success: function(dept){     //请求成功后后回调函数
                for (var i=0;i<dept.length;i++){
                    var tr = "<tr>" +
                        "<td>"+dept[i].deptno+"</td>" +
                        "<td>"+dept[i].dname+"</td>" +
                        "<td>"+dept[i].loc+"</td>" +
                        "<td><input type='button' value='修改' onclick='edit("+dept[i].deptno+")'></td>" +
                        "<td><input type='button' value='删除' onclick='del("+dept[i].deptno+")'></td>" +
                        "</tr>";
                    $("#tab").append(tr);
                }
            },
            error: function () {
                alert("错误:" + 0);
            }
        });
    }

    //增加或者修改数据
    function saveOrUpdate() {
        $.ajax({
            type: "POST",   //提交的方式
            url: "/dept/saveOrUpdate",    //提交的地址
            data: $("#myForm").serialize(),     //需要提交的数据，使用表单序列化
            success:function (int) {
                if (int>0){
                    alert("true");
                    $("#dname").val("");
                    $("#loc").val("");
                    listAll();
                }
            }
        })
    }

    //编辑部门的方法
    function edit(deptno) {
        $.ajax({
            type: "POST",   //提交的方式
            url: "/dept/edit?deptno="+deptno,    //提交的地址
            success:function (dept) {
                $("#dname").val(dept.dname);
                $("#loc").val(dept.loc);
            }
        })
    }

    //删除部门的方法
    function del(deptno) {
        $.ajax({
            type: "POST",   //提交的方式
            url: "/dept/delete?deptno="+deptno,    //提交的地址
            success:function (dept) {
                //刷新当前页面
                window.location.reload()
            }
        })
    }

    //测试异常回滚
    function test() {
        var i = [
            {"deptno":100,"dname":"测试部1","loc":"测试部1"},
            {"deptno":102,"dname":"测试部2","loc":"测试部2"},
            {"deptno":103,"dname":"测试部3","loc":"测试部3"}
        ];
        $.ajax({
            type: "POST",   //提交的方式
            url: "/test",    //提交的地址
            data: JSON.stringify(i),    //将数据转换成json格式的字符串
            contentType: "application/json",    //发送的数据格式是json格式的
            success:function (dept) {
                alert(dept);
            },
            error:function () {
                alert(0)
            }
        })
    }
</script>
</html>
