var oTable;
var salaryLocal='/welcome/salary';
	$(function() {
		//EditableTable.init();
		oTable=$('#editable-sample').dataTable(maxDataTable);
	});
	function doAction(fn,id){
		if(fn=='view'){
			blockPost(ctx+salaryLocal+'View.htm', {recordId:id});
		}
	}