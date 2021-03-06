<#escape x as x!"">
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title></title>
    <link href="${request.contextPath}/resources/pad/css/style.css" rel="stylesheet" type="text/css">
    <link href="${request.contextPath}/resources/pad/css/idangerous.swiper.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" charset="utf-8" src="${request.contextPath}/resources/pad/js/shining.js"></script>
    <script src="${request.contextPath}/resources/pad/js/jquery-2.1.3.min.js"></script>
    <script src="${request.contextPath}/resources/pad/js/idangerous.swiper.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#trans_btn > li').click(function(){
                var index = $(this).index();
                $('#trans_btn > li').removeClass('on').eq(index).addClass('on');
                $('#trans_details > div').hide().eq(index).show();
            })
        });
        $(document).ready(function(){
            loadDetail();
        });
    </script>
</head>
<body>
<div class="header">
    <h1>女神的项链</h1>
</div>
<div class="wrap">
    <div class="trans_wr">
    </div>
    <input type="hidden" id="openId" value="${openId}">
    <div id="trans_details">
        <div id="product" class="product" style="display: block;">
            <div class="device">
                <div class="swiper-container">
                    <div class="swiper-wrapper">
                    	<#if images??>
                    	<#list images as image>
                        <div class="swiper-slide"><img src="${request.contextPath}/resources/admin/${image}"></div>
                        </#list>
                        </#if>
                    </div>
                </div>
                <div class="pagination"></div>
            </div>
            <div class="title_wr">
                <div class="title">
                    <h3>${productInfo.title}(${productInfo.author})</h3>
                    <p>${productInfo.content}</p>
                </div>
                <div style="width: 50%;text-align: center;float: left;margin-top: 10px;">
                    <a onclick="toPraise(${productInfo.id},'${request.contextPath}/admin/product/toPraise');" class="title_btn"><span class="icon icon_aixin"></span>${productInfo.greatNum}&nbsp点赞</a>
                </div>
                <div style="width: 50%;text-align: center;float: left;margin-top: 10px;">
                    <a href="http://mp.weixin.qq.com/s?__biz=MzAxODUyMDM2NA==&mid=215417449&idx=1&sn=fed93a6817c24812e750fef9525e0cf4#rd" class="title_btn"><span class="icon icon_share"></span>星工坊</a>
                </div>
                <div class="clear"></div>
            </div>
            <div class="bg_line"></div>
            <div class="product_details">
                <h3>材料清单</h3>
                <table bordercolor="#cccccc" border="1" style="width: 100%;border-collapse: collapse;line-height: 23px;">
                <thead>
                <tr>
                <th width="25%">编号</th>
                <th width="25%">名称</th>
                <th width="25%">规格</th>
                <th width="25%">颜色</th>
                </tr>
                </thead>
                <tbody>
                	<#if materialInfos??>
                	<#list materialInfos as materialInfo>
                <tr>
                <th>${materialInfo.materialName}</th>
                <th>${materialInfo.more1}</th>
                <th>${materialInfo.materialSpecification}</th>
                <th>${materialInfo.more2}</th>
                </tr>
                	</#list>
                	</#if>
                </tbody>
                </table>
                <br>
            </div>
        </div>
        <div id="course" class="course" style="display: none;">
            <div class="course_01">
                <img src="${request.contextPath}/resources/pad/image/picture1.fw.png" width="100px">
                <h3>标题</h3>
                <p>足球比赛规则由国际足球协会理事会(IFAB)制定并修改，是足球比赛所必须遵守的规则场地面积场地必须是长方形，边线的长度必须长于球门线的长度。长度90-120米。</p>
            </div>
            <div class="bg_line"></div>
            <div class="course_02">
                <ul>
                	<#if materialInfos??>
                	<#list materialInfos as materialInfo>
                    <li>
                    	<span>${materialInfo.materialName}</span>
                        <!--<img src="${request.contextPath}/resources/pad/image/picture2.fw.png" width="100%">-->
                    </li>
                    </#list>
                    </#if>
                </ul>
                <div style="width: 100%;text-align: center;margin-bottom: 10px;">
                    <a href="#" class="course_02_btn">技法<span class="icon_round"></span></a>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var mySwiper = new Swiper('.swiper-container',{
        pagination: '.pagination',
        loop:true,
        grabCursor: true,
        paginationClickable: true,
        autoplay: 3000,
        speed: 1500,
        calculateHeight: true,
        onSwiperCreated: function(swiper){
            $(".swiper-pagination-switch:eq(0)").addClass('swiper-visible-switch').addClass('swiper-active-switch');
            swiper.reInit();
        }
    });


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
		    	//
		     }else{
		    	alert(data.message);
		    	//location.href=location.href;
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