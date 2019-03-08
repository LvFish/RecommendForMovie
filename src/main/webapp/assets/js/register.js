
function login() {
    var name = document.getElementById("password1").value;
    var pass = document.getElementById("password2").value;
    if (pass!=name)
    {
        alert("两次密码不一致，请重新输入")
        window.open('register.jsp');
    }
    else
    {
    }
}