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


//身份证去重验证
$("#identityNumber").blur(function() {
    let identityNumber = $("#identityNumber").val();
    //ajax
    $.ajax({
        type : "post",
        url : "/admin/regIdentityCheck",
        data : {
            identityNumber : identityNumber
        },
        success : function(result) {
            if (!result.success) {
                layer.msg(result.message, {time: 1000, icon: 5, shift: 6});
                $("#identityNumber").val("");
            }
        }
    });
});


//更新的ajax请求
function RegPersonnelUpdate() {
    let loadingIndex = -1;
    //获取参数
    let id = $("#id").val();

    let serialNumber = $("#serialNumber").val();
    if(serialNumber == ''){
        layer.msg("工号不能为空", {time: 500, icon: 5, shift: 6});
        return false;
    }
    let name = $("#name").val();
    if(name == ''){
        layer.msg("姓名不能为空", {time: 500, icon: 5, shift: 6});
        return false;
    }
    let age = $("#age").val();
    if(age == ''){
        layer.msg("年龄不能为空", {time: 500, icon: 5, shift: 6});
        return false;
    }
    let sex = $("#sex").val();

    let phoneNumber = $("#phoneNumber").val();
    let Phone_reg = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
    //验证手机格式
    if(phoneNumber != '') {
        if (!Phone_reg.test(phoneNumber)) {
            layer.msg("手机号格式不正确", {time: 500, icon: 5, shift: 6});
            return  false;
        }
    }
    let identityNumber = $("#identityNumber").val();
    //身份证正则规律
    let CardNo_reg = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/

    if(identityNumber == ''){
        layer.msg("身份证号不能为空", {time: 500, icon: 5, shift: 6});
        return false;
    }
    if(!CardNo_reg.test(identityNumber)){
        layer.msg("身份证号格式不正确", {time: 500, icon: 5, shift: 6});
        return false;
    }

    let belongCompany = $("#belongCompany").val();
    if(belongCompany == ''){
        layer.msg("所属公司不能为空", {time: 500, icon: 5, shift: 6});
        return false;
    }
    let technicalGrade = $("#technicalGrade").val();
    if(technicalGrade == ''){
        layer.msg("技术等级不能为空", {time: 500, icon: 5, shift: 6});
        return false;
    }

    let statu = $("#statu").val();


    //通过ajax请求发送
    $.ajax({
        type:"post",
        url:"/admin/updateRegPersonnel",
        //contentType:"json",
        data:{
            id : id,
            serialNumber : serialNumber,
            name : name,
            age : age,
            identityNumber : identityNumber,
            sex : sex,
            phoneNumber : phoneNumber,
            belongCompany : belongCompany,
            technicalGrade : technicalGrade,
            statu : statu
        },
        beforeSend : function () {
            loadingIndex = layer.msg('处理中', {icon: 16});
            return true;
        },
        success : function (result) {
            if(result.success){
                //更新成功 跳转到首页面
                layer.close(loadingIndex);
                window.location.href = "/admin/toRegPersonnel";
            }else {
                layer.msg(result.message, {time:1000, icon:5, shift:6});
            }
        }
    });
}