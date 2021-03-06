<#escape x as x!"">
<#include "../common/header.ftl">
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">作品分类管理</a>
        </li>
    </ul><!-- .breadcrumb -->
</div>
<div class="page-content">
    <div class="page-header">
        <form class="form-inline" id="saveForm" action="${request.contextPath}/admin/procate/list/">
            <div class="form-group">
                <input class="input-large" type="text" id="cateName" name="cateName" value="">
                <a class="btn btn-sm btn-primary" onclick="addType('${request.contextPath}/admin/procate/save','${request.contextPath}/admin/procate/list/p1')">
                    <i class="icon-search align-top"></i>添加分类
                </a>
            </div>
        </form>
    </div><!-- /.page-header -->
    <div class="row">
        <div class="col-xs-6">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="tabbable ">
                    <div class="tab-content">
                        <div class="table-responsive">
                            <div class="dataTables_wrapper">
                                <table id="J_Table" class="table table-striped table-bordered table-hover J_Table">
                                    <thead>
                                    <tr>
                                        <th>分类</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#if infos?has_content>
                                    <#list infos as info>
                                    <tr>
                                    <th>${info.cateName}</th>
                                    <th>
                                        <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                            <a class="btn btn-xs btn-danger J_DeleteTr" data-value="${request.contextPath}/admin/procate/delete/${info.id}">
                                                <i class="icon-stop bigger-120"> 删除 </i>
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
												<#include "../common/pages.ftl">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
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
<#include "../common/footer.ftl">
<script type="text/javascript">

function addType(url,tourl){
	var cateName =$('#cateName').val();
	$.ajax({
		   type: "GET",
		   url: url,
		   data: {"cateName":cateName},
		   success: function(data){
		     if(data.isSuccess){
		    	alert("添加成功");
		    	location.href=tourl;
		     }else{
		    	alert("添加失败");
		     }
		   },
		   dataType: "json"
		});
}
</script>
</#escape>