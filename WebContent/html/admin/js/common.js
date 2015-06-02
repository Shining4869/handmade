//TODO  正式注释
function log(msg){
//	console.log(msg);
}

function save(url, toUrl){
	saveForm("saveForm",url, toUrl);
}

function checkNum(sort){
	  var reg = new RegExp("^[0-9]*$");
	 return reg.test(sort);
}

function saveForm(key,url, toUrl){
	var queryString = $("#"+key).serialize();

	$.ajax({
		type: "POST",
		'url': url,
		data: queryString, 
		dataType: "json",
		success: function( data ){
			alert(data.message);
			if(data.isSuccess == true){
				if(toUrl){
					if("do_refesh"==toUrl || 'refresh'==toUrl){
						location.href=location.href;
					}else if("ajax" ==toUrl){
						
					}else {
						location.href=toUrl;
					}
				}
			}else {
//				alert(data.message);
			}
		},
        error: function() {
            alert("网络错误，请重试！");
        }
	});
}

$(function(){
    $(".toUrl").bind("click",function(){
      var toUrl = $(this).attr("data-toUrl");
      window.location.href = toUrl;
    });
 })

function selectAll(){
	if($("#all").attr("checked")){
		$("table input[type=checkbox]").attr("checked",true);
	}else {
		$("table input[type=checkbox]").attr("checked",false);
	}
}

$(function(){
	$("#doCheck input").bind("click",function(){
		if($(this).attr("checked")){
			$("#doCheck input").attr("checked",false);
			$(this).attr("checked",true);
		}
	});
});

function changeImg(t){
	$(t).attr("src",$(t).attr("src")+new Date());
}

function yzmSave(checkUrl, url, toUrl){
	var yzm = $("#yzm").val();
	$.ajax({
		type: "POST",
		url: checkUrl,
		data: {"yzm":yzm}, 
		dataType: "json",
		success: function( data ){
//			alert(data.message);
			if(data== true){
				save(url, toUrl);
			}else{
				alert("Verification code error");
			}
		},
        error: function() {
            alert("网络错误，请重试！");
        }
	});
}

function deleteOne(id, url){
	var language = $("#language").val();
	if(confirm("确定删除？")){
		if(language){
			$.ajax({
				type: "GET",
				url: url,
				data: {id : id, language : language}, 
				dataType: "json",
				success: function( data ){
					if(data){
						alert("删除成功！");
						window.location.reload();
					}else{
						alert("删除失败！");
					}
				},
		        error: function() {
		            alert("网络错误，请重试！");
		        }
			});
		}else{
			$.ajax({
				type: "GET",
				url: url,
				data: {id : id}, 
				dataType: "json",
				success: function( data ){
					if(data){
						alert("删除成功！");
						window.location.reload();
					}else{
						alert("删除失败！");
					}
				},
		        error: function() {
		            alert("网络错误，请重试！");
		        }
			});
		}
	}
}

var REGION_AJAX_URL ;
$(function(){
	if(!!YS){
		REGION_AJAX_URL = YS.config.path.rootPath+"/common/findregionByParentId";
	}
});


$(document).ready(function(){
	var type = $("#type").val();
	$("#country").change(function(){
		var countryId = $("#country").val();
		try{
			$("#countryMobileNo").html(countryMobileMap.get(countryId));
			$("#countryMobileNoInput").val(countryMobileMap.get(countryId));
		}catch(e){}
		$.ajax({
			type: "GET",
			url: REGION_AJAX_URL,
			data: {parentId : countryId}, 
			dataType: "json",
			success: function( data ){
				var html = "<option></option>";
				for(var i=0; i<data.length;i++){
					if(type=="chinese"){
						html = html + "<option value="+data[i].regionId+">"+data[i].regionChineseName+"</option>"
					}else{
//						alert("english");
						html = html + "<option value="+data[i].regionId+">"+data[i].regionEnglishName+"</option>"
					}
				}
				$("#state").html(html);
				$("#city").html("");
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
	});
	
	$("#state").change(function(){
		var state = $("#state").val();
		$.ajax({
			type: "GET",
			url: REGION_AJAX_URL,
			data: {parentId : state}, 
			dataType: "json",
			success: function( data ){
				var html = "";
				for(var i=0; i<data.length;i++){
					if(type=="chinese"){
						html = html + "<option value="+data[i].regionId+">"+data[i].regionChineseName+"</option>"
					}else{
						html = html + "<option value="+data[i].regionId+">"+data[i].regionEnglishName+"</option>"
					}
				}
				$("#city").html(html);
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
	});
});






function Map(){
this.container = new Object();
}


Map.prototype.put = function(key, value){
this.container[key] = value;
}


Map.prototype.get = function(key){
return this.container[key];
}


Map.prototype.keySet = function() {
var keyset = new Array();
var count = 0;
for (var key in this.container) {
//跳过object的extend函数
if (key == 'extend') {
continue;
}
keyset[count] = key;
count++;
}
return keyset;
}


Map.prototype.size = function() {
var count = 0;
for (var key in this.container) {
//跳过object的extend函数
if (key == 'extend'){
continue;
}
count++;
}
return count;
}


Map.prototype.remove = function(key) {
delete this.container[key];
}


Map.prototype.toString = function(){
var str = "";
for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
str = str + keys[i] + "=" + this.container[keys[i]] + ";\n";
}
return str;
} 

var countryMobileMap = new Map();
countryMobileMap.put("1","0044");//英国
countryMobileMap.put("62","00351");//葡萄牙
countryMobileMap.put("104","0034");//西班牙
countryMobileMap.put("178","0030");//希腊
countryMobileMap.put("134","0039"); //意大利

