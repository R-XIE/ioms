var costLocal = '/cost/costAction';
function ck_cost(id) {
	blockPost(ctx + costLocal + 'ListAjax.htm');
}
function ck_cost_edit(id){
	blockPost(ctx+costLocal+'Input.htm', {recordId:id});
}
var dialog = new DialogFunc("提示信息", '保存基本信息成功', "success").init('success',
'hiddenDialog');
function del(id,filename){
	dialog = new DialogFunc("询问", '您是否删除明细信息', "confirm").init(
			'confirm', 'hiddenDialog');// loginout
	dialog.show();
	dialog.confirm(function() {
		$.ajax({
			type : "post",
			url : ctx + costLocal + "DelFile.htm",
			data : {recordId:id,fileName:filename},
			success : function(data) {
				if (data == true) {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					dialog = new DialogFunc("提示信息", '删除明细信息成功.',
							"success").init('success', 'hiddenDialog');
					dialog.show();
					createDelDownload('','h');
				} else {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					dialog = new DialogFunc("错误", '删除明细信息失败', "alert").init(
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
	});
}
function download(detail){
	var out=$('#id').val()+$('#costType').val()+"明细"+
		$('#costTime').val().split(' ')[0].replace('-','').replace('-','');
	out=encodeURI(out);
	window.location = ctx + costLocal + "Download.htm?fileName="+detail+"&out="+out;
}
function createDelDownload(detail,type){
	var id=$('#id').val();
	if(type=='s'){
		$('#delBtn').attr('onclick',"del('"+id+"','"+detail+"')");
		$('#downloadBtn').attr('onclick',"download('"+detail+"')");
		$('#delBtn').show();
		$('#downloadBtn').show();
	}
	else{
		$('#delBtn').removeAttr('onclick');
		$('#downloadBtn').removeAttr('onclick');
		$('#delBtn').hide();
		$('#downloadBtn').hide();
	}
}
$(function () {
	'use strict';
	var url = ctx+costLocal+'UploadFile.htm';
	if($('#detail').val()==''){
		$('#delBtn').hide();
		$('#downloadBtn').hide();
	}else{
		$('#delBtn').show();
		$('#downloadBtn').show();
	}
	$('#fileupload').fileupload({
		url : url,
		dataType : 'text',
		submit:function(){
			$('#fileupload').prop('disabled',true);
		},
		done : function(e, data) {
			$('#fileupload').prop('disabled',false);
			//ck_interview_uploadView($('#id').val());
			dialog = new DialogFunc("提示信息", '上传文件成功.',
			"success").init('success', 'hiddenDialog');
			dialog.show();
			createDelDownload(data.result,'s');
		}
	});
});
