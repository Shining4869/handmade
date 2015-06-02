<#escape x as x!"">
<#include "../common/header.ftl">
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">发布作品</a>
        </li>
    </ul><!-- .breadcrumb -->
</div>
<div class="page-content">
<div class="page-header">
</div><!-- /.page-header -->
<!-- PAGE CONTENT BEGINS -->
<div class="row">
<div class="col-xs-12">
<form id="saveForm" class="form-horizontal" action="${request.contextPath}/admin/product/addProduct/" enctype="multipart/form-data" method="post">
<div class="form-group ">
    <label class="col-sm-2 control-label"> 标题：</label>
    <div class="col-sm-4">
        <input type="text" id="title" name="title" class="col-xs-12 col-sm-12" value="${productInfo.title}">
        <span class="errorClass"></span>
    	<input type="hidden" name="id" value='${(productInfo.id)!''}'>
    </div>
</div>

<div class="space-4"></div>

<div class="form-group">
    <label class="col-sm-2 control-label"> 作者：</label>
    <div class="col-sm-4">
        <input type="text" id="author" name="author" class="col-xs-12 col-sm-12" value="${productInfo.author}">
        <span class="errorClass"></span>
    </div>
</div>

<div class="space-4"></div>

<div class="form-group ">
    <label class="col-sm-2 control-label"> 手机号：</label>
    <div class="col-sm-4">
        <input type="text" id="mobile" name="mobile" class="col-xs-12 col-sm-12" value="${productInfo.mobile}">
        <span class="errorClass"></span>
    </div>
</div>

<div class="space-4"></div>

<div class="form-group ">
    <label class="col-sm-2 control-label"> 微信号：</label>
    <div class="col-sm-4">
        <input type="text" id="weixin" name="weixin" class="col-xs-12 col-sm-12" value="${productInfo.weixin}">
        <span class="errorClass"></span>
    </div>
</div>

<div class="space-4"></div>

<div class="form-group ">
    <label class="col-sm-2 control-label"> 类别：</label>
    <div class="col-sm-4">
        <select class="col-xs-12 col-sm-12" name="type">
        	<#if types?has_content>
        	<#list types as type>
            <option value="${type.cateName}" <#if type.cateName==productInfo.type>selected</#if>>${type.cateName}</option>
            </#list>
            </#if>
        </select>
    </div>
</div>

<div class="space-4"></div>

<!--<div class="form-group">
    <label class="col-sm-2 control-label"> 图片：</label>
    <div class="col-sm-4">
        <button>上传</button>
        <br>
        <img width="100px" height="100px" src="">
        <img width="100px" height="100px" src="">
        <img width="100px" height="100px" src="">
    </div>
</div>-->
<div class="space-4"></div>


