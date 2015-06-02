<#escape x as x!"">
<#include "../common/header.ftl">
            <div class="breadcrumbs" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="#">材料管理</a>
                    </li>
                </ul><!-- .breadcrumb -->
            </div>

            <div class="page-content">
                <div class="page-header">
                    <form class="form-inline">
                        <div class="form-group" style="margin-bottom: 10px;">
                            <label>材料名称：</label>
                            <input class="input-small" type="text" value="">
                            <label>规格：</label>
                            <input class="input-small" type="text" value="">
                            <label>单位质量：</label>
                            <input class="input-small" type="text" value="">
                            <label>单位价格：</label>
                            <input class="input-small" type="text" value="">
                            <label>成本单价：</label>
                            <input class="input-small" type="text" value="">
                        </div>
                        <br>
                        <div class="form-group" style="margin-bottom: 10px;">
                            <button class="btn btn-sm btn-primary">
                                <i class="icon-search align-top"></i>查询
                            </button>

                            <button class="btn btn-sm btn-primary">
                                <i class="icon-search align-top"></i>批量导入
                            </button>

                            <button class="btn btn-sm btn-primary">
                                <i class="icon-search align-top"></i>单个添加
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
                                                    <th>图片</th>
                                                    <th>材料名称</th>
                                                    <th>规格</th>
                                                    <th>单位</th>
                                                    <th>单位价格</th>
                                                    <th>单位质量</th>
                                                    <th>成本单价</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>

                                                <tbody>
                                                <#if materialInfos??>
                                                <#list materialInfos as materialInfo>
                                                <tr>
                                                <th class="center">
                                                    <label>
                                                        <input type="checkbox" class="ace J_SelectAll">
                                                        <span class="lbl"></span>
                                                    </label>
                                                </th>
                                                <th>
                                                    <img src="${request.contextPath}/resources/admin/store/<#if materialInfo??>${materialInfo.image}</#if>" width="60px" height="60px">
                                                </th>
                                                <th>${materialInfo.materialName}</th>
                                                <th>${materialInfo.materialSpecification}</th>
                                                <th>${materialInfo.unit}</th>
                                                <th>${materialInfo.unitPrice}</th>
                                                <th>${materialInfo.unitQuality}</th>
                                                <th>${materialInfo.costPrice}</th>
                                                <th>
                                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                        <button class="btn btn-xs btn-success">
                                                            <i class="icon-stop bigger-120"> 编辑 </i>
                                                        </button>

                                                        <button class="btn btn-xs btn-danger" onclick="delMaterial(${materialInfo.id},'${request.contextPath}/admin/material/deleteMaterial')">
                                                            <i class="icon-stop bigger-120"> 删除 </i>
                                                        </button>
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
</#escape>