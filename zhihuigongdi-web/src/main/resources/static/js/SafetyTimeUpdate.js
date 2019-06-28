$(function () {
    $(".list-group-item").click(function(){
        if ( $(this).find("ul") ) {
            $(this).toggleClass("tree-closed");
            if ( $(this).hasClass("tree-closed") ) {
                $("ul", this).hide("fast");
            } else {
                $("ul", this).show("fast");
            }
        }
    });
});



//更新的ajax请求
function SafetyTimeUpdate() {
    //layer弹层索引
    let loadingIndex = -1;
    //获取参数
    let id = $("#id").val();

    let safetyTimeUpdate = $("#safetyTimeUpdate").val();
    //通过ajax请求发送
    $.ajax({
        type:"post",
        url:"/admin/updateSafetyTime",
        data:{
            id : id,
            safetyTimeUpdate : safetyTimeUpdate
        },
        beforeSend : function () {
            loadingIndex = layer.msg('处理中', {icon: 16});
            return true;
        },
        success : function (rusult) {
            if(rusult.success){
                //更新成功 跳转到首页面
                layer.close(loadingIndex);
                window.location.href = "/admin/toSafetyTimeUpdate";
            }else {
                layer.msg(rusult.message, {time:1000, icon:5, shift:6});
            }
        }
    });
}