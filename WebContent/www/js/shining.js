function checkMobile(mobile) {
	if (mobile.length == 11
			&& /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/
					.test(mobile)) {
		return true;
	} else {
		return false;
	}
}

function loadRank(){
	$.ajax({
		type : "POST",
		url : YS.config.path.rootpath+"/handmade/app/product/rankOfProduct",
		success : function(data) {
			if(data.isSuccess){
				$("#rankingul").html(loadMsg_rank_parse(data.result));
			}
		},
		dataType : "json"
	});

}

function loadMsg_rank_parse(list) {
	var html = "";
	for ( var key in list) {
		var product = list[key];
		html += "<li><img src=\"image/user-ava.fw.png\" width=\"80px\">"
				+"<p><span>作者：</span><span class=\"gray\">"+product.author+"</span></p>"
				+"<p><img src=\"image/dianzan.fw.png\" width=\"40px\"><span class=\"orange\">"+product.greatNum+"</span></p>"
				+"</li>";
	}
	return html;
}


function loadHome(){
	$("#shopList_banner").hide();
	appAjax({
		type : "POST",
		url : "app/bulkproduct/findTop",
		success : function(data) {
//			console.log(data);
			if(data.isSuccess){
				showPage('home');
				$("#carousel").html(loadMsg_home_parse(data.result));
				carousel=$("#carousel").carousel({
					pagingDiv: "carousel_dots",
					pagingCssName: "carousel_paging2",
					pagingCssNameSelected: "carousel_paging2_selected",
					preventDefaults:false,
					wrap:true //Set to false to disable the wrap around
				});
			}
		},
		dataType : "json"
	});

}

function loadMsg_home_parse(list) {
	var html = "";
	for ( var key in list) {
		var images = list[key];
		html += "<div class=\"item\">"
				+"<a onclick=\"bulkDetail('dingdan',"+ images.id+")\"><img src=\""
				+images.imageNum
				+"\"></a></div>";
	}
	return html;
}

function loadBulk(){
	$("#shopList_banner").show();
	appAjax({
		type : "POST",
		url : "app/bulkproduct/findBulk",
		success : function(data) {
//			console.log(data);
			if(data.isSuccess){
				showPage('shopList');
				$("#carousel_sl").html(loadMsg_bulk_parse(data.result));
				carousel_sl=$("#carousel_sl").carousel({
					pagingDiv: "carousel_dots_sl",
					pagingCssName: "carousel_paging2",
					pagingCssNameSelected: "carousel_paging2_selected",
					preventDefaults:false,
					wrap:true //Set to false to disable the wrap around
				});
			}
		},
		dataType : "json"
	});
}

function loadMsg_bulk_parse(list) {
	var html = "";
	for ( var key in list) {
		var images = list[key];
		html += "<div class=\"item\" onclick=\"bulkDetail('dingdan',"+ images.id+")\">"
				+"<img src=\""
				+images.imageNum
				+"\"></div>";
	}
	return html;
}





