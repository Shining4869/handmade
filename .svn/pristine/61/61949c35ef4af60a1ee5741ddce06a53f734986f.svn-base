<#escape x as x!"">
<#include "../common/header.ftl">
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">选择材料</a>
        </li>
    </ul><!-- .breadcrumb -->
</div>
<div class="page-content">
<div class="page-header">
    <form class="form-inline" id="searchForm" data-url="${request.contextPath}/admin/material/toSearch/p1">
        <div class="form-group" style="margin-bottom: 10px;">
            <label>材料编号：</label>
            <input class="input-large" name="materialName" id="materialName" type="text"  value="${(searchBean.materialName)!''}">
            <a class="btn btn-sm btn-primary" onclick="toSearch();">
                <i class="icon-search align-top"></i>查询
            </a>
        </div>
    </form>
</div><!-- /.page-header -->
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
            <div class="tabbable ">
                <div class="tab-content">
                    <div class="table-responsive">
                        <div class="dataTables_wrapper">
                            <table id="J_Table" class="table table-striped table-bordered table-hover J_Table">
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
                                    <th>材料单位质量</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody class="J_tbody1" id="tbody">
                                <#if materials?has_content>
                                <#list materials as material>
                                <tr class="J_tr${material.id}">
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace J_SelectAll">
                                        <input type="hidden" name="id" value="${material.id}">
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>${material.materialName}</th>
                                <th>${material.more1}</th>
                                <th>${material.materialSpecification}</th>
                                <th>${material.more2}</th>
                                <th>${material.unit}</th>
                                <th>${material.unitQuality}</th>
                                <th>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                        <a class="btn btn-xs btn-success J_toaddmaterial">
                                            <i class="icon-stop bigger-120"> 选择 </i>
                                        </a>
                                    </div>
                                </th>
                                </tr>
                                </#list>
                                </#if>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-xs-6">
                                </div>
                                <!--<div class="col-xs-6">
                                    <div class="dataTables_paginate paging_bootstrap">
                                        <ul class="pagination" id="pageul">
                                        <#assign pageUrl>
										<@yep.searchPageUrl request.requestUri 'pageNo=#' request.queryString/>
										</#assign>
										<#include "../common/pages.ftl">
                                        </ul>
                                    </div>
                                </div>-->
                            </div><br>
                            <form id="choseForm" action="${request.contextPath}/admin/product/toAddProduct">
                            <table id="secondtable" class="table table-striped table-bordered table-hover J_Table">
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
                                    <th>材料单位质量</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            </table><br>
                            <div class="form-group" style="margin-bottom: 10px;">
					            
					            <span>管理员密码：</span>
					            <input type="password" name="more1" id="more1" value="" style="width:150px">
					            <span>条码重量：</span>
					            <input type="text" name="codeQuality" value="" style="width:150px">
					            <a  class="btn btn-sm btn-primary" onclick="saveChose('${request.contextPath}/admin/findByPassword')">
					                <i class="icon-search align-top"></i>下一步
					            </a>
					        </div>
                            <form>
                        </div>
                    </div><!-- /.table-responsive -->
                </div>
            </div>
        </div><!-- /.row -->
        <div class="hr hr32 hr-dotted"></div>
        <!-- PAGE CONTENT ENDS -->
    </div><!-- /.col -->
</div><!-- /.row -->
<#include "../common/footer.ftl">
<script type="text/javascript">

function saveChose(checkurl){
	var password=$('#more1').val();
	var flag= 0;
	if(password==""){
		alert('请输入密码');
		return;
	}
	$.ajax({
		   type: "GET",
		   url: checkurl,
		   data: {"more1":password},
		   success: function(data){
		     if(data.isSuccess){
		     	$('#choseForm').submit();
		     }else{
		    	alert("密码不正确");
		     }
		   },
		   dataType: "json"
		});
}

$(".J_tbody1").on("click", '.J_toaddmaterial', function() {
	var this_ = $(this).parent().parent().parent();
	this_.find("i").html("删除");
	$("#secondtable").append(this_);
});

$("#secondtable").on("click", '.J_toaddmaterial', function(){
	var this_ = $(this);
	this_.parent().parent().parent().remove();
});
	


function toSearch(){
	$.ajax({
		   type: "GET",
		   url: $('#searchForm').attr('data-url'),
		   data: $('#searchForm').serialize(),
		   success: function(data){
		     if(data.isSuccess){
		    	 var map=data.result;
		    	 $("#tbody").html(load_tbody_parse(map.materials));
//		    	 $("#pageul").html(load_pageul_parse(map.page));
//		    	 $("#materialName").html(load_materialName_parse(map.searchBean));
		     }else{
		    	alert("查询失败");
		     }
		   },
		   dataType: "json"
		});
}

function load_tbody_parse(list) {
	var html = "";
	for ( var key in list) {
		var material = list[key];
		if(material !=null){
		html += ""
				+"<tr class=\"J_tr"+material.id+"\">"
				+"<th class=\"center\"><label><input type=\"checkbox\" class=\"ace J_SelectAll\">"
				+"<input type=\"hidden\" name=\"id\" value=\""+material.id+"\">"
				+"<span class=\"lbl\"></span></label></th>"
				+"<th>"+material.materialName+"</th>"
				+"<th>"+material.more1+"</th>"
				+"<th>"+material.materialSpecification+"</th>"
				+"<th>"+material.more2+"</th>"
				+"<th>"+material.unit+"</th>"
				+"<th>"+material.unitQuality+"</th>"
				+"<th><div class=\"visible-md visible-lg hidden-sm hidden-xs btn-group\">"
				+"<a class=\"btn btn-xs btn-success J_toaddmaterial\">"
				+"<i class=\"icon-stop bigger-120\"> 选择 </i>"
				+"</a></div></th></tr>";
//				+""
//				+""
//				+""
//				+""
//				+""
//				+""
//				+""
//				+""
//				+""
//				+""
//				+""
//				+""
//				+""
//				+""
//				+""
		}
	}
	return html;
}
</script>
</#escape>   