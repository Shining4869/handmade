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
    <form class="form-inline" action="${request.contextPath}/admin/material/list/p1/">
        <div class="form-group" style="margin-bottom: 10px;">
            <label>材料编号：</label>
            <input class="input-large" name="materialName" type="text"  value="${(searchBean.materialName)!''}">
            <button class="btn btn-sm btn-primary">
                <i class="icon-search align-top"></i>查询
            </button>
            <a class="btn btn-sm btn-primary" href="${request.contextPath}/admin/material/add/">
                <i class="icon-search align-top"></i>添加
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
                                <tbody>
                                <#if infos?has_content>
                                <#list infos as material>
                                <tr id="tr${material.id}">
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
                                        <a class="btn btn-xs btn-success J_DeleteTr" data-value="${request.contextPath}/admin/material/delete/${material.id}">
                                            <i class="icon-stop bigger-120"> 删除 </i>
                                        </a>
                                    </div>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                        <a class="btn btn-xs btn-success" href="${request.contextPath}/admin/material/input/${material.id}">
                                            <i class="icon-stop bigger-120"> 编辑 </i>
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
<#include "../common/footer.ftl">
</#escape>   