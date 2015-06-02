<#escape x as x!"">
<#import "common/common.ftl" as yep>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>控制台</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- basic styles -->
    <link href="${request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${request.contextPath}/resources/assets/css/font-awesome.min.css" />
    <!-- page specific plugin styles -->

    <!-- ace styles -->
    <link rel="stylesheet" href="${request.contextPath}/resources/assets/css/ace.min.css" />
    <link rel="stylesheet" href="${request.contextPath}/resources/assets/css/qfc-min.css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${request.contextPath}/resources/assets/css/ace-ie.min.css" />
    <![endif]-->
    <script src="${request.contextPath}/resources/assets/js/jquery-2.0.3.min.js"></script>
    
    	
	<script language="javascript" type="text/javascript" src="${request.contextPath}/resources/admin/js/datepicker/WdatePicker.js"></script>
	
	<script src="${request.contextPath}/resources/assets/js/shining/shining.js"></script>

    <!-- ace settings handler -->
    <script src="${request.contextPath}/resources/assets/js/ace-extra.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${request.contextPath}/resources/assets/js/html5shiv.js"></script>
    <script src="${request.contextPath}/resources/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="navbar navbar-default" id="navbar">
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    后台管理系统
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a  href="#" >
                <span class="user-info">
                 退出
                </span>
                    </a>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>

<div class="main-container" id="main-container">

<div class="main-container-inner">
<a class="menu-toggler" id="menu-toggler" href="#">
    <span class="menu-text"></span>
</a>

<div class="sidebar" id="sidebar">
    <ul class="nav nav-list">
        <li class="active">
            <a href="报名用户.html">
                <i class="icon-text-width"></i>
                <span class="menu-text"> 材料选择 </span>
            </a>
        </li>
    </ul><!-- /.nav-list -->
    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>
</div>

<div class="main-content">
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
    <form class="form-inline" action="${request.contextPath}/material/toChoseMaterial/p1">
        <div class="form-group" style="margin-bottom: 10px;">
            <label>材料编号：</label>
            <input class="input-large" name="materialName" type="text"  value="${(searchBean.materialName)!''}">
            <button class="btn btn-sm btn-primary">
                <i class="icon-search align-top"></i>查询
            </button>
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
                                    <th>材料规格</th>
                                    <th>材料类别</th>
                                    <th>操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <#if materials?has_content>
                                <#list materials as material>
                                <tr id="tr${material.id}">
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace J_SelectAll">
                                        <input type="hidden" name="id" value="${material.id}">
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>${material.materialName}</th>
                                <th>${material.materialSpecification}</th>
                                <th>${material.type}</th>
                                <th>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                        <a class="btn btn-xs btn-success" onclick="toaddmaterial(${material.id});">
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
                                <div class="col-xs-6">
                                            <div class="dataTables_paginate paging_bootstrap">
                                                <ul class="pagination">
                                                <#assign pageUrl>
												<@yep.searchPageUrl request.requestUri 'pageNo=#' request.queryString/>
												</#assign>
												<#include "common/pages.ftl">
                                                </ul>
                                            </div>
                                </div>
                            </div><br>
                            <form id="choseForm" action="${request.contextPath}/product/toAddProduct">
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
                                    <th>材料规格</th>
                                    <th>材料类别</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            </table><br>
                            <div class="form-group" style="margin-bottom: 10px;">
					            <button class="btn btn-sm btn-primary" >
					                <i class="icon-search align-top"></i>下一步
					            </button>
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


<script type="text/javascript">
function saveChose(url){
	$.ajax({
		   type: "GET",
		   url: $('#choseForm').attr('data-url'),
		   data: $('#choseForm').serialize(),
		   success: function(data){
		     if(data.isSuccess){
		    	alert("保存成功");
		    	location.href=url;
		     }else{
		    	alert("保存失败");
		     }
		   },
		   dataType: "json"
		});
}

	function toaddmaterial(id){
		var html1= $("#tr"+id).html();
		$("#secondtable").append("<tr>"+html1+"</tr>");
	}

    (function($,undefined){
        //操作：全选
        $(".J_SelectAll").on("change", function(){
            if ($(this).prop("checked")) {
                $(".J_SelectSub").prop("checked", true);
            } else {
                $(".J_SelectSub").prop("checked", false);
            }
        });
        //操作：删除
        $(".J_Table").on("click", ".J_DeleteTr", function() {
            var _this = $(this);
            bootbox.confirm("确认删除", function(result) {
                if(result) {
                    _this.parent().parent().parent().remove();
                }
            });
        })
    })(jQuery);
</script>
</body>
</html>
</#escape>   