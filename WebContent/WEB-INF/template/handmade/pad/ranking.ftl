<#escape x as x!"">
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title></title>
    <link href="${request.contextPath}/resources/pad/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" charset="utf-8" src="${request.contextPath}/resources/pad/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
	var SG = SG || {data:{},util:{}};
	SG.config = {path:{rootPath:"${request.contextPath}"}}
	var YS = YS || {data:{},util:{}};
	YS.config = {path:{rootPath:"${request.contextPath}"}}
	</script>
    <script type="text/javascript">
    
    
    </script>
</head>
<body>
<div class="header">
    <h1>初赛排名</h1>
</div>
<div class="wrap">
    <div class="show_pic">
    	<h3></h3>
        <img src="${request.contextPath}/resources/pad/排名页面图.jpg" width="100%">
    </div>
    <input type="hidden" id="openId" value="${openId}">
    <div class="show_ranking">
        <div class="title_wr">
        </div>
        <div class="ranking">
            <ul id="rankingul">
            	<#if products??>
            	<#list products as product>
                <li>
                	<div style="width:50%;float:left;">
                	<a href="${request.contextPath}/admin/product/toProductDetail?id=${product.id}">
                    <img src="${request.contextPath}/resources/admin/${product.more1}" width="100%">
                    </a>
                    </div>
                    <div style="width:50%;text-align:center;float:left;">
                    <p><span></span><span class="gray">${product.title}</span></p>
                    <p><span></span><span class="gray">${product.author}</span></p>
                    <p>
                    <!--<a onclick="toPraise(${product.id},'${request.contextPath}/admin/product/toPraise');">-->
                    <a href="${request.contextPath}/admin/product/toProductDetail?id=${product.id}">
                    <img src="${request.contextPath}/resources/pad/image/dianzan.fw.png" width="40px">
                    <!--<img src="image/dianzan.fw.png" width="40px">-->
                    </a>
                    <span class="orange">${product.greatNum}</span></p>
                    </div>
                </li>
                </#list>
                </#if>
            </ul>
            <div id="rankdiv">
            <!--<a onclick = "loadMore123('${request.contextPath}/admin/product/loadMore/p${page}');" class="submit_btn">加载更多</a>-->
            <div>
        </div>
    </div>
</div>
<script>
function loadMore123(url){
	$.ajax({
		   type: "GET",
		   url: url,
		   //data: {"id":id},
		   success: function(data){
		     if(data.isSuccess){
		     	var list =data.result;
		     	html1 = "<a onclick = \"loadMore123('${request.contextPath}/admin/product/loadMore/p"+list.page+"');\" class=\"submit_btn\">加载更多</a>";
		     	$("#rankingul").append(load_more_parse(list.products));
		     	$("#rankdiv").html(html1);
		     }else{
		    	alert(data.message);
		     }
		   },
		   dataType: "json"
		});
}

function load_more_parse(list) {
	var html = "";
	for ( var key in list) {
		var product = list[key];
		if(product !=null){
		html += "<li><div style=\"width:50%;float:left;\"><a href=\"${request.contextPath}/admin/product/toProductDetail?id="+product.id+"\">"
				+"<img src=\"${request.contextPath}/resources/admin/"+product.more1+"\" width=\"100%\"></a></div>"
				+"<div style=\"width:50%;text-align:center;float:left;\"><p><span></span><span class=\"gray\">"+product.title+"</span></p><p><span></span><span class=\"gray\">"+product.author+"</span></p>"
				+"<p><a onclick=\"toPraise("+product.id+",'${request.contextPath}/admin/product/toPraise');\">" 
				+"<img src=\"${request.contextPath}/resources/pad/image/dianzan.fw.png\" width=\"40px\"></a>"
				+"<span class=\"orange\">"+product.greatNum+"</span></p></div></li>";
		}
	}
	return html;
}

function toPraise(id,url){
	var openId = $("#openId").val();
	$.ajax({
		   type: "GET",
		   url: url,
		   data: {"id":id,"openId":openId},
		   success: function(data){
		     if(data.isSuccess){
		    	alert("点赞成功");
		    	//location.href=location.href;
		     }else{
		    	alert(data.message);
		    	location.href=location.href;
		     }
		   },
		   dataType: "json"
		});
}
</script>

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
 <script type="text/javascript">
  wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: 'wx156ab8abe9339584', // 必填，公众号的唯一标识
		    timestamp: '${(map.timestamp)!''}', // 必填，生成签名的时间戳
		    nonceStr: '${(map.nonceStr)!''}', // 必填，生成签名的随机串
		    signature: '${(map.signature)!''}',// 必填，签名，见附录1
		    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		
		wx.ready(function(){
			wx.onMenuShareTimeline({
			    title: '女神的项链', // 分享标题
			    link: 'http://nvshen.hzlianhai.com/admin/product/rankOfProduct/p1', // 分享链接
			    imgUrl: 'http://nvshen.hzlianhai.com/resources/pad/%E6%8E%92%E5%90%8D%E9%A1%B5%E9%9D%A2%E5%9B%BE.jpg', // 分享图标
			    success: function () { 
			    },
			    cancel: function () { 
			        alert("分享取消");
			    }
			});
			
			ShareAppMessage();
		});
		
		function ShareAppMessage(){
			wx.onMenuShareAppMessage({
			    title: '女神的项链', // 分享标题
			    desc: '女神的项链', // 分享描述
			    link: 'http://nvshen.hzlianhai.com/admin/product/rankOfProduct/p1', // 分享链接
			    imgUrl: 'http://nvshen.hzlianhai.com/resources/pad/%E6%8E%92%E5%90%8D%E9%A1%B5%E9%9D%A2%E5%9B%BE.jpg', // 分享图标
			    success: function () {
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    }
			});
		}
		</script>
</body>
</html>
</#escape>