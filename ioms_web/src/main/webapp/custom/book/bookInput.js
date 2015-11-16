var bookLocal = '/book/bookAction';
var chosenBookType;
var chosenBookState;
var bookInputDate;
var validateParam = {
	rules : {
		bookCode : {
			required : true,
		},
		bookName : {
			required : true,
		},
		bookPrice : {
			required : true,
			number:true,
			min:1
		},
		bookInputDate : {
			required : true,
		}

	},
	messages : {
		bookCode : {
			required : "图书编号不能为空",
		},
		bookName : {
			required : "图书名称不能为空",
		},
		bookPrice : {
			required : "图书售价不能为空",
			number: "图书售价必须为数字",
			min: "图书售价最少为1",
		},
		bookInputDate : {
			required : "图书入库日期不能为空",
		}
	},
	onfocusout : function(element) {
		$(element).valid();
	}
};
var validate;
var editParam;
var addParam;
function initNotView(bookState) {
	if(bookState!=-1){
		chosenBookState = $("#bookState").chosen({
			no_results_text : '没有匹配结果'
		});
	}
	chosenBookType = $("#bookType").chosen({
		no_results_text : '没有匹配结果'
	}).change(function() {
		if (chosenBookType.val() == null) {// valid
			showError();
		} else {
			removeError();
		}
	});
	bookInputDate = $('#bookInputDate').inputmask("y-m-d", {
		"placeholder" : "yyyy/mm/dd"
	});
	removeError();
	validate = $("#input_book_form").validate(validateParam);
	$('#bookTypeValidate').blur(function() {
		if (chosenBookType.val() == null) {// valid
			showError();
		} else {
			removeError();
		}
	});

}
function chose_mult_set_ini(values, type) {
	var select = '#bookType';
	var arr = values.split(',');
	var length = arr.length;
	console.log(length);
	var value = '';
	for (var i = 0; i < length; i++) {
		value = arr[i];
		$(select + " [value='" + value + "']").attr('selected', 'selected');
	}
	if (type == 'ADD')
		$(select + " [value='" + 1 + "']").attr('selected', 'selected');
	$(select).trigger("liszt:updated");
}
function showError() {
	$("#bookTypeError").addClass("error").html("图书类型至少选择一项");
	$("#bookTypeError").removeAttr("style");// display: none;
}
function removeError() {
	$("#bookTypeError").removeClass("error").html("");
	$("#bookTypeError").css('display', 'none');
}
function ck_index() {
	blockPost(ctx + '/welcome/main.htm');
}
function ck_book() {
	blockPost(ctx + bookLocal + 'List.htm');
}
function save(id) {
	var dialog = new DialogFunc("提示信息", '保存基本信息成功', "success").init('success',
			'hiddenDialog');
	if ($('#input_book_form').valid()) {
		if (chosenBookType.val() == null) {// valid
			showError();
		} else {
			removeError();
			var bookCode = $('#bookCode').val();
			var bookType = chosenBookType.val().join(',');
			var bookName = $('#bookName').val();
			var bookPrice = $('#bookPrice').val();
			var bookInputDate = $('#bookInputDate').val();
			var bookDesc = $('#bookDesc').val();
			var bookState = chosenBookState.val();
			if (id == null || id == '') {
				addParam = {
					bookCode : bookCode,
					bookType : bookType,
					bookName : bookName,
					bookPrice : bookPrice,
					bookDesc:bookDesc,
					bookInputDate:bookInputDate,
					bookState:bookState
				};
				$.ajax({
					type : "post",
					url : ctx + bookLocal + "Save.htm",
					data : addParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '新增图书信息成功.',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_book();
						} else {
							dialog = new DialogFunc("错误", '新增图书信息失败', "alert")
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
				editParam = {
					bookId : id,
					bookCode : bookCode,
					bookType : bookType,
					bookName : bookName,
					bookPrice : bookPrice,
					bookDesc:bookDesc,
					bookInputDate:bookInputDate,
					bookState:bookState
				};
				$.ajax({
					type : "post",
					url : ctx + bookLocal + "Save.htm",
					data : editParam,
					success : function(data) {
						if (data == true) {
							dialog = new DialogFunc("提示信息", '保存图书信息成功',
									"success").init('success', 'hiddenDialog');
							dialog.show();
							ck_book();
						} else {
							dialog = new DialogFunc("错误", '保存图书信息失败', "alert")
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
}