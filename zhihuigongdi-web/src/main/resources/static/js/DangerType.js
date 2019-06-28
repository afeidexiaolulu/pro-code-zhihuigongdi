/*ready事件*/
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
    //查询第一页
    queryPage(1);
});


function queryPage(pageNo) {
    let index = -1 ;
    $.ajax({
        type:"post",
        url:"/admin/findAllDangerType",
        data:{
            pageNo:1,
            pageSize:15
        },
        beforeSend : function(){
            index = layer.load(0, {time: 10*1000});
            return true ;
        },
        success:function(result){
            layer.close(index);
            if(result.success){
                //局部刷新
                var page = result.data ;
                var list = page.datas ;
                var content = '';
                $.each(list,function(i,e){
                    content+='<tr>';
                    content+='  <td align="center">'+(i+1)+'</td>';
                    content+='  <td align="center"><input type="checkbox" id="'+e.id+'" loginacct="'+e.loginacct+'"></td>';
                    content+='  <td align="center">'+e.dangerType+'</td>';;
                    content+='  <td align="center">';
                    content+='	  <button type="button" class="btn btn-danger btn-xs" onclick="deleteDangerType('+e.id+',\''+e.dangerType+'\')" ><i class=" glyphicon glyphicon-remove"></i></button>';
                    content+='  </td>';
                    content+='</tr>';
                });
                $("tbody").html(content);

                //分页条
                var navigator = '';
                if(page.pageno == 1){
                    navigator +='<li class="disabled"><a href="#">上一页</a></li>';
                }else{
                    navigator +='<li><a onclick="queryPage('+(page.pageno-1)+')">上一页</a></li>';
                }

                for(var i=1; i<= page.totalno ; i++){
                    if(i == page.pageno){
                        navigator +='<li class="active"><a onclick="queryPage('+i+')">'+i+'</a></li>';
                    }else{
                        navigator +='<li><a onclick="queryPage('+i+')">'+i+'</a></li>';
                    }
                }

                if(page.pageno == page.totalno){
                    navigator +='<li class="disabled"><a href="#">下一页</a></li>';
                }else{
                    navigator +='<li><a onclick="queryPage('+(page.pageno+1)+')">下一页</a></li>';
                }

                $(".pagination").html(navigator);
            }else{
                //加载失败，弹出错误消息
                layer.msg("加载数据失败!", {time:1000, icon:5, shift:6});
            }
        }
    });
}


/*删除的js*/
function deleteDangerType(id, dangerType) {
    //layer弹层进行提醒
    layer.confirm("确认要删除"+dangerType+"么？",  {icon: 3, title:'提示'}, function(cindex){
        layer.close(cindex);
        //发送ajax请求
        $.ajax({
            type:"get",
            url:"/admin/delDangerType",
            data:{
                id:id
            },
            success:function(result){
                if(result.success){
                    //提示删除成功
                    layer.msg("删除成功!", {time:1000, icon:6});
                    //跳转到首页面
                    queryPage(1);
                }else {
                    //提示失败
                    layer.msg("删除成功!", {time:1000, icon:6});
                }
            }


        });
    }, function(cindex){
        layer.close(cindex);
    });

}