$(document).ready(function () {
	document.documentElement.style.fontSize = document.documentElement.clientWidth / 5 + 'px';
    toProjectDetail(GetQueryString("itemNo"),GetQueryString("queryDate"));

	Return();
});


function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

// 详情
function toProjectDetail(itemNo,queryDate){
		$.ajax({
	    type: 'post',
	    url: '/phone/toProjectDetail?itemNo='+ itemNo +'&queryDate='+ queryDate, // ajax请求路径
	    dataType: 'json',
	    success: function (data) {
	    	let craneWeight = data.craneWeight == "8888"?"无数据":data.craneWeight;
			let liferWeight = data.liferWeight == "8888"?"无数据":data.liferWeight;
			let noiseWarning = data.noiseWarning == "8888"?"无数据":data.noiseWarning;
			let dustWarning = data.dustWarning == "8888"?"无数据":data.dustWarning;
			let carWarning = data.carWarning == "8888"?"无数据":data.carWarning;

			$("#entryName").html(data.itemName);
			$("#detailsDate").html(data.statisticsDate.substring(0,10));
			$("#safetyIndex").html(data.score);
			$("#ranking").html(data.rankNumT);
			$("#workerTrainingRate").html(data.workerRate);
			$("#personnelArrivalRate").html(data.manaRate);
			$("#overloadTowerCrane").html(craneWeight);
			$("#overloadCrane").html(liferWeight);
			$("#xcessiveNoise").html(noiseWarning);
			$("#DustRaisingExceedingStandard").html(dustWarning);
			$("#mudTruckViolations").html(carWarning);

	    },
	    error: function () {
	        alert("数据请求失败！")
	    }
	});
}

function Return(){
	$(".Return").click(function(){
		window.history.go(-1);
	})
}
