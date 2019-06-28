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
    initForm();
});

let list;
//更新表格
function initForm(){
    content = '';
    let typeList = $("#dangerTypeList").val();
    list = $.parseJSON(typeList);
    $.each(list,function (i,e) {
        if(i == (list.length-1)){
            content += '<div class="form-group">'
            content += '<label for="'+e.id+'">危险预警类型</label>'
            content += '<p  /*value='+e.dangerType+'*/ class="form-control" id='+e.id+'>'+e.dangerType+'</p>'
            content += '</div>'
            content += '<div class="form-group">'
            content += '<label for='+e.id+e.dangerType+'>危险预警数量</label>'
            content += '<input type="text" class="form-control" id='+e.id+e.dangerType+' >'
            content += '</div>'
            content += '<button type="button" class="btn btn-success" onclick="DanderWarningAdd()"><i class="glyphicon glyphicon-plus"></i> 新增完成</button>'
        }else {
            content += '<div class="form-group">'
            content += '<label for="'+e.id+'">危险预警类型</label>'
            content += '<p  /*value='+e.dangerType+'*/ class="form-control" id='+e.id+'>'+e.dangerType+'</p>'
            content += '</div>'
            content += '<div class="form-group">'
            content += '<label for='+e.id+e.dangerType+'>危险预警数量</label>'
            content += '<input type="text" class="form-control" id='+e.id+e.dangerType+'>'
            content += '</div>'
        }
    });
    $("#warningForm").html(content);
}

function DanderWarningAdd() {

    let str = '';
    let loadingIndex = -1;
    //获取参数
    $.each(list, function (i,e) {
        let type = $("#"+e.id).text();
        str += "type="+ type;
        str += "&";
    });
    $.each(list,function (i,e) {
        let num = $("#"+e.id+e.dangerType).val();
        if(i>0){
            str += "&";
        }
        str += "num="+ num;
    });
    //通过ajax请求发送
    $.ajax({
        type:"post",
        url:"/admin/addDanderWarning",
        data:str,
        beforeSend : function () {
            loadingIndex = layer.msg('处理中', {icon: 16});
            return true;
        },
        success : function (rusult) {
            if(rusult.success){
                //添加成功，跳转到分页查询首页
                window.location.href="/admin/toDangerWarning"
            }else {
                layer.msg(rusult.message, {time:1000, icon:5, shift:6});
            }
        }
    });
}

//继续新增
function RegPersonnelAddAnd() {
    //通过ajax请求发送
    $.ajax({
        type: "post",
        url: "/admin/addRegPersonnel",
        data: {
            serialNumber: serialNumber,
            name: name,
            age: age,
            sex: sex,
            phoneNumber: phoneNumber,
            belongCompany: belongCompany,
            technicalGrade: technicalGrade,
            statu: statu
        },
        beforeSend: function () {
            loadingIndex = layer.msg('处理中', {icon: 16});
            return true;
        },
        success: function (rusult) {
            if (rusult.success) {
                //添加成功，跳转到分页查询首页
                window.location.href = "/admin//toRegPersonnelAdd"
            } else {
                layer.msg(rusult.message, {time: 1000, icon: 5, shift: 6});
            }
        }
    });
}