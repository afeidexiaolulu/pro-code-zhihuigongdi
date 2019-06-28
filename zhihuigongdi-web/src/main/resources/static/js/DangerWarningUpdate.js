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
function DangerWarningUpdate() {
    //获取参数
    let loadingIndex = -1;
    //获取参数
    let id = $("#id").val();
    let type = $("#type").val();
    let num = $("#num").val();
    //通过ajax请求发送
    $.ajax({
        type:"post",
        url:"/admin/updateDangerWarning",
        data:{
            id : id,
            type : type,
            num : num,
        },
        beforeSend : function () {
            loadingIndex = layer.msg('处理中', {icon: 16});
            return true;
        },
        success : function (rusult) {
            if(rusult.success){
                //更新成功 跳转到首页面
                layer.close(loadingIndex);
                window.location.href = "/admin/toDangerWarning";
            }else {
                layer.msg(rusult.message, {time:1000, icon:5, shift:6});
            }
        }
    });
}