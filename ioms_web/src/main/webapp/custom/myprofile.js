var param = {
	rules : {
		username : {
			required : true,
			minlength : 2
		},
		password : {
			minlength : 5
		},
		confirm_password : {
			minlength : 5,
			equalTo : "#password"
		},
		email : {
			required : true,
			email : true
		},
		phone : {
			required : true,
			isPhone : true
		},
		emergency : {
			required : true,
			minlength : 5
		},
		address : {
			required : true,
			minlength : 5
		}
	},
	messages : {
		username : {
			required : "用户名不能为空",
			minlength : "用户名不得少于2个字符"
		},
		password : {
			minlength : "密码最少5个字符"
		},
		confirm_password : {
			minlength : "重复密码最少5个字符",
			equalTo : "2次密码输入不一致"
		},
		email : {
			required : "邮箱地址不能为空",
			email : "请输入正确的邮箱地址"
		},
		phone : {
			required : "联系方式不能为空",
			isPhone : "请正确填写您的联系方式"
		},
		emergency : {
			required : "紧急联系方式不能为空",
			minlength : "紧急联系方式不得少于5个字符"
		},
		address :{
			required : "用户住址不能为空",
			minlength : "用户住址不得少于5个字符"
		}
	},
	onfocusout: function(element) { 
	      $(element).valid(); 
	}
};
var Script = function () {
    $().ready(function() {
    	// 手机号码验证
    	var regTel=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
    	var regMobile=/^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;   
    	jQuery.validator.addMethod("isMobile", function(value, element) {       
    	    var length = value.length;   
    	    var mobile = regMobile;   
    	   return this.optional(element) || (length == 11 && mobile.test(value));       
    	}, "请正确填写您的手机号码");       
    	    
    	 // 电话号码验证       
    	jQuery.validator.addMethod("isTel", function(value, element) {       
    	     var tel = regTel;    //电话号码格式010-12345678   
    	    return this.optional(element) || (tel.test(value));       
    	}, "请正确填写您的电话号码");   
    	// 联系电话(手机/电话皆可)验证   
    	jQuery.validator.addMethod("isPhone", function(value,element) {   
    	    var mobile = regMobile;   
    	    var tel = regTel;   
    	    return this.optional(element) || (tel.test(value) || mobile.test(value));   
    	 
    	}, "请正确填写您的联系电话");   
        var validate=$("#profileForm").validate(param);
        //填写内容
        $("#username").change(function(){
        	 $('#view_username').html($(this).val());
        });
        $("#phone").change(function(){
        	 $('#view_phone').html($(this).val());
        });
        $("#emergency").change(function(){
        	 $('#view_emergency').html($(this).val());
        });
        $("#email").change(function(){
        	 $('#view_email').html($(this).val());
        });
        $("#address").change(function(){
        	 $('#view_address').html($(this).val());
        });
        var url = ctx+"/welcome/saveImgProfile.htm";
        $('#fileupload').fileupload({
    		url : url,
    		dataType : 'text',
    		submit:function(){
    			$('#fileupload').prop('disabled',true);
    		},
    		done : function(e, data) {
    			$('#fileupload').prop('disabled',false);
    			//ck_interview_uploadView($('#id').val());
    			var dialog = new DialogFunc("提示信息", '上传头像成功.',
    			"success").init('success', 'hiddenDialog');
    			dialog.show();
    			$('.infoIcon').attr('src',ctx+'/welcome/saveImgProfileDownload.htm?fileName='+data.result+"&r="+Math.random());
    		}
    	});
    });
}();
function ck(id,ctx){
	 var dialog = new DialogFunc("提示信息", '修改信息成功', "success").init(
				'success', 'hiddenDialog');
	if($('#username').valid()&& $('#password').valid() && $('#confirm_password').valid()){
		if($('#phone').valid()&& $('#emergency').valid() && $('#email').valid() &&$('#address').valid()){
			 if($("#agree").parent('span').attr('class')=="checked"){
				 //全部执行通过
				 var username=$('#view_username').html();
				 var password=$('#password').val();
				 var phone=$('#view_phone').html();
				 var emergency=$('#view_emergency').html();
				 var email=$('#view_email').html();
				 var address=$('#view_address').html();
				 //序列化表单元素，返回json数据  
		         var param={staffId:id,staffRealName:username,staffPwd:password,staffEmail:email,
		        		 staffPhone:phone,staffEmergency:emergency,staffAddress:address};
		         $.ajax({  
		                type:"post",  
		                url:ctx+"/welcome/saveprofile.htm",  
		                data:param,  
		                success:function(data){ 
		                	 dialog.hide();
		    	    		 $('#hiddenDialog').html();
		    	    		 $('div[class="modal-backdrop fade in"]').remove();
		                   if(data==true){
		                	 var info=$('#name_info');
		                	 info.html(username);
		                	 dialog = new DialogFunc("提示信息", '修改信息成功', "success").init(
			   							'success', 'hiddenDialog');
		                	 dialog.show();
		                	 $("#info_refresh").click();
		                   }else{
		                	 dialog = new DialogFunc("错误", '修改信息失败', "alert").init(
		   							'alert', 'hiddenDialog');
		   					 dialog.show();
		                   }
		                },  
		                error:function(e) {
		                	 dialog.hide();
		    	    		 $('#hiddenDialog').html();
		    	    		 $('div[class="modal-backdrop fade in"]').remove();
		                	dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
									'alert', 'hiddenDialog');
							dialog.show();
		                }  
		            });
				 //alert(username+","+phone+","+emergency+","+email+","+address+","+icon);
			 }else{
				 var dialog = new DialogFunc("提示信息", '请确认信息后勾选后提交', "alert").init(
							'alert', 'hiddenDialog');
					dialog.show();
			 }
		}else{
			$('#tabsleft').find("a[href*='tabsleft-tab2']").trigger('click');
		}
	}else{
		$('#tabsleft').find("a[href*='tabsleft-tab1']").trigger('click');
	}
}