//表单验证
$("#Form").validate({
	
errorElement: 'div',
			errorClass: 'has-error',
			focusInvalid: false,
					
			rules : {
				"supName" : {
					required : true
		       	}
			},
			messages: {
				supName: {
					required: "供应商名称不能为空"
				}
			},
			invalidHandler: function (event, validator) { //display error alert on form submit   
				//$('.alert-danger', $('.login-form')).show();
			},
			highlight: function (e) {
				$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
			},
			success: function (e) {
				$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
				$(e).remove();
			},
			submitHandler: function (form) {
			},
			invalidHandler: function (form) {
			},		
		   /* 错误信息的显示位置 */   
		   errorPlacement: function(error, element) {
			   error.insertAfter(element.parent());
		   },
		   debug:true
		});
