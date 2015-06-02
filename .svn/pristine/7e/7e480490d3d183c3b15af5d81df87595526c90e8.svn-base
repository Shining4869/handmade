function saveTime(id){
	$.ajax({
		   type: "GET",
		   url: $('#saveForm'+id).attr('data-url'),
		   data: $('#saveForm'+id).serialize(),
		   success: function(data){
		     if(data.isSuccess){
		    	 //location.href=location.href;
		    	alert("保存成功");
		    	//
		     }else{
		    	alert("保存失败");
		     }
		   },
		   dataType: "json"
		});
}

function saveUserInfo(toUrl){
	$.ajax({
		   type: "GET",
		   url: $('#saveForm').attr('data-url'),
		   data: $('#saveForm').serialize(),
		   success: function(data){
		     if(data.isSuccess){
		    	 alert("保存成功");
		    	 location.href=toUrl;
		     }else{
		    	alert(data.message);
		     }
		   },
		   dataType: "json"
		});
}

function delProduct(id,url){
	if(confirm("确定删除？")){
	$.ajax({
		   type: "GET",
		   url: url,
		   data: {"id":id},
		   success: function(data){
		     if(data.isSuccess){
		    	location.href=location.href;
		    	alert("删除成功");
		    	//
		     }else{
		    	alert("删除失败");
		     }
		   },
		   dataType: "json"
		});
	}
}

function delMaterial(id,url){
	$.ajax({
		   type: "GET",
		   url: url,
		   data: {"id":id},
		   success: function(data){
		     if(data.isSuccess){
		    	location.href=location.href;
		    	alert("删除成功");
		    	//
		     }else{
		    	alert("删除失败");
		     }
		   },
		   dataType: "json"
		});
}
