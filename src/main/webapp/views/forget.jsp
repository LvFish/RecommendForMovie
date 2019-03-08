<%--
  Created by IntelliJ IDEA.
  User: liujiawang
  Date: 2019/2/28
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>电影推荐系统</title>
    <link href="/assets/css/register.css" rel="stylesheet" type="text/css" />
    <script src="/assets/js/jquery-3.2.0.min.js"></script>
</head>
<script>
    var time=60;
    var code = "";
    function sendemail(val){
//        var obj = $("#btn");
        var email = document.getElementById('email').value;
        var username = document.getElementById('username').value;
        var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if(email==""||email.length==0){
            alert("邮箱不能为空");
            return;
        }else if(!(isEmail.test(email))){
            alert("请输入正确格式邮箱");
            return;
        }
        settime(val);
        $.post("/user/sendEmail",
            {
                email: email,
            },
            function(data,status){
                if(status){
                    console.log(data.msg);
                    //登陆成功
                    code = data.msg;
                    document.getElementById('code').setAttribute("value",code);
                }else{
                    alert("登陆失败");
                }
            });
    }
    function settime(obj) { //发送验证码倒计时
        if (time == 0) {
            obj.removeAttribute("disabled");
            obj.value="免费获取验证码";
            time = 60;
            return;
        } else {
            obj.setAttribute("disabled", true);
            obj.value="重新发送(" + time + ")";
            time--;
        }
        setTimeout(function() {
                settime(obj) }
            ,1000)
    }
</script>
<body>
<div>
    <div class="box">
        <div>
            <label class="box-lable">重置密码</label>
        </div>
        <div class="box-form">
            <form action="/user/forget" method="post" name="addForm">
                <div class="box-input">
                </div>
                <div class="box-input">
                    <input placeholder="用户名..." type="text" class="input-text" name="username" id="username" required="required" />
                </div>
                <div class="box-input">
                    <input placeholder="邮箱..." type="text" class="input-text" name="email" id="email" required="required"/>
                </div>
                <div class="form-text">
                    <input type="button" id="btn" value="免费获取验证码" onclick="sendemail(this)"/>
                </div>
                <div class="box-input">
                    <input placeholder="验证码" type="text" class="input-text" name="number" id="number" required="required"/>
                </div>
                <div class="">
                    <input type="hidden" name="code" id="code"/>
                </div>
                <div class="box-input">
                    <input placeholder="密码..." type="password" class="input-text" name="password1" id="password1" required="required"/>
                </div>
                <div class="box-input">
                    <input placeholder="再次输入" type="password" class="input-text" name="password2" id="password2" required="required"/>
                </div>
                <div class="">
                    <button class="form-button" type="submit" id="submit" >重置密码</button>
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