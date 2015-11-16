var Script = function() {
	$("#info_refresh").click(function() {
		var url = $(this).attr("data-url");
		blockPost(url);
	});
	
	$("#index_refresh").click(function() {
		var url = $(this).attr("data-url");
		blockPost(url);
	});
}();

function infoRefresh(){
	$("#info_refresh").click(function() {
		var url = $(this).attr("data-url");
		blockPost(url);
	});
}

function indexRefresh(){
	$("#index_refresh").click(function() {
		var url = $(this).attr("data-url");
		blockPost(url);
	});
}