function resetTabs(obj) {
    $(obj).parent().parent().next(".content").children().remove();
    $(obj).parent().find("li").removeClass("selectActive");
}

function loadTab() {

    $(".content > div").hide();
    $(".horizontal").each(function () {
        $(this).find("li:first").addClass("selectActive");
    });
    $(".content").each(function () {
        $(this).load($(".horizontal > li:first").attr("name"));
    });
    $(".horizontal li").on("click", function (e) {
        e.preventDefault();
        if ($(this).attr("class") == "box-shadow selectActive") {
            return;
        } else if($(this).attr("name") == "VideoSurveillance"){
            if(sessionStorage.getItem("name") == null){
                resetTabs(this);
                $(this).addClass("selectActive");
                sessionStorage.setItem("name", "VideoSurveillance");
                $("#content").load("VideoSurveillance");
            }else{
                sessionStorage.setItem("Newname", "index");
                location=location;
            }
        }else {
            resetTabs(this);
            $(this).addClass("selectActive");
            $("#content").load($(this).attr("name"));
        }
    });

    if (sessionStorage.getItem("Newname") ==  "index" ){
        $(".horizontal li").removeClass("selectActive");
        $(".horizontal li:nth-child(5)").addClass("selectActive");
        $("#content").load("VideoSurveillance")
        sessionStorage.removeItem("Newname");
    }
}