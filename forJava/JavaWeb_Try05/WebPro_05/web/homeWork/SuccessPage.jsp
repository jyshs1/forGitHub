<!DOCTYPE html>
<html lang="en">
<%--
    Todo:登录成功页面， 打印出当前在线人数
            以及用户名用来标识登录的是哪个一个用户
--%>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    session = request.getSession();
    String currentUser = (String) session.getServletContext().getAttribute("online");
%>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>登陆成功页面</title>
</head>
<body >
</body>

<script>
    document.write("<h3>你好：");
    document.write(GetQueryString("userName")+"</h3>")
    document.write("<h2>当前在线人数为：<%=currentUser%> </h2>")
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        document.getElementById("div_text").innerHTML = r;
        return null;
    }
</script>
</html>