<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sl
  Date: 2017/1/4
  Time: 下午3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>电影推荐系统</title>
    <link href="/assets/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div>
    <div class="box">
        <div>
            <label class="box-lable">欢迎登陆</label>
        </div>
        <div class="box-form">
            <form action="/user/login" method="post" name="addForm">
                <div class="box-input">
                </div>
                <div class="box-input">
                    <input placeholder="用户名..." type="text" class="input-text" name="username" id="username" required="required" />
                </div>
                <div class="box-input">
                    <input placeholder="密码..." type="password" class="input-text" name="password" id="password" required="required"/>
                </div>
                <div class="form-text">
                    <a href="forget.html">忘记密码</a>
                    <a href="register.html">注册</a>
                </div>
                <div class="">
                    <button class="form-button" type="submit" id="submit">登陆</button>
                </div>
                <c:if test="${message!=null}">
                    <p>${message}</p>
                </c:if>
            </form>
        </div>

    </div>
</div>

</body>
</html>