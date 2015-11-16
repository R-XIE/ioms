var oTable;
var alterLocal='/welcome/salaryAlterAction';
	$(function() {
		//EditableTable.init();
		oTable=$('#editable-sample').dataTable(maxDataTable);
	});
	function doAction(fn,id){
		if(fn=='view'){
			blockPost(ctx+alterLocal+'View.htm', {recordId:id});
		}
	}
	function ck_index(){
		blockPost(ctx+'/welcome/main.htm');
   }