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



function DangerTypeAdd() {
    //获取参数
    let loadingIndex = -1;
    //获取参数
    let dangerType = $("#dangerType").val();

    //通过ajax请求发送
    $.ajax({
        type:"post",
        url:"/admin/addDangerType",
        data:{
            dangerType:dangerType
        },
        beforeSend : function () {
            loadingIndex = layer.msg('处理中', {icon: 16});
            return true;
        },
        success : function (rusult) {
            if(rusult.success){
                //添加成功，跳转到分页查询首页
                window.location.href="/admin/toDangerType"
            }else {
                layer.msg(rusult.message, {time:1000, icon:5, shift:6});
            }
        }
    });
}

//继续新增
function DangerTypeAddAnd() {
    //获取参数
    let dangerType = $("#dangerType").val();

    //通过ajax请求发送
    $.ajax({
        type: "post",
        url: "/admin/addDangerType",
        data: {
            dangerType: dangerType,
        },
        beforeSend: function () {
            loadingIndex = layer.msg('处理中', {icon: 16});
            return true;
        },
        success: function (rusult) {
            if (rusult.success) {
                //添加成功，跳转到分页查询首页
                window.location.href = "/admin/toDangerTypeAdd"
            } else {
                layer.msg(rusult.message, {time: 1000, icon: 5, shift: 6});
            }
        }
    });
}