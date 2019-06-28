//获取url路径中参数的值
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}
/**
 * 获取项目根路径,如:http://localhost:8083/uimcardprj
 */
function getRootPath() {
	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;

	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);

	//获取主机地址，如： http://localhost:8083
	var localhostPath = curWwwPath.substring(0, pos);

	//获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

	return(localhostPath);
}

/**
 * 比较当前时间和跟进日期，如果大于，返回true,如果小于,返回false
 * @param {String} follDate:yyyy-MM-dd
 * @param {String} mytoday:yyyy-MM-dd
 */
function dateCompare(follDate, mytoday) {

	var arrFollDate = follDate.split('-');
	var follDateMonth = parseInt(arrFollDate[1]) - 1;
	var follDateBegin = new Date(arrFollDate[0], follDateMonth, arrFollDate[2]);
	var follDates = follDateBegin.getTime();

	var arrMytoday = mytoday.split('-');
	var mytodayMonth = parseInt(arrMytoday[1]) - 1;
	var mytodayBegin = new Date(arrMytoday[0], month, arrMytoday[2]);
	var mytodays = mytodayBegin.getTime();

	if (mytodays >= follDates) {
		return true;
	} else {
		return false;
	}
}

/**
 * 获取url中的携带的参数
 */
function getParameterFromUrl(name) {
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
	var r = decodeURI(window.location.search).substr(1).match(reg);
	if(r != null)
		return unescape(r[2]);
	return null;
}

/**
 * 获取当前时间(yyyy-MM-DD HH:MM:SS)
 */
function getCurrentTime() {
	var now = new Date();
	var year = now.getFullYear(); //年
	var month = now.getMonth() + 1; //月
	var day = now.getDate(); //日
	var hh = now.getHours(); //时
	var mm = now.getMinutes(); //分
	var ss = now.getSeconds(); //秒
	var clock = year + "-";
	if(month < 10)
		clock += "0";
	clock += month + "-";
	if(day < 10)
		clock += "0";
	clock += day + " ";
	if(hh < 10)
		clock += "0";
	clock += hh + ":";
	if(mm < 10) clock += '0';
	clock += mm + ":";
	if(ss < 10) clock += '0';
	clock += ss;
	return(clock);
}

/**
 * 刷新指定页面
 * @param {Object} url
 */
function flush(url) {
	window.location.href = getRootPath() + url;
}

/**
 * 将时间戳转换成日期格式
 * console.log(timestampToTime(1403058804));//2014-06-18 10:33:24
 * */
function timestampToTime(timestamp) {
	if(timestamp == "" || timestamp == null) return null;
	var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
	var Y = date.getFullYear() + '-';
	var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
	var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
	var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
	var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
	var s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();

	return Y + M + D + h + m + s;
}

/**
 * 将时间戳转换成日期格式(不包含时分秒)
 * console.log(timestampToTime(1403058804));//2014-06-18
 * */
function timestampToTimeWithoutHMS(timestamp) {
	if(timestamp == "" || timestamp == null) return null;
	var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
	var Y = date.getFullYear() + '-';
	var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
	var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
	//var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
	//var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
	//var s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();

	return Y + M + D ;//+ h + m + s;
}

/**
 * 去除字符串空格
 * */
function Trim(str){ 
 return str.replace(/(^\s*)|(\s*$)/g, ""); 
}

/**
 * 解析fileJson数据,并设置给控件属性
 * @param {Object} widgetId 控件id
 * @param {Object} fileListJsonStr 文件数据
 */
function parseFileJsonAndSetLink(widgetId, fileListJsonStr) {
	if(fileListJsonStr == null || fileListJsonStr == ""){
		return;
	}
	var fileJson = $.parseJSON(fileListJsonStr);
	for(var i = 0; i < fileJson.length; i++) {
		var fileName = fileJson[i].fileName;
		var link = getRootPath() + '/components/upload/download.htm?downloadId=' + fileJson[i].id;
		$("#" + widgetId).append("<a download href='" + link + "'>" + fileName + "</a></br>");
	}
}





$(document).ready(function() { 
	$("[data-seqype]:eq(0)").click(function(){
		var type = $("[data-seqype]:eq(0)").attr("data-seqype");
		getSeqIndex(type);
	});
	$("[data-seqype]:eq(0)").focus(function(){
		  this.blur();
	});
	
})

var getSeqIndex = function(type){
	$.ajax({
		url : getRootPath()+'/platform/storage/inventoryNextSeq/getInventoryNextSeq.htm',
		type : 'post',
		data : {"type":type},
		dataType : 'json',
		success : function(data) {
			$("[data-seqype]:eq(0)").val(data.kcSeq);	
		}
	});
}
// todo 2019/4/24 判断领料添加页面
$(function f() {
	var idmark = GetQueryString("mark");
	if (idmark==1){
		$("#workType").remove();
	} else if(idmark==2){
		$("#generalType").remove();
	}
});

// 格式化时间
function dateFormat(oDate, fmt) {
	var o = {
		"M+": oDate.getMonth() + 1, //月份
		"d+": oDate.getDate(), //日
		"h+": oDate.getHours(), //小时
		"m+": oDate.getMinutes(), //分
		"s+": oDate.getSeconds(), //秒
		"q+": Math.floor((oDate.getMonth() + 3) / 3), //季度
		"S": oDate.getMilliseconds()//毫秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (oDate.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}










