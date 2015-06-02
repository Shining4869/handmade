<#escape x as x!"">
<#include "../common/header.ftl">
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">用户列表</a>
        </li>
    </ul><!-- .breadcrumb -->
</div>
<div class="page-content">
<div class="page-header">
    <form class="form-inline" action="${request.contextPath}/admin/userInfo/list/p1">
        <div class="form-group" style="margin-bottom: 10px;">
            <label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
            <input class="input-large" name="contestantName" type="text"  value="${(searchBean.contestantName)!''}">
            <label>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
            <input class="input-large" name="mobile" type="text" value="${(searchBean.mobile)!''}"><br><br>
            <label>初次创作时间：</label>
            <input type="text"  name="firstTime" value="<#if (searchBean.firstTime)??>${searchBean.firstTime?string('yyyy-MM-dd')}</#if>" 
			class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
            <label>二次创作时间：</label>
            <input type="text"  name="secondTime" value="<#if (searchBean.secondTime)??>${searchBean.secondTime?string('yyyy-MM-dd')}</#if>" 
			class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
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
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>初次创作时间</th>
                                    <th>二次创作时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if userInfos?has_content>
                                <#list userInfos as userinfo>
                                <tr>
                                <form id="saveForm${userinfo.id}" action="${request.contextPath}/admin/userInfo/list/p1" data-url="${request.contextPath}/admin/userInfo/saveOrUpdateUser">
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace J_SelectAll">
                                        <input type="hidden" name="id" value="${userinfo.id}">
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>${userinfo.contestantName}</th>
                                <th>${userinfo.mobile}</th>
                                <th>
                                <input type="text"  name="FTime" value="<#if (userinfo.firstTime)??>${userinfo.firstTime?string('yyyy-MM-dd')}</#if>" 
								class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
								<select name ="firAp" >
								<option <#if userinfo.firstAp??&&userinfo.firstAp=="AM">selected</#if> value="AM">上午</option>
								<option <#if userinfo.firstAp??&&userinfo.firstAp=="PM">selected</#if> value="PM">下午</option>
								</select>
								</th>
                                <th>
                                <input type="text"  name="STime" value="<#if (userinfo.secondTime)??>${userinfo.secondTime?string('yyyy-MM-dd')}</#if>" 
								class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
								<select name ="secAp" >
								<option <#if userinfo.secondAp??&&userinfo.secondAp=="AM">selected</#if> value="AM">上午</option>
								<option <#if userinfo.secondAp??&&userinfo.secondAp=="PM">selected</#if> value="PM">下午</option>
								</select>
                                </th>
                                <th>
                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                        <a class="btn btn-xs btn-success" onclick="saveTime(${userinfo.id});">
                                            <i class="icon-stop bigger-120"> 保存 </i>
                                        </a>
                                    </div>
                                </th>
                                </form>
                                </#list>
                                </#if>
                                </tr>
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