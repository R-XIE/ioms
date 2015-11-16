var param = {
	rules : {
		title : {
			required : true,
		},
		content : {
			required : true,
		},
		reservation : {
			required : true,
		}
	},
	messages : {
		title : {
			required : "推送标题不能为空"
		},
		content : {
			required : "推送内容不能为空"
		},
		reservation : {
			required : "推送起止时间不能为空"
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var validate;
$(function() {
	validate = $("#input_push_form").validate(param);
	$('#reservationtime').daterangepicker({
		timePicker : true,
		timePickerIncrement : 1,
		format : 'YYYY-MM-DD HH:mm:ss',
		timePicker12Hour:false
	});
});
function icon_down() {
	var el = $('#input_widget');
	if ($('#input_icon_down').hasClass("icon-chevron-down")) {
		$('#input_icon_down').removeClass("icon-chevron-down").addClass(
				"icon-chevron-up");
		el.slideUp(200);
	} else {
		$('#input_icon_down').removeClass("icon-chevron-up").addClass(
				"icon-chevron-down");
		el.slideDown(200);
	}
}
function icon_remove() {
	$('#input_icon_remove').parents(".widget").remove();
}
function save(id) {
	var param;
	var dialog = new DialogFunc("提示信息", '修改信息成功', "success").init('success',
			'hiddenDialog');
	//这里没写完
	if($("#input_push_form").valid()){
		var time=$('#reservationtime').val();
		var beginTime=time.split(' - ')[0];
		var endTime=time.split(' - ')[1];
		if (id == null || id == '') {
			param = {
					title : $('#title').val(),
					content :$('#content').val(),
					beginTime : beginTime,
					endTime : endTime
				};
			$.ajax({
				type : "post",
				url : biuPushPath+"add.action",
				data : param,
				dataType:"json", 
				success : function(data) {
					if (data.result == true) {
						dialog = new DialogFunc("提示信息", '新增推送信息成功', "success")
								.init('success', 'hiddenDialog');
						dialog.show();
						icon_remove();
						$('#index_refresh').trigger('click');
					} else {
						dialog = new DialogFunc("错误", '新增推送信息失败', "alert").init(
								'alert', 'hiddenDialog');
						dialog.show();
					}
				},
				error : function(e) {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
							'alert', 'hiddenDialog');
					dialog.show();
				}
			});
		}else{
			param = {
					id:id,
					title :$('#title').val(),
					content : $('#content').val(),
					beginTime : beginTime,
					endTime : endTime
				};
			$.ajax({
				type : "post",
				url : biuPushPath+"update.action",
				data : param,
			    dataType:"json", 
				success : function(data) {
					if (data.result == true) {
						dialog = new DialogFunc("提示信息", '编辑推送信息成功', "success")
								.init('success', 'hiddenDialog');
						dialog.show();
						icon_remove();
						$('#index_refresh').trigger('click');
					} else {
						dialog = new DialogFunc("错误", '编辑推送信息失败', "alert").init(
								'alert', 'hiddenDialog');
						dialog.show();
					}
				},
				error : function(e) {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
							'alert', 'hiddenDialog');
					dialog.show();
				}
			});
			
		}
		
	}
//	var url = ctx + '/base/positionActionSave.htm';
//	var dialog = new DialogFunc("提示信息", '修改信息成功', "success").init('success',
//			'hiddenDialog');
//	if ($('#positionName').valid()) {
//		if (id == null || id == '') {
//			param = {
//				positionName : positionName,
//				branchId : branchId,
//				positionDesc : positionDesc
//			};
//			$.ajax({
//				type : "post",
//				url : url,
//				data : param,
//				success : function(data) {
//					if (data == true) {
//						dialog = new DialogFunc("提示信息", '新增岗位成功', "success")
//								.init('success', 'hiddenDialog');
//						dialog.show();
//						icon_remove();
//						$('#index_refresh').trigger('click');
//					} else {
//						dialog = new DialogFunc("错误", '新增岗位失败', "alert").init(
//								'alert', 'hiddenDialog');
//						dialog.show();
//					}
//				},
//				error : function(e) {
//					dialog.hide();
//					$('#hiddenDialog').html();
//					$('div[class="modal-backdrop fade in"]').remove();
//					dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
//							'alert', 'hiddenDialog');
//					dialog.show();
//				}
//			});
//
//		} else {
//			param = {
//				positionId : id,
//				positionName : positionName,
//				branchId : branchId,
//				positionDesc : positionDesc
//			};
//			$.ajax({
//				type : "post",
//				url : url,
//				data : param,
//				success : function(data) {
//					if (data == true) {
//						dialog = new DialogFunc("提示信息", '编辑岗位成功', "success")
//								.init('success', 'hiddenDialog');
//						dialog.show();
//						icon_remove();
//						$('#index_refresh').trigger('click');
//					} else {
//						dialog = new DialogFunc("错误", '编辑岗位失败', "alert").init(
//								'alert', 'hiddenDialog');
//						dialog.show();
//					}
//				},
//				error : function(e) {
//					dialog.hide();
//					$('#hiddenDialog').html();
//					$('div[class="modal-backdrop fade in"]').remove();
//					dialog = new DialogFunc("错误", 'JS执行错误', "alert").init(
//							'alert', 'hiddenDialog');
//					dialog.show();
//				}
//			});
//		}
//	}
}