<#escape x as x!"">
<#import "../common/common.ftl" as yep>
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
    <script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
    <script type="text/javascript">
	var SG = SG || {data:{},util:{}};
	SG.config = {path:{rootPath:"${request.contextPath}"}}
	var YS = YS || {data:{},util:{}};
	YS.config = {path:{rootPath:"${request.contextPath}"}}
	</script>
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
                    <a  href="${request.contextPath}/admin/logout" >
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
        <li class="<#if request.requestUri?index_of('/userInfo/list/p') != -1 > active</#if>">
            <a href="${request.contextPath}/admin/userInfo/list/p1.html">
                <i class="icon-text-width"></i>
                <span class="menu-text"> 报名用户 </span>
            </a>
        </li>
        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-dashboard"></i>
                <span class="menu-text"> 作品管理 </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu" <#if (request.requestUri?index_of('admin/productInfo/list') gt -1) 
            || (request.requestUri?index_of('admin/procate/list') gt -1) > 
             style="display: block;" </#if>>
                <li class="<#if request.requestUri?index_of('/admin/productInfo/list') != -1 > active</#if>">
                    <a href="${request.contextPath}/admin/productInfo/list/p1">
                        <i class="icon-double-angle-right"></i>
                        作品管理
                    </a>
                </li>
                <li class="<#if request.requestUri?index_of('/admin/procate/list') != -1 > active</#if>">
                    <a href="${request.contextPath}/admin/procate/list/p1">
                        <i class="icon-double-angle-right"></i>
                        作品管理分类
                    </a>
                </li>
            </ul>
        </li>
        <li class="<#if request.requestUri?index_of('admin/material/list') != -1 > active</#if>">
            <a href="${request.contextPath}/admin/material/list/p1">
                <i class="icon-desktop"></i>
                <span class="menu-text"> 材料管理 </span>
            </a>
        </li>
    </ul><!-- /.nav-list -->
    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>
</div>
<div class="main-content">
</#escape>