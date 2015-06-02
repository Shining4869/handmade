
function intent(){
	console.log("wujie");
	var toUrl = $(this).attr("data-toUrl");
    window.location.href = toUrl;
}

$(function(){
	$("#province").bind("change",function(){
		var url =$(this).attr("tourl");
		console.log(url);
		$.ajax({
	 		   type: "POST",
	 		   url: url,
	 		   data:  {"parentId":$(this).val()},
	 		   success: function(data){
	 		     if(data.isSuccess){
	 		    	 	var html = '<option value="">级别</option>';
	 		    	 	if(!! data.result) {
	 		    	 		for(var index=0 in data.result){
	 		    	 			var p = data.result[index];
	 		    	 			html += '<option value="'+p.id+'"  > '+ p.name+'</option> ';
	 		    	 		}
	 		    	 	}
	 		    	 	$("#pCity").html(html);
	 		     }else{
	 		    	bootbox.alert("保存失败");
	 		     }
	 		   },
	 		   dataType: "json"
	 		});
	});
	$("#pCity").bind("change",function(){
		var url =$(this).attr("tourl");
		console.log(url);
		$.ajax({
	 		   type: "POST",
	 		   url: url,
	 		   data:  {"parentId":$(this).val()},
	 		   success: function(data){
	 		     if(data.isSuccess){
	 		    	 	var html = '<option value="">级别</option>';
	 		    	 	if(!! data.result) {
	 		    	 		for(var index=0 in data.result){
	 		    	 			var p = data.result[index];
	 		    	 			html += '<option value="'+p.id+'"  > '+ p.name+'</option> ';
	 		    	 		}
	 		    	 	}
	 		    	 	$("#region").html(html);
	 		     }else{
	 		    	bootbox.alert("保存失败");
	 		     }
	 		   },
	 		   dataType: "json"
	 		});
	});
});

$(function(){
    $(".toUrl-wj").bind("click",function(){
      var toUrl = $(this).attr("data-toUrl");
      console.log(toUrl);   
      var checks = $("div.check_two:visible");
      console.log(checks);
      console.log(checks.length);
      var ids = "";
      for(var i=0;i<checks.length;i++){
    	  console.log(checks.eq(i).find("input").val());
    	  ids = ids +","+ checks.eq(i).find("input").val();
      }
      ids = ids.substring(1);
      window.location.href = toUrl+"?ids="+ids;
    });
 });
