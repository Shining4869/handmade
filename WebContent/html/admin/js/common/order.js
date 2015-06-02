var AJAX_UPDATE_STATUS_URL = SG.config.path.rootPath+"/admin/order/";

function updateStatus(id, action){
	var content = $("#J_content").html();
	var shipNo = $("#J_shipNo").val();
	$.ajax({
		type: "GET",
		url: AJAX_UPDATE_STATUS_URL + action,
		data: {tradeId:id, content:content, shipNo:shipNo}, 
		dataType: "json",
		success: function( data ){
			alert(data.message);
			if(data && data.isSuccess == true){
				window.location.reload();
			}
		},
        error: function() {
            alert("网络错误，请重试！");
        }
	});
}