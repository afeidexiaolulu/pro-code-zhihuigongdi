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

let json = {
    pageNo:1,
    pageSize:10
};

function queryPage(pageNo) {
    json.pageNo = pageNo;
    let index = -1 ;
    $.ajax({
        type:"get",
        url:"/admin/findAllRegPersonnel",
        data:json,
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
                    content+='  <td align="center"><input type="checkbox" id="'+e.id+'"></td>';
                    content+='  <td align="center">'+e.serialNumber+'</td>';
                    content+='  <td align="center">'+e.name+'</td>';
                    content+='  <td align="center">'+e.sex+'</td>';
                    content+='  <td align="center">'+e.age+'</td>';
                    content+='  <td align="center">'+e.identityNumber+'</td>';
                    content+='  <td align="center">'+e.phoneNumber+'</td>';
                    content+='  <td align="center">'+e.belongCompany+'</td>';
                    content+='  <td align="center">'+e.technicalGrade+'</td>';
                    content+='  <td align="center">'+e.statu+'</td>';
                    content+='  <td align="center">';
                    content+='	  <button type="button" class="btn btn-primary btn-xs" onclick="window.location.href=\'/admin/toUpdateRegPersonnel?id='+e.id+'\'"><i class=" glyphicon glyphicon-pencil"></i></button>';
                    content+='	  <button type="button" class="btn btn-danger btn-xs" onclick="deleteRegPersonnel('+e.id+',\''+e.name+'\')" ><i class=" glyphicon glyphicon-remove"></i></button>';
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
function deleteRegPersonnel(id, name) {
    //layer弹层进行提醒
    layer.confirm("确认要删除"+name+"么？",  {icon: 3, title:'提示'}, function(cindex){
        layer.close(cindex);
        //发送ajax请求
        $.ajax({
            type:"get",
            url:"/admin/delRegPersonnel",
            data:{
                id:id
            },
            success:function(result){
                if(result.success){
                    //提示删除成功
                    layer.msg("删除成功!", {time:1000, icon:6});
                    //清空搜索框
                    $("#nameId").val("");
                    $("#techId").val("");
                    $("#identityId").val("");
                    $("#companyId").val("");
                    json.name = '' ;
                    json.technicalGrade = '' ;
                    json.identityNumber = '' ;
                    json.belongCompany = '' ;
                    //跳转到首页面
                    queryPage(1);
                }else {
                    //提示失败
                    layer.msg("删除失败!", {time:1000, icon:6});
                }
            }
        });
    }, function(cindex){
        layer.close(cindex);
    });
}

//复选框状态勾选
$("#checkId").click(function(){
    var checkStatus = this.checked;
    var checkBoxsArray = $("tbody :checkbox");
    $.each(checkBoxsArray,function (i,e) {
        e.checked = checkStatus;
    });
});

//批量删除代码
$("#delRegPersonnelBatch").click(function () {
    var idstr = '';
    var tbodyCheckedArray = $("tbody :checked");
    if(tbodyCheckedArray.length==0){
        return false ;
    }
    $.each(tbodyCheckedArray, function (i, e) {
        if(i>0){
            idstr += '&';
        }
        idstr += "id=" + e.id;
    });
    //layer弹层进行提醒
    layer.confirm("确认要删除这些数据么？",  {icon: 3, title:'提示'}, function(cindex){
        layer.close(cindex);
        //发送ajax请求
        $.ajax({
            type:"get",
            url:"/admin/delRegPersonnelBatch",
            data:idstr,
            success:function(result){
                if(result.success){
                    //提示删除成功
                    layer.msg("批量删除成功!", {time:1000, icon:6});
                    //清空搜索框
                    $("#nameId").val("");
                    $("#techId").val("");
                    $("#identityId").val("");
                    $("#companyId").val("");
                    json.name = '' ;
                    json.technicalGrade = '' ;
                    json.identityNumber = '' ;
                    json.belongCompany = '' ;
                    //跳转到首页面
                    queryPage(1);
                }else {
                    //提示失败
                    layer.msg("批量删除失败!", {time:1000, icon:6});
                }
            }
        });
    }, function(cindex){
        layer.close(cindex);
    });
});

//更新用户状态
function updateRegPersonnel(id){
    //发送ajax请求
    $.ajax({
        type:"get",
        url:"/admin/updateRegPersonnel",
        data:{
            id:id
        },
        success:function(result){
            if(result.success){
                //提示删除成功
                layer.msg("更新状态成功!", {time:1000, icon:6});
                //跳转到首页面
                queryPage(1);
            }else {
                //提示失败
                layer.msg("更新状态失败!", {time:1000, icon:6});
            }
        }
    });
}

$("#queryRBtn").click(function(){
    let name = $("#nameId").val();
    let technicalGrade = $("#techId").val();
    let identityNumber = $("#identityId").val();
    let belongCompany = $("#companyId").val();
    json.name = name ;
    json.technicalGrade = technicalGrade ;
    json.identityNumber = identityNumber ;
    json.belongCompany = belongCompany ;
    queryPage(1);
});