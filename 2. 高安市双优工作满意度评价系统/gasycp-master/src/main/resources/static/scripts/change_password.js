/**
 * @return {boolean}
 */
function Check()
{

    var reg =/^[a-zA-Z0-9]{6,18}$/;                     //6-18位字母和数字的混合组成的正则表达式的js代码
    var username=document.getElementById("username");
    var password=document.getElementById("password");
    var new_password=document.getElementById("new_password");

    if(username.value==="")
    {
        alert("用户名不能为空，请重新填写！");
        return false;
    }
    else if(!reg.test(username.value))
    {
        alert("请正确输入用户名！");
        username.value="";
        username.focus();
        return false;
    }
    else if(new_password.value==="")
    {
        alert("密码不能为空，请重新输入！");
        return false;
    }
    else if(!reg.test(new_password.value))
    {
        alert("请正确输入新密码，由6-18位字母和数字的混合组成");
        new_password.value="";
        new_password.focus();
        return false;
    }
    else if(password.value!==new_password.value)
    {
        alert("两次输入不一致，请重新确认密码！");
        password.value="";
        password.focus();
        return false;
    }
    return true;
}
