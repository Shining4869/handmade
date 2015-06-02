
/**
 * 设置 属性 失去焦点时间
 */
var  blurEvent = false;
/**
 * 提交 是否 验证 全部属性 
 */
var  validateAll = true;
/**
 * 调试 信息打印
 * @param str
 */
function  printLog(str){
	//console.log(str);
}
function printFailLog(str){
	console.info(str);
}

function  printLog2(str){
	console.log(str);
}


///^\d{4}-\d{2}-\d{2}$/.test('2014-12-14')
var time_regex = /^\d{4}-\d{2}-\d{2}$/;
var  double_regex =  /^[-\+]?\d+(\.\d+)?$/ ;
var NOt_null_regex = /^.+$/;
var email_regex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;


var strRegex = '^((https|http|ftp|rtsp|mms)?://)'
	+ '?(([0-9a-z_!~*\'().&=+$%-]+: )?[0-9a-z_!~*\'().&=+$%-]+@)?' //ftp的user@
	+ '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184
	+ '|' // 允许IP和DOMAIN（域名）
	+ '([0-9a-z_!~*\'()-]+.)*' // 域名- www.
	+ '([0-9a-z][0-9a-z-]{0,61})?[0-9a-z].' // 二级域名
	+ '[a-z]{2,6})' // first level or .museum
	+ '(:[0-9]{1,4})?' // 端口- :80
	+ '((/?)|' // a slash isn't required if there is no file name
	+ '(/[0-9a-z_!~*\'().;?:@&=+$,%#-]+)+/?)$'; 
//strRegex= "^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$";

var URL_Regexp = new RegExp(strRegex);

/**
 * 每个要验证 属性 input 的 filed 对象
 * 
 * @param params
 * @returns
 */
function Field(params) {
	// 要验证的字段的ID
	this.field_id = params.fid;
	// 验证器对象数组
	this.validators = params.val;
	// 当验证成功的时候执行的事件
	this.on_suc = params.suc;
	// 当验证失败的时候执行的事件
	this.on_error = params.err;
	// 是否通过验证
	this.checked = false;
};
/**
 * 验证全部的 规则
 */
Field.prototype.validate = function() {
	// 循环每一个验证器
	printLog("验证规则数量："+this.validators.length);
	for (var item in this.validators) {
		// 给验证器附加验证成功和验证失败的回调事件
		this.set_callback(this.validators[item]);
		// 执行验证器上的Validate方法，验证是否符合规则
		if (!this.validators[item].validate(this.data())) {
			printFailLog("验证失败 file："+this.field_id);
			break;
			// 一旦任意一个验证器失败就停止
		}
	}
	printLog("验证OK==〉："+this.field_id);
};
// 获取字段值的方法
Field.prototype.data = function() {
	try{
		var val = document.getElementById(this.field_id).value;
		printLog2("id:"+ this.field_id+",value:"+val);
		return val;
	}catch(e){
	}
};
/**
 * 设置验证器回调函数的方法 加入了checked 判断属性 验证是否通过
 */
Field.prototype.set_callback = function(val) {
	var self = this;
	
	// 换一个名字来存储this，不然函数的闭包中会覆盖这个名字
	val.on_suc = function() {
		// 验证成功执行的方法
		self.checked = true;
		// 将字段设置为验证成功
		self.on_suc(val.tips);
		// 执行验证成功的事件
	};
	val.on_error = function() {
		// 验证失败的时候执行的方法
		self.checked = false;
		// 字段设置为验证失败
		self.on_error(val.tips);
		// 执行验证失败的事件
	};
};
// 长度验证的验证器类
function Len_val(min_l, max_l, tip) {
	this.min_v = min_l;
	this.max_v = max_l;
	this.tips = tip;
	this.on_suc = null;
	this.on_error = null;
}
Len_val.prototype.validate = function(fd) {
	if (fd.length < this.min_v || fd.length > this.max_v) {
		this.on_error();
		return false;
	}
	this.on_suc();
	return true;
};
// 正则表达式验证器
function Exp_val(expresion, tip) {
	this.exps = expresion;
	this.tips = tip;
	this.on_suc = null;
	this.on_error = null;
}
Exp_val.prototype.validate = function(fd) {
	if (!fd) {
//		this.on_suc();
//		return true;
		this.on_error();
		return false;
	}
	var flag = this.exps.test(fd);
	if (this.exps.test(fd)) {
		this.on_suc();
		this.exps.lastIndex = 0;
		return true;
	} else {
		this.on_error();
		return false;
	}
};


