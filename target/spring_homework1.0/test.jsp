<%--
  Created by IntelliJ IDEA.
  User: sheep
  Date: 2020/3/20
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试文件提交</title>
</head>
<body>
<div style="border: 1px solid black;width: 450px;padding: 10px">
    <%-- 上传文件表单必须是post 必须加enctype属性--%>
    <form action="/test/addFile" method="post" enctype="multipart/form-data">

        文件:<input type="file" name="head"/>
        <input type="submit" value="同步提交"/>
    </form>
</div>
<div style="border: 1px solid black;width: 450px;padding: 10px">
    <form id="myForm">
        文件:<input type="file" name="head"/>
        <input type="button" value="异步提交" onclick="a()"/>
    </form>
</div>
<div style="border: 1px solid black;width: 450px;padding: 10px">
    <form id="myForms">
        文件:<br>
        <input type="file" name="head"/><br>
        <input type="file" name="head"/><br>
        <input type="file" name="head"/><br>
        <input type="button" value="多个文件提交" onclick="b()"/>
    </form>
</div>
</body>
<script src="static/jquery-1.12.4.js"></script>
<script>
    function a() {
        //FormData是html5的提供的一个对象
        var form = new FormData(document.getElementById("myForm"));
        $.ajax({
            type: "POST",   //提交的方式
            url: "/test/addFile",    //提交的地址
            data:form,    //提交的数据
            processData: false, //发送的映射数据会被转换成字符串，这个设置可以回避
            contentType: false,
            success:function () {
                alert(true);
            },
            error:function () {
                alert(false)
            }
        })
    }

    function b() {
        //FormData是html5的提供的一个对象
        var form = new FormData(document.getElementById("myForms"));
        $.ajax({
            type: "POST",   //提交的方式
            url: "/test/addFiles",    //提交的地址
            data:form,    //提交的数据
            processData: false, //发送的映射数据会被转换成字符串，这个设置可以回避
            contentType: false,
            success:function () {
                alert(true);
            },
            error:function () {
                alert(false)
            }
        })
    }

</script>
</html>