<div class="form-group">
                                
                                <label class="col-sm-2 control-label">传播方图：</label>
                                  <div class="col-sm-4">
                                    	<a class="btn btn-primary btn-app radius-4">
                                        <img id="first_img" src="${request.contextPath}/resources/admin/<#if productInfo?has_content>${productInfo.more1}</#if>" width="80" >
                                      </a>
                                       <input type="file" name="more1" id="file_upload_0" value="<#if productInfo?has_content>${productInfo.more1}</#if>" class="col-xs-12 col-sm-6"/>
                                  </div>
                                </div>
                                
                                
                                <div class="form-group">
                                  <label class="col-sm-2 control-label" >产品轮播图:</label>
                                  <div class="col-xs-12 col-sm-6" id="J_duotu">
                                    <div class="clearfix J_upload_img">
                                    <#if imagePaths?has_content>
	                				<#list imagePaths as imagePath>
                                   	 <a class="btn btn-primary btn-app radius-4 J_clean_a">
                                        <img src="${request.contextPath}/resources/admin/${imagePath}" width="100" height="100">
                                      </a>
                                      </#list>
                                      </#if>
                                    </div>
                                    <img src="" width="100" height="100" style="display:none">
                                    <input type="file" name="file_upload" class="col-xs-12 col-sm-6 J_upload_file"/>
                                    <img src="" width="100" height="100" style="display:none">
                                    <input type="file" name="file_upload" style="display:none" class="col-xs-12 col-sm-6 J_upload_file"/>
                                    <img src="" width="100" height="100" style="display:none">
                                    <input type="file" name="file_upload" style="display:none" class="col-xs-12 col-sm-6 J_upload_file"/>
                                    <img src="" width="100" height="100" style="display:none">
                                    <input type="file" name="file_upload" class="col-xs-12 col-sm-6 J_upload_file" style="display:none"/>
                                    <img src="" width="100" height="100" style="display:none">
                                    <input type="file" name="file_upload" class="col-xs-12 col-sm-6 J_upload_file" style="display:none"/>
                                    <img src="" width="100" height="100" style="display:none">
                                    <input type="file" name="file_upload" class="col-xs-12 col-sm-6 J_upload_file" style="display:none"/>
                                    <img src="" width="100" height="100" style="display:none">
                                    <input type="file" name="file_upload" class="col-xs-12 col-sm-6 J_upload_file" style="display:none"/>
                                    <img src="" width="100" height="100" style="display:none">
                                    <input type="file" name="file_upload" class="col-xs-12 col-sm-6 J_upload_file" style="display:none"/>
                                    <img src="" width="100" height="100" style="display:none">
                                    <input type="file" name="file_upload" class="col-xs-12 col-sm-6 J_upload_file" style="display:none"/>
                                    <img src="" width="100" height="100" style="display:none">
                                    <input type="file" name="file_upload" class="col-xs-12 col-sm-6 J_upload_file" style="display:none"/>
                                    <input type="button" value="清除图片" onclick="clean();"/>
                                  </div>
                                  <script type="text/javascript">
                                    	function clean(){
                                    		$(".J_clean_a").remove();
                                    		$("#J_duotu > img").attr("src","").css("display","none");
                                    		$("#J_duotu > input[type='file']").val("").hide().eq(0).show();
                                    	}
                                    </script>
                                </div>

<div class="space-4"></div>

<div class="form-group ">
    <label class="col-sm-2 control-label"> 作品介绍：</label>
    <textarea style="height:100px;width:400px" name="content" id="content">
    ${productInfo.content}
	</textarea>
</div>

<div class="space-4"></div>
<div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
            <div class="tabbable ">
                <div class="tab-content">
                    <div class="table-responsive">
                        <div class="dataTables_wrapper">
                            <table id="J_Table" class="table table-striped table-bordered table-hover J_Table" >
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label>
                                            <input type="checkbox" class="ace J_SelectAll">
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th>材料编号</th>
                                    <th>材料名称</th>
                                    <th>材料规格</th>
                                    <th>材料颜色</th>
                                    <th>材料单位</th>
                                    <th>单位质量</th>
                                    <th>用量</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if materials?has_content>
                                <#list materials as material>
                                <tr id="tr${material.id}">
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace J_SelectAll">
                                        <!--<input type="hidden" name="id" value="${material.id}">-->
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>${material.materialName}</th>
                                <th>${material.more1}</th>
                                <th>${material.materialSpecification}</th>
                                <th>${material.more2}</th>
                                <th>${material.unit}</th>
                                <th>${material.unitQuality}</th>
                                <th>${(material.more3)!''}</th>
                                </tr>
                                </#list>
                                </#if>
                                </tbody>
                            </table>
                        </div>
                    </div><!-- /.table-responsive -->
                </div>
            </div>
        </div><!-- /.row -->
        <div class="hr hr32 hr-dotted"></div>
        <!-- PAGE CONTENT ENDS -->
    </div><!-- /.col -->
</div>

<div class="space-4"></div>

<div class="clearfix">
    <div class="col-sm-offset-1 col-sm-10">
        <button class="btn btn-info" type="button" onclick="toCheck('${request.contextPath}/admin/productInfo/list/p1');">
            <i class="icon-ok bigger-110"></i>
            提交
        </button>
    </div>
</div>
<button type="submit" id="J_submit" style="display:none">
</form>
</div>
</div><!-- /.row -->
<div class="hr hr32 hr-dotted"></div>
<!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
</div><!-- /.row -->
<div class="page-footer">
</div><!-- /.page-footer -->
</div><!-- /.page-content -->
</div><!-- /.main-content -->

