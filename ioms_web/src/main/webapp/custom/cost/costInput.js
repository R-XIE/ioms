var costLocal = '/cost/costAction';
var dataParam = {
	language : 'zh-CN',
	format : 'yyyy-mm-dd hh:ii',
	pickDate: true,
    pickTime: true,
    showMeridian: true,
    hourStep: 1,
    minuteStep: 5,
	todayBtn : 1,
	autoclose : 1,
	inputMask: true
};
var validateParam = {
	rules : {
		costName : {
			required : true,
		},
		costMoney : {
			required : true,
			number:true,
			min:0,			
		},
		costTime : {
			required : true
		}
	},
	messages : {
		costName : {
			required : "费用名称不能为空",
		},
		costMoney : {
			required : "费用金额不能为空",
			number:"费用金额必须为数字",
			min:"费用金额最少为0",			
		},
		costTime : {
			required : "产生费用的时间不能为空"
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var validate;
var editParam;
var addParam;
var chosenCostType;
var costDate;
function initNotView() {
	chosenCostType = $("#costType").chosen();
	// validate=$("#input_position_form").validate(param);
	costDate = $('#costTime').datetimepicker(dataParam);
	validate = $("#input_cost_form").validate(validateParam);
}
function download(detail){
	var out=$('#id').val()+$('#costType').val()+"明细"+
	$('#costTime').val().split(' ')[0].replace('-','').replace('-','');
	out=encodeURI(out);
window.location = ctx + costLocal + "Download.htm?fileName="+detail+"&out="+out;
}
function ck_cost() {
	blockPost(ctx + costLocal + 'ListAjax.htm');
}
function upload(id){
	blockPost(ctx+costLocal+'UploadView.htm',{recordId:id});
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存基本信息成功', "success").init('success',
			'hiddenDialog');
	var json=form_serialize({costType:chosenCostType.val()});
	if ($('#input_cost_form').valid()) {
		if (id == null || id == '') {
			addParam =json;
			$.ajax({
				type : "post",
				url : ctx + costLocal + "Save.htm",
				data : addParam,
				success : function(data) {
					if (data == true) {
						dialog = new DialogFunc("提示信息", '新增费用信息成功.',
								"success").init('success', 'hiddenDialog');
						dialog.show();
						ck_cost();
					} else {
						dialog = new DialogFunc("错误", '编辑费用信息失败', "alert").init(
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
		} else {
			editParam =json;
			json['costId']=id;
			$.ajax({
				type : "post",
				url : ctx + costLocal + "Save.htm",
				data : editParam,
				success : function(data) {
					if (data == true) {
						dialog = new DialogFunc("提示信息", '保存费用信息成功', "success")
								.init('success', 'hiddenDialog');
						dialog.show();
						ck_cost();
					} else {
						dialog = new DialogFunc("错误", '保存费用信息失败', "alert").init(
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
}