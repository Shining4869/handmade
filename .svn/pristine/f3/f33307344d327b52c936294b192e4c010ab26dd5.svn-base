window.onload=function(){
	//alert("加载完毕")
	document.getElementById('loader').style.display = 'none';
} 
function timer(){
	var time = setInterval(function(){
		var now = parseInt($('#time').text());
		now --;
		if(now<0) {
			clearInterval(time);
			gameEnd();
			return false;
		}
		$('#time').text(now);
	},1000);
}
$(function(){
	$('#mask').click(function(){
		$('#mask').hide();
	});
	$('#game').on('touchmove', function (event) {
	    event.preventDefault();
	});
	$('#meinv')[0].addEventListener('touchstart',ts,false);
	$('#meinv')[0].addEventListener('touchmove',tm,false);
	$('#meinv')[0].addEventListener('touchend',te,false);
});
var gs = false;
function gamestart(){
	gs = true;
	timer();
}
function gamereset(){
	$('#meinv')[0].addEventListener('touchstart',ts,false);
	$('#meinv')[0].addEventListener('touchmove',tm,false);
	$('#meinv')[0].addEventListener('touchend',te,false);
	gs = false;
	score = 0;
	$('#time').text('10');
	$('#score_text').text('0');
}
function gameEnd(){
//	alert('游戏结束');
	console.log("游戏结束");
	var mobile = $("#mobile").val();
	saveinfo2();
	if(!mobile &&  score >= 30){
		$("#zjtzdiv").show();
	}
	
	$('#kll').text(score+"00");
	$('#meinv')[0].removeEventListener('touchstart',ts,false);
	$('#meinv')[0].removeEventListener('touchmove',tm,false);
	$('#meinv')[0].removeEventListener('touchend',te,false);
	to('end');
}



function saveinfo(){
	//保存用户信息
//	var score = $('#score_all').text();
	var id = $("#id").val();
//	var gobi = $('#gobi').text();
	var mobile;
	mobile = $("#tel").val();
	if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile)) && mobile.length!=11){ 
        alert("手机号码格式不正确"); 
        return; 
    } 
	
	
	$.ajax({
		type : "GET",
//		url : SG.config.path.rootPath+'/fatGame/save/',
		url : SG.config.path.rootPath+'/fatGame/addMobile/',
		data : {
			mobile : mobile,
			id : id,
//			gobi : gobi
		},
		dataType : "json",
		success : function(data) {
			//$("#zjtzdiv").hide();
			if(data.isSuccess) {
				
			}else {
				alert(data.message);
			}
		},
		error : function() {
			alert("网络错误，请重试！");
		}
		
	});
	$("#zjtzdiv").hide();
	
//	$("#mobile").val(mobile);
//	saveinfo2();
}


function  saveinfo2(){
	var mobile = $("#mobile").val();
	var id = $("#id").val();
	$.ajax({
		type : "GET",
//		url : SG.config.path.rootPath+'/fatGame/save/',
		url : SG.config.path.rootPath+'/fatGame/saveLog/',
		data : {
			value : score,
			mobile : mobile,
			userId : id,
//			gobi : gobi
		},
		dataType : "json",
		success : function(data) {
			//$("#zjtzdiv").hide();
			if(data.isSuccess) {
				var dayNum =$("#dayNum").val()-1;
				if(dayNum <=0) {
					$("#palyAgain").hide();
				}
				$("#dayNum").val(dayNum);
				$("#dayNumShow").html(dayNum);
			}else {
				alert(data.message);
			}
		},
		error : function() {
			alert("网络错误，请重试！");
		}
	});
	
}


// var fi = 0;
var w = window.innerWidth;
var xx;
var score = 0;
function ts(){
	if(!gs){
		gamestart();
	}
	var meinv = $('#meinv');
	meinv.addClass('up');
	setTimeout(function(){
		meinv.removeClass('up');
	},100);
	// console.log('s');
	var x = parseInt(event.touches[0].clientX*0.8+20);
	xx = x;
	var y = parseInt(event.touches[0].clientY);
	var rand = parseInt(Math.random()*6+1);//123456

	var game = $('#game');
	// fi ++;
	var html = "<div id='fd' class=\"food food"+rand+"\" style='left:"+x+"px;top:"+y+"px;'></div>";
	
	game.append(html);

	// var ff = fi-1;
	var foodo = $('.food');
	setTimeout(function(){
		foodo.remove();
	},600);
}
function tm(event){
	var x = event.touches[0].clientX;
	if(x>w-70) x = w-70;
	// console.log('m');
	// var ff = fi-1;
	var foodo = $('.food')[0];
	foodo.style.left = parseInt(x*0.8+20)+"px";
	foodo.style.top = parseInt(event.touches[0].clientY)+"px";
}
function te(event){
	score ++;
	$('#score_text').text(score);
	var meinv = $('#meinv');
	if(score>60){
		meinv.addClass('meinv9');
	}
	else if(score>50){
		meinv.addClass('meinv8');
	}
	else if(score>45){
		meinv.addClass('meinv7');
	}
	else if(score>40){
		meinv.addClass('meinv6');
	}
	else if(score>35){
		meinv.addClass('meinv5');
	}
	else if(score>30){
		meinv.addClass('meinv4');
	}
	else if(score>20){
		meinv.addClass('meinv3');
	}
	else if(score>10){
		meinv.addClass('meinv2');
	}
	// console.log('e');
	var x = parseInt(event.changedTouches[0].clientX*0.8+20);//+startX;
	var y = parseInt(event.changedTouches[0].clientY);//+startY;
	if(Math.abs(x-xx)<30) return;
	// var ff = fi-1;
	var foodo = $('.food');
	foodo[0].style.opacity = 0;
	var left = parseInt(foodo.css('left'))+70;
	var top = parseInt(foodo.css('top'))+50;
	// console.log(foodo.css('top'));
	// console.log(top);
	foodo.css('left',left+'px');
	foodo.css('top',top+'px');

}
function to(id){
	$('body>div').hide();
	$('#'+id).show();
}
function mask(){
	$('#mask').show();
}









