function buildValidate(){
	
}

$(function() {
	$('#file_upload_0').uploadify({
		'auto'     : true,
		'buttonText' : '上传图片',
		'width' : '95px',
		'fileTypeExts' : '*.gif;*.jpg;*.png;*.bmp;*.jpeg',
		'fileSizeLimit': '2048KB',
		'swf'      : YS.config.path.rootPath+'/resources/admin/flash/uploadify.swf', // 选择文件flash文件
		'uploader' : YS.config.path.rootPath+'/uploadify/upload/build', // 处理上传的Servlet
		'onUploadSuccess' : function (event, data, fileObj) {   //当文件上传完成时触发  
            var title = event.name;  
            var fsize = event.size; 
        	var imgurl = YS.config.path.rootPath+"/resources/admin/store/"+data;
        	$("#first_img").attr("src",imgurl);
        	$("#imageLogo").val(data);
		} 
	});
});

$(function() {
	$('#file_upload_1').uploadify({
		'auto'     : true,
		'buttonText' : '上传图片',
		'width' : '95px',
		'fileTypeExts' : '*.gif;*.jpg;*.png;*.bmp;*.jpeg',
		'fileSizeLimit': '2048KB',
		'swf'      : YS.config.path.rootPath+'/resources/admin/flash/uploadify.swf', // 选择文件flash文件
		'uploader' : YS.config.path.rootPath+'/uploadify/upload/build', // 处理上传的Servlet
		'onUploadSuccess' : function (event, data, fileObj) {   //当文件上传完成时触发  
            var title = event.name;  
            var fsize = event.size; 
            var imgurl = YS.config.path.rootPath+"/resources/admin/store/"+data;
            var FrontAspectImg = "<a class=\"btn btn-primary btn-app radius-4\"><img src=\""+imgurl+"\" width=\"80\" height=\"80\">" +
            		"<span class=\"badge badge-pink J_delete_a\">delete</span><input type=\"hidden\" value=\""+data+"\" name=\"frontAspect\"></a>"
            var html = $(".J_upload_img").html();
            $(".J_upload_img").html(html+FrontAspectImg);
            } 
	});
});

$(function() {
	$('#file_upload_8').uploadify({
		'auto'     : true,
		'buttonText' : 'Upload Brochure',
		'width' : '95px',
		'buttonClass' : 'co',
		'fileTypeExts' : '*.pdf',
		'fileSizeLimit': '50000KB',
		'swf'      : YS.config.path.rootPath+'/resources/admin/flash/uploadify.swf', // 选择文件flash文件
		'uploader' : YS.config.path.rootPath+'/uploadify/upload/pdf', // 处理上传的Servlet
		'onUploadSuccess' : function (event, data, fileObj) {   //当文件上传完成时触发 
			alert("Upload sucessfully！");
            $("input[name='compInfoPdf']").val(data);
           
            var root = YS.config.path.rootPath;
            $("#pdfDown").attr("href",root+"/resources/admin/store/"+data).html("Download");
            } 
	});
});

$(document).ready(function(){
	$(document).on("click",".J_delete_a",function(){
		var _this = $(this);
		_this.parent().remove();
	});
//	$(".J_delete_a").on("click",function(){
//		var _this = $(this);
//		_this.parent().remove();
//	});
});