//正则表达式验证器
function Exp_val2(expresion, tip) {
	this.exps = expresion;
	this.tips = tip;
	this.on_suc = null;
	this.on_error = null;
}
Exp_val2.prototype.validate = function(fd) {
	if (!fd) {
		this.on_suc();
		return true;
//		this.on_error();
//		return false;
	}
	var flag = this.exps.test(fd);
//	console.log(this.exps.lastIndex);
	if (this.exps.test(fd)) {
		this.on_suc();
		this.exps.lastIndex = 0;
		return true;
	} else {
		this.on_error();
		return false;
	}
};
// 远程验证器 ajax
function Remote_val(url, tip) {
	this.p_url = url;
	this.tips = tip;
	this.on_suc = null;
	this.on_error = null;
}
Remote_val.prototype.validate = function(fd) {
	var self = this;
	$.post(this.p_url, {
		f : fd
	}, function(data) {
		if (data.rs) {
			self.on_suc();
			return;
		} else {
			self.on_error();
		}
	}, 'json');
	return false;
};
// 自定义函数验证器
function Man_val(tip, func) {
	this.tips = tip;
	this.val_func = func;
	this.on_suc = null;
	this.on_error = null;
}
Man_val.prototype.validate = function(fd) {
	if (this.val_func(fd)) {
		this.on_suc();
	} else {
		this.on_error();
	}
};
function UserForm(items) {
	this.f_item = items;
	// 把字段验证对象数组复制给属性
	for (var idx = 0; idx < this.f_item.length; idx++) {
		// 循环数组
		var fc = this.get_check(this.f_item[idx]);
		// 获取封装后的回调事件
		if(blurEvent){
			$('#' + this.f_item[idx].field_id).blur(fc);
		}
		// 绑定到控件上
	}
}
// 绑定验证事件的处理器，为了避开循环对闭包的影响

UserForm.prototype.get_check = function(v) {
	return function() {
		// 返回包装了调用validate方法的事件
		v.validate();
	};
};
UserForm.prototype.set_submit = function(bid, bind) {
	var self = this;
	$('#' + bid).click(function() {
		if (self.validate()) {
			bind();
		}
	});
};
// 验证所有的字段
UserForm.prototype.validate = function() {
	var flag = true;
	for (var idx in this.f_item) {
		printLog("开始验证属性："+this.f_item[idx].field_id);
		// 循环每一个验证器
		this.f_item[idx].validate();
		// 再检测一遍
		if (!this.f_item[idx].checked) {
			printLog("验证属性 失败："+this.f_item[idx].field_id);
			flag = false;
			if(!validateAll){
				return false;
			}
			// 如果错误就返回失败，阻止提交
//			return false;
		}
	}
	
	if(flag){
		return true;
	}else {
		return false;
	}
	// 一个都没错就返回成功执行提交
};
function log2(msg, obj) {
}


/*
var uf = new UserForm([ new Field({
	fid : "f1",
	val : [ new Len_val(1, 5, "长度错误") ],
	suc : function(text) {
		$('#t1').html('');
		$('#t1').attr('class', 'suc');
	},
	err : function(text) {
		$('#t1').html(text);
		$('#t1').attr('class', 'error');
	}
}) ]);
uf.set_submit("bt", function(form) {
	alert("表单已经提交了");
}); 

if(uf.validate()){
 xx
 }

*
*/
