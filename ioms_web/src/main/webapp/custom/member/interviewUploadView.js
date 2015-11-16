var interviewLocal = '/member/interviewAction';
function ck_index() {
	blockPost(ctx + '/welcome/main.htm');
}
function ck_interview(id) {
	blockPost(ctx + interviewLocal + 'ListAjax.htm');
}
function ck_interview_edit(id){
	blockPost(ctx+interviewLocal+'Input.htm', {recordId:id});
}
function ck_interview_uploadView(id){
	blockPost(ctx+interviewLocal+'UploadView.htm', {recordId:id});
}
var dialog = new DialogFunc("提示信息", '保存基本信息成功', "success").init('success',
'hiddenDialog');
function del(id,filename){
	dialog = new DialogFunc("询问", '您是否删除个人简历', "confirm").init(
			'confirm', 'hiddenDialog');// loginout
	dialog.show();
	dialog.confirm(function() {
		$.ajax({
			type : "post",
			url : ctx + interviewLocal + "DelFile.htm",
			data : {recordId:id,fileName:filename},
			success : function(data) {
				if (data == true) {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					dialog = new DialogFunc("提示信息", '删除个人简历成功.',
							"success").init('success', 'hiddenDialog');
					dialog.show();
					createDelDownload('','h');
				} else {
					dialog.hide();
					$('#hiddenDialog').html();
					$('div[class="modal-backdrop fade in"]').remove();
					dialog = new DialogFunc("错误", '删除个人简历失败', "alert").init(
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
function download(cv){
	var out=$('#id').val()+$('#interviewName').val()+"的简历"+
	$('#interviewDate').val().split(' ')[0].replace('-','').replace('-','');
	out=encodeURI(out);
	window.location = ctx + interviewLocal + "Download.htm?fileName="+cv+"&out="+out;
}
//<button type="button" class="btn" onclick="del('${entity.interviewId}','${entity.interviewCv }')">
//<i class="icon-remove"></i>
//删除简历信息</button>
//<button type="button" class="btn" onclick="download('${entity.interviewCv }')">
//<i class="icon-download-alt"></i>
//下载简历信息</button>
function createDelDownload(cv,type){
	var id=$('#id').val();
	if(type=='s'){
		$('#delBtn').attr('onclick',"del('"+id+"','"+cv+"')");
		$('#downloadBtn').attr('onclick',"download('"+cv+"')");
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
	var url = ctx+interviewLocal+'UploadFile.htm';
	if($('#cv').val()==''){
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
