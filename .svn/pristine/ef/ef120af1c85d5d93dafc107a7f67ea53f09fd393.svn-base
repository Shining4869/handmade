<#escape x as x!"">
<#include "../common/header.ftl">
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">发布材料</a>
        </li>
    </ul><!-- .breadcrumb -->
</div>
<div class="page-content">
<div class="page-header">
</div><!-- /.page-header -->
<!-- PAGE CONTENT BEGINS -->
<div class="row">
<div class="col-xs-12">
<form class="form-horizontal" role="form" id="saveForm" action="${request.contextPath}/admin/material/save/">
<input type="hidden" name="id" value="${(info.id)!''}"/>
<div class="form-group ">
    <label class="col-sm-2 control-label"> 材料名称：</label>
    <div class="col-sm-4">
    	<input type="text" name="more1" class="col-xs-12 col-sm-12" value="${(info.more1)!''}">
    </div>
</div>
<div class="space-4"></div>
<div class="form-group">
    <label class="col-sm-2 control-label"> 编号：</label>
    <div class="col-sm-4">
        <input type="text" name="materialName" class="col-xs-12 col-sm-12" value="${(info.materialName)!''}">
    </div>
</div>
<div class="space-4"></div>
<div class="form-group ">
    <label class="col-sm-2 control-label"> 规格：</label>
    <div class="col-sm-4">
        <input type="text" name="materialSpecification" class="col-xs-12 col-sm-12" value="${(info.materialSpecification)!''}">
    </div>
</div>
<div class="space-4"></div>
<div class="form-group ">
    <label class="col-sm-2 control-label"> 颜色：</label>
    <div class="col-sm-4">
        <input type="text" name="more2" class="col-xs-12 col-sm-12" value="${(info.more2)!''}">
    </div>
</div>
<div class="space-4"></div>
<div class="form-group ">
    <label class="col-sm-2 control-label"> 单位：</label>
    <div class="col-sm-4">
        <input type="text" name="unit" class="col-xs-12 col-sm-12" value="${(info.unit)!''}">
    </div>
</div>
<div class="space-4"></div>
<div class="form-group ">
    <label class="col-sm-2 control-label"> 单位质量：</label>
    <div class="col-sm-4">
        <input type="text" name="unitQuality" class="col-xs-12 col-sm-12" value="${(info.unitQuality)!''}">
    </div>
</div>
<div class="space-4"></div>
<div class="form-group ">
    <label class="col-sm-2 control-label"> 成本单价：</label>
    <div class="col-sm-4">
        <input type="text" name="costPrice" class="col-xs-12 col-sm-12" value="${(info.costPrice)!''}">
    </div>
</div>
<div class="space-4"></div>
<div class="form-group ">
    <label class="col-sm-2 control-label"> 单位单价：</label>
    <div class="col-sm-4">
        <input type="text" name="unitPrice" class="col-xs-12 col-sm-12" value="${(info.unitPrice)!''}">
    </div>
</div>
<div class="space-4"></div>
<div class="form-group">
    <label class="col-sm-2 control-label"> 图片：</label>
    <div class="col-sm-4">
    	<input type="hidden"  name="image" id="imageLogo" value='${(info.image)!""}' />
        <input type="file" name="file_upload" id="file_upload_1" style="display: block;float: left;margin-top: 20px;"/>
		<img id="first_img" style="width:100px;height:100px;" src="<#if info?has_content && info.image?has_content>${request.contextPath}/resources/admin/store/${info.image}</#if>"/>
    </div>
</div>
<div class="space-4"></div>
<div class="clearfix">
    <div class="col-sm-offset-1 col-sm-10">
        <a class="btn btn-info" type="button" id="save">
            <i class="icon-ok bigger-110"></i>
            提交
        </a>
    </div>
</div>
</form>
</div>
</div><!-- /.row -->
<div class="hr hr32 hr-dotted"></div>
<!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
</div><!-- /.row -->
<#include "../common/footer.ftl">
<script src="${request.contextPath}/resources/admin/js/jquery.uploadify.min.js?v=${jslevel}" type="text/javascript"></script>
<script type="text/javascript">
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
            $("#first_img").attr("src", "${request.contextPath}/resources/admin/store/" + data);
         	$("#imageLogo").val(data);
            } 
	});
});
 </script>
</#escape>