//表单验证
$("#Form").validate({
	rules : {
		"orderTime" : {
			required : true
       	},
       	"supName" : {
			required : true
       	},
       	"shipFee" : {
			required : true,
			money:true
       	}
       	
	},
	messages: {
	
	},
	
});



//选择供应商 --关闭弹框
function selectSup(user_name, user_id){
	$("#supName").val(user_name);
	$("#supId").val(user_id);
	$("#modal-table .table-header").find('button').trigger("click");
}


function selectUser(user_name, user_id, realName){
	$("#purchaseName").val(user_name);
	$("#purchaseId").val(user_id);
	$("#modal-table .table-header").find('button').trigger("click");
}

function selectSku(skuId){
	$("#modal-table .table-header").find('button').trigger("click");
	
	var trLen = $("#skuTbody").find("tr").length;
	
	var skuIds = $("#skuTbody").find("input[name*=skuId]");
	for (var i = 0; i < skuIds.length; i++) {
		if (skuIds.eq(i).val() == skuId) {
			return;
		}
	}
	
	$.ajax({
		type: "GET",
		url: YFT.config.path.rootPath + "/order/buy/sku?index=" + trLen,
		data: "skuId=" + skuId, 
		dataType: "html",
		success: function( data ){
			$(data).appendTo($("#skuTbody"));
		},
        error: function() {
            alert("网络错误，请重试！");
        }
	});
	
}

