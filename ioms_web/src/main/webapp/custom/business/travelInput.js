var travelLocal = '/business/businessTravelAction';
var dataParam = {
		timePicker : true,
		timePickerIncrement : 1,
		format : 'YYYY-MM-DD HH:mm',
		timePicker12Hour:false
};
var validateParam = {
	rules : {
		travelTime : {
			required : true,
		},
		travelAddress : {
			required : true,
		},
		travelGoal : {
			required : true,
		},
		travelVehicle : {
			required : true,
		},
		travelInvoiceNum:{
			digits:true,
			min:0,
		},
		travelTicket:{
			number:true,
			min:0,
		},
		travelFare:{
			number:true,
			min:0,
		},
		travelFares:{
			number:true,
			min:0,
		},
		travelTransport:{
			number:true,
			min:0,
		},
		travelAllowance:{
			number:true,
			min:0,
		},
		travelAccommodation:{
			number:true,
			min:0,
		},
		travelOther:{
			number:true,
			min:0,
		}

	},
	messages : {
		travelTime : {
			required : "出差起止时间不能为空",
		},
		travelAddress : {
			required : "出差地址不能为空",
		},
		travelGoal : {
			required : "出差目的不能为空"
		},
		travelVehicle : {
			required : "交通工具不能为空",
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var validate;
var editParam;
var addParam;
var chosenStaff;
var travelTime;
function initNotView() {
	chosenStaff = $("#travelStaff").chosen({
		no_results_text : '没有匹配结果'
	});
	travelTime = $('#travelTime').daterangepicker(dataParam);
	$.extend($.validator.messages,{
		number: "请输入合法的数字",  
		min: $.validator.format("请输入一个最小为 {0} 的值"),
		digits: "只能输入整数",
	});
	validate = $("#input_travel_form").validate(validateParam);
}
function ck_travel() {
	blockPost(ctx + travelLocal + 'List.htm');
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存出差信息成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_travel_form').valid()) {
			var travelStartTime =$('#travelTime').val().split(' - ')[0];
			var travelEndTime =$('#travelTime').val().split(' - ')[1];
			var json=form_serialize({travelStaff:chosenStaff.val()});
			json['travelStartTime']=travelStartTime;
			json['travelEndTime']=travelEndTime;
			if (id == null || id == '') {
				addParam =json;
				$.ajax({
					type : "post",
					url : ctx + travelLocal + "Save.htm",
					data : addParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '新增出差信息成功.',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_travel();
						} else {
							dialog = new DialogFunc("错误", '新增出差信息失败', "alert")
									.init('alert', 'hiddenDialog');
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
			} else {
				editParam = json;
				editParam['businessTravelId']=id;
				$.ajax({
					type : "post",
					url : ctx + travelLocal + "Save.htm",
					data : editParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '保存出差信息成功',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_travel();
						} else {
							dialog = new DialogFunc("错误", '保存出差信息失败', "alert")
									.init('alert', 'hiddenDialog');
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
	}