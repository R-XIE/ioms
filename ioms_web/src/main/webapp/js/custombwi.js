//目前已经废除
function addCustomBWI(icon,content,badge,size){
	var cbwi='<a class="icon-btn span'+size+'"><i class="'+icon+'"></i>'+
	'<div>'+content+'</div><span class="badge badge-info">'+badge+'</span></a>';
//	<a class="icon-btn span2" href="#">
//    <i class="icon-group"></i>
//    <div>Users</div>
//    <span class="badge badge-important">2</span></a>
	return cbwi;
}