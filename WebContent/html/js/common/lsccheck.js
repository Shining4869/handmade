//选择客户 --关闭弹框
function selectProduct(stock_product_id){
  	$("#modal-table .table-header").find('button').trigger("click");
  	
//  	var trLen = $("#productTbody").find("tr").length;
	var stockProductIds = $("#productTbody").find("input[name*=record_stockProductId]");
	for (var i = 0; i < stockProductIds.length; i++) {
		if (stockProductIds.eq(i).val() == stock_product_id) {
			return;
		}
	}
	
	$.ajax({
		type: "GET",
		url: YFT.config.path.rootPath + "/lsc/check/product/"+stock_product_id,
		dataType: "html",
		success: function( data ){
			$(data).appendTo($("#productTbody"));
		},
        error: function() {
            alert("网络错误，请重试！");
        }
	});
}
	  
function addRecord(){
	var addrecord = $("#J_iframe_up");
	var title = "请选择库存产品";
    var _id = "#modal-table";
    var _src = YFT.config.path.rootPath+"/lsc/check/products/";
    $(_id+' .modal-body').find("iframe").remove();
    $('<iframe src="'+_src+'" border="0" width=100% height="100%">').appendTo($(_id+' .modal-body'));
    addrecord.click();
    if(title){
  	  $(_id+' .table-header i').html(title);
    }
}

$("#productTbody").on('blur', '.J_ss', function(){
	var planAmount = $(this).parent().prev().find(".J_gg").val();
	if(planAmount && $(this).val()){
		$(this).parent().next().find(".J_dd").val($(this).val()-planAmount);
	}
}).on('keyup', '.J_ss', function(){
	 if (!(/^([1-9][0-9]*|0)(\.[0-9]*)?$/.test(this.value))) {
        this.value = this.value ? this.value.substr(0,(this.value.length-1)) : "";
    } 
});


