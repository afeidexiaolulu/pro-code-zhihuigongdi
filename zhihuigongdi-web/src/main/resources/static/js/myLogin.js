//var ctx = [[@{/}]];
function dologin() {
    /* 取用户名和密码 */
    var loginacct =  $("#loginacct").val();
    /* 判断 */
    if(loginacct == ""){
        layer.msg("<em style='color:red'>"+"用户名不能为空", {time:2000, icon:5, shift:6});
        return false;
    }
    var password =  $("#password").val();
    if(password == ""){
        layer.msg("<em style='color:red'>"+"请填写密码后登录", {time:2000, icon:5, shift:6});
        return false;
    }

    /* 开启阿贾克斯异步请求 */
    $.ajax({
        type:"post",
        url:"/doLogin",
        data:{
            loginAcct:loginacct,
            pwd:password
        },
        beforeSend:function(){

            return true;
        },
        success:function(result){
            /* 返回的resultjson对象有success属性 为一个Boolean值 */
            if(result.success){
                /* 返回结果后进行请求 */
                window.location.href="/admin/toAdminIndex";
            }else{
                /* 失败弹窗提示 */
                layer.msg("<em style='color:red'>"+result.message, {time:2000, icon:5, shift:6});
            }
        }
    });
}