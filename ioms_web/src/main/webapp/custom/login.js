/**
 * 
 */
$(function() {
		var x=$('#hid_flag').val();
		if(x){
			window.location.reload();
		}
		var dialog=new DialogFunc().init('alert');
		function alertUname() {
			dialog=new DialogFunc( "提示信息", '用户名不能为空',"alert").init('alert','hiddenDialog');
			if ($('#uname_input').val() == '') {
				dialog.show();
				//输入框获取焦点
				return false;
			} else {
				dialog.hide();
				return true;
			}
		}
		function alertPwd() {
			dialog=new DialogFunc( "提示信息", '密码不能为空',"alert").init('alert','hiddenDialog');
			if ($('#pwd_input').val() == '') {
				dialog.show();
				return false;
			} else {
				dialog.hide();
				return true;
			}
		}
		$('#btn_submit').click(function() {

			var username = $('#uname_input').val();
			var password = $('#pwd_input').val();
			if(!alertUname()){
				return;
			}
			if(!alertPwd()){return;}
			$('#sub_login').html('登录中...');
			$.post(ctx+"login_ajax.htm", {
				name : username,
				pwd : password
			}, function(data) {
				if (data == 'error') {
					dialog=new DialogFunc( "提示信息", '用户名密码错误，请重新输入',"alert").init('alert','hiddenDialog');
					dialog.show();
					$('#sub_login').html('登录');
				} else {
					window.location.href = ctx+'welcome/index.htm';
				}
			});
		});
		document.onkeydown = function(e)
		{
		    var e = window.event   ?   window.event   :   e; 
		    if(e.keyCode == 13){
		    	 dialog.hide();
	    		 $('#hiddenDialog').html();
	    		 $('div[class="modal-backdrop fade in"]').remove();
	    		 $("#btn_submit").trigger("click");
		    }
		}; 
	});
