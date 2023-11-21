<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- 登录表单 LoginPart.jsp -->
<html lang="en" xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
      h1 {
        text-align: center;
      }
      form {
        width: 300px;
        margin: 0 auto;
      }
      table {
        width: 100%;
        border: 1px solid #000000;
        border-collapse: collapse;
      }
    </style>
  </head>
  <body>
  <%
    /**
     * Todo: 读取cookies 判断上次是否选择记住密码 是否超时
     *  如果选择记住密码且未超时，那么将会把coolies里保存的用户名和密码填入到表单中
     *  并把复选框打勾
     *  如果未选择记住密码或超时，那么判断结束后什么也不做
     *
     * */
    request.setCharacterEncoding("utf-8");
    response.setHeader("Content-Type", "text/html;charset=UTF-8");

    Cookie[] cookies = request.getCookies();
    Cookie cookieUser = null;
    Cookie cookiePassWord = null;
    Cookie cookieReminder = null;
    session = request.getSession();
    // isPasswordSave 密码是否被保存
    boolean isPasswordSave = false;
    String isChecked = "";
    if (cookies != null) {
      for (Cookie c : cookies) {
        if (c.getName().equals("isPasswordSave")) {
          //找到就读取 value 看看是否为true
          cookieReminder = c;
          //如果有 存记住密码的cookie 且 为true 保存了密码
          //那就说明此时 有cookie保存了账户和密码 且cookie未超时
          if (cookieReminder.getValue().equals("true") && c.getMaxAge() == -1) {
            isPasswordSave = true;
            session.setAttribute("isPasswordSave", "ture");
            isChecked = "checked";  //上次保存了密码，这次复选框要打勾
          }
        }
      }
    }
    if (!isPasswordSave) {   //上次未设置保存密码 或者cookie 超时
      session.setAttribute("isPasswordSave","false");
    }
    //cookie点了保存账户与密码 去cookies中寻找保存的账号与密码
    String userName = "";
    String userPassword = "";
    if (isPasswordSave) {
      //找到用户名与密码 那么填充页面
      for (Cookie c : cookies) {
        String name = c.getName();
        if (name.equals("userName")) {
          userName = c.getValue();
        } else if (name.equals("userPassword")) {
          userPassword = c.getValue();
        }
      }
    }
  %>
    <h1>账户登录</h1>
    <div>
<!--      表单代码 post到login.do 页面 -->
      <form action="login.do" target="_parent" method="post">
        <table>
          <tr>
            <td><label >账号:</label></td>
            <td><input type="text" name="userName" value="<%=userName%>"/></td>

          </tr>
          <tr>
            <td><label >密码:</label></td>
            <td><input type="password" name="userPassword" value="<%=userPassword%>"/></td>
          </tr>

          <tr>
            <td>
              <label
              >记住密码
                <input
                        type="checkbox"
                        name="isPasswordSave"
                        value="true"
                        <%=isChecked%>
                /></label>
            </td>
            <td>
              <label> </label>
            </td>
          </tr>

          <tr>
            <td><input type="submit" value="提交"/></td>
            <td><input type="reset" value="重新输入"/></td>
          </tr>
        </table>
      </form>
    </div>
  </body>
</html>