</div><!-- /.main-container-inner -->

<a href="javascript:;" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="icon-double-angle-up icon-only bigger-110"></i>
</a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->

<script src="${request.contextPath}/resources/assets/js/jquery-2.0.3.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

<!--[if !IE]> -->


<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${request.contextPath}/resources/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->

<script type="text/javascript">
    if("ontouchend" in document) document.write("<script src='${request.contextPath}/resources/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
</script>
<script src="${request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<script src="${request.contextPath}/resources/assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="${request.contextPath}/resources/assets/js/excanvas.min.js"></script>
<![endif]-->

<script src="${request.contextPath}/resources/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${request.contextPath}/resources/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${request.contextPath}/resources/assets/js/jquery.slimscroll.min.js"></script>


<script src="${request.contextPath}/resources/assets/js/bootbox.min.js"></script>



<script type="text/javascript">

</script>

<!-- ace scripts -->

<script src="${request.contextPath}/resources/assets/js/ace-elements.min.js"></script>
<script src="${request.contextPath}/resources/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<link rel="stylesheet" href="${request.contextPath}/resources/assets/css/datepicker.css">
<script src="${request.contextPath}/resources/assets/js/date-time/bootstrap-datepicker.min.js"></script>

<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.validate.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.validate.method.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/message.js"></script>

<script src="${request.contextPath}/resources/admin/js/jquery.uploadify.min.js?v=${jslevel}" type="text/javascript"></script>

<style>
	    		.errorClass { color:red}
</style>
<script type="text/javascript">
$('#file_upload_0').change(function(){
    var file = this.files[0]; //选择上传的文件
    var r = new FileReader();
    r.readAsDataURL(file); //Base64
    $(r).load(function(){
        $('#first_img').attr('src', this.result);
    });
});

$(".J_upload_file").change(function(){
	var this_ = $(this);
	this_.next().next().show();
	this_.hide();
	var file = this.files[0]; //选择上传的文件
    var r = new FileReader();
    r.readAsDataURL(file); //Base64
    $(r).load(function(){
        this_.prev().attr('src', this.result);
        this_.prev().show();
    });
});

function toCheck(tourl) {
	var content= $('#content').val();
	if(content==""){
		alert('填写作品介绍');
		return;
	}
	
	var title = $("#title").val();
	var author = $("#author").val();
	var mobile = $("#mobile").val();
	if(title.length > 5){
		alert("标题长度不能超过五个字符");
		return;
	}
	if(author.length > 5){
		alert("作者长度不能超过五个字符");
		return;
	}
	if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile)) && mobile.length!=11){ 
        alert("手机号码格式不正确"); 
        return; 
    }
	if(!$("#saveForm").valid()){
		return;
	}
	$("#J_submit").click();	
}

$(function(){
	
	$("#saveForm").validate({
		/* 设置验证规则 */	 
		rules: {
			title: {
				required: true,
				maxlength:5 
			},
			author: {
				required: true
			}
			,mobile :{
				required: true,
				number:true
			}
			,weixin :{
				required: true
			}
			,preQuality :{
				required: true,
				number:true
			}
			
		},
		/* 错误信息的显示位置 */	 
		errorPlacement: function(error, element) {
			// 错误信息提示  有2层 是因为加class 会自动包了一层
			var errorPlace = element.parent().nextAll(".errorClass").first();
			errorPlace.html(error.html()).addClass("errorClass");
			errorPlace = element.nextAll(".errorClass").first();
			errorPlace.html(error.html()).addClass("errorClass");
		},	 
		/* 验证通过时的处理 */	 
		success: function(lable) {
			lable.remove();
		},
		onkeyup: false
	});
	})


function saveProduct(tourl){
	$.ajax({
		   type: "GET",
		   url: $('#saveForm').attr('data-url'),
		   data: $('#saveForm').serialize(),
		   success: function(data){
		     if(data.isSuccess){
		    	alert("保存成功");
		    	location.href=tourl;
		    	//
		     }else{
		    	alert("保存失败");
		     }
		   },
		   dataType: "json"
		});
}
</script>
</body>
</html>
</#escape>