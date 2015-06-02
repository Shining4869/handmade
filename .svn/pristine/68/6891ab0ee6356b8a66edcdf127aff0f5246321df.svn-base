$(function(){
	$("#tuangouin").bind("click",function(){
//		alert($(this).attr("checked"));
		
		if($(this).prop("checked")){
			$("#customerStatus").val(1);
			$("#customerStatus_div").show();
			
		}else {
			$("#customerStatus").val(0);
			$("#customerStatus_div").hide();
		}
		
	})
	
	
	$(".addSyu").bind("click",function(){
		
		var addTr ="<tr>"+ $("#add_tr").html()+"</tr>";
		$("#sku_list").append(addTr);
		$("#sku_list .addSyu").hide();
		$("#sku_list .delSku").show();
		
	//	$("#add_tr input").val("");
		
		setDelSkuEvent();
	});
	
	setDelSkuEvent();
})


function setDelSkuEvent(){
	$(".delSku").bind("click",function(){
		var  object = this;
		 bootbox.confirm("确认删除", function(result) {
		       if(result) {
		    	   	$(object).parent().parent().remove();
		       }
		 });
	})
}

var name=false;
var pic=false;
var desc=false;
var title=false;
var rprice=false;
var price=false;
var weight=false;

function check(key1,key2){
	checkProductName();
	checkPic();
	checkDesc();
	checkTitle();
	checkRetailPrice();
	checkPrice();
	checkWeight();
	if(name&&pic&&desc&&title&&rprice&&price&&weight){
		saveFun(key1,key2);
	}else{
		alert("您填入的信息不完整");
	}
}

function checkProductName(){
	var a=$("#productName").val();
	//alert(a);
	if(a!=""){
		name=true;
	}else{
		name=false;
	}
}

function checkPic(){
	var a=$("#imageLogo").val();
	//alert(a);
	if(a!=""){
		pic=true;
	}else{
		pic=false;
	}
}

function checkDesc(){
	var n=UE.getEditor('editor').getContent();
	//alert(n);
	if(n!=""){
		desc=true;
	}else{
		desc=false;
	}
}

function checkTitle(){
	var a=$("#titles").val();
	//alert(a);
	if(a!=""){
		title=true;
	}else{
		title=false;
	}
}

function checkRetailPrice(){
	var str=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
	var a=$("#skuRetailPrices").val();
	//alert(a);
	if(!str.test(a)){
		alert("含有非法字符");
		return;
	}
	if(a!=""){
		rprice=true;
	}else{
		rprice=false;
	}
}

function checkPrice(){
	var str=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
	var a=$("#skuPrices").val();
	//alert(a);
	if(!str.test(a)){
		alert("含有非法字符");
		return;
	}
	if(a!=""){
		price=true;
	}else{
		price=false;
	}
}

function checkWeight(){
	var str=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
	var a=$("#weights").val();
	//alert(a);
	if(!str.test(a)){
		alert("含有非法字符");
		return;
	}
	if(a!=""){
		weight=true;
	}else{
		weight=false;
	}
}

function checkTem(){
	var str =/^[0-9]+\.{0,1}[0-9]{0,2}$/;
	var a=$("#temperature").val();
	if(!str.test(a)){
		alert("含有非法字符");
		return;
	}
}

function checkBulk(){
	var str =/^[0-9]+\.{0,1}[0-9]{0,2}$/;
	var a=$("#bulk").val();
	if(!str.test(a)){
		alert("含有非法字符");
		return;
	}
}

function checkOrder(){
	var str=/^[1-9][0-9]$|^[1-9]$/;
	var a=$("#productOrder").val();
	if(!str.test(a)){
		alert("请输入0-99之间的数字");
		return;
	}
}

function checkPPrice(){
	var str =/^[0-9]+\.{0,1}[0-9]{0,2}$/;
	var a=$("#preferentialPrice").val();
	if(!str.test(a)){
		alert("含有非法字符");
		return;
	}
}
function checkMPrice(){
	var str =/^[0-9]+\.{0,1}[0-9]{0,2}$/;
	var a=$("#marketPrice").val();
	if(!str.test(a)){
		alert("含有非法字符");
		return;
	}
}
