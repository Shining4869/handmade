//表单验证
$("#Form").validate({
	
errorElement: 'div',
	rules : {
		"name" : {
			required : true
       	},
       	"cusName" : {
       		required : true
       	},
       	"appointment" : {
       		required : true
       	},
       	"appointStartTime" : {
       		required : true
       	},
       	"appointEndTime" : {
       		required : true
       	},
       	"realStartTime" : {
       		required : true
       	},
       	"realEndTime" : {
       		required : true
       	},
       	"visitContent" : {
       		required : true
       	}
	},
	messages: {
		name: {
			required: "客户名称不能为空"
		},
		cusName: {
			required: "客户名称不能为空"
		},
		appointment: {
			required: "预约对象不能为空"
		},
		appointStartTime: {
			required: "预约开始时间不能为空"
		},
		appointEndTime: {
			required: "预约结束时间不能为空"
		},
		realStartTime: {
			required: "拜访开始时间不能为空"
		},
		realEndTime: {
			required: "拜访结束时间不能为空"
		},
		visitContent: {
			required: "拜访记录不能为空"
		}
	},
});
