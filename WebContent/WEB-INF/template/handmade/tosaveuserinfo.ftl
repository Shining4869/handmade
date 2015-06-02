<#escape x as x!"">
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title></title>
    <link href="${request.contextPath}/resources/handmade/css/style.css" rel="stylesheet" type="text/css">
    
    <script src="${request.contextPath}/resources/assets/js/jquery-2.0.3.min.js"></script>
    
    	
	<script language="javascript" type="text/javascript" src="${request.contextPath}/resources/admin/js/datepicker/WdatePicker.js"></script>
	
	<script src="${request.contextPath}/resources/assets/js/shining/shining.js"></script>
    
</head>
<body>
<div class="header">
    <a class="leftButton" href="#"><img src="${request.contextPath}/resources/handmade/image/backBtn.png"></a>
    <h1>我要报名</h1>
    <a class="rightButton" href="#"><img src="${request.contextPath}/resources/handmade/image/point.png"></a>
</div>
<div class="wrap">
    <form id="saveForm" data-url="${request.contextPath}/userInfo/saveUserInfo" >
        <div class="input_group">
            <label>姓名</label><input name="contestantName" type="text">
            <label>手机号</label><input name="mobile" type="text">
            <label>预约初次创作时间</label>
            <input type="text"  name="firstTime" class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
            <select name ="firstAp" ><option value="上午">上午</option><option value="下午">下午</option></select>
            <label>预约二次创作时间</label>
            <input type="text"  name="secondTime" class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
            <select name="secondAp" ><option value="上午">上午</option><option value="下午">下午</option></select>
        </div>
    </form>
    <div class="btn">
        <a class="submitBtn" onclick="saveUserInfo('${request.contextPath}/userInfo/toGameIntroduce');">提交报名</a>
        <a class="submitBtn" href="${request.contextPath}/userInfo/toGameIntroduce">取消</a>
    </div>
</div>
</body>
</html>
</#escape>