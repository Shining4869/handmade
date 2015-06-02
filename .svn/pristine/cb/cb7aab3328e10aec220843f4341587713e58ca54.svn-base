//选择客户 --关闭弹框
function selectSku(stockType,skuId){
  	$("#modal-table .table-header").find('button').trigger("click");
  	
//  	var trLen = $("#productTbody").find("tr").length;
	var skuIds = $("#skuTbody").find("input[name*=record_skuId]");
	for (var i = 0; i < skuIds.length; i++) {
		if (skuIds.eq(i).val() == skuId) {
			return;
		}
	}
	
	$.ajax({
		type: "GET",
		url: YFT.config.path.rootPath + "/lsc/"+stockType+"/sku/",
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
	  
function addRecord(stockType){
	var addrecord = $("#J_iframe_up");
	var title = "请选择产品规格";
    var _id = "#modal-table";
    var _src = YFT.config.path.rootPath+"/lsc/"+stockType+"/pskus/";
    $(_id+' .modal-body').find("iframe").remove();
    $('<iframe src="'+_src+'" border="0" width=100% height="100%">').appendTo($(_id+' .modal-body'));
    addrecord.click();
    if(title){
  	  $(_id+' .table-header i').html(title);
    }
}

$("#skuTbody").on('blur', '.J_ss', function(){
	var planAmount = $(this).parent().prev().find(".J_gg").val();
	if(planAmount && $(this).val()){
		$(this).parent().next().find(".J_dd").val($(this).val()-planAmount);
	}
}).on('keyup', '.J_ss', function(){
	 if (!(/^([1-9][0-9]*|0)(\.[0-9]*)?$/.test(this.value))) {
        this.value = this.value ? this.value.substr(0,(this.value.length-1)) : "";
    } 
});

function selectSup(use_name, use_id) {
	$("#J_user_Name").val(use_name);
	$("#J_user_Id").val(use_id);
	$("#modal-table .table-header").find('button').trigger("click");
}

function selectCus(use_name, use_id) {
	$("#J_cus_Name").val(use_name);
	$("#J_cus_Id").val(use_id);
	$("#modal-table .table-header").find('button').trigger("click");
}
