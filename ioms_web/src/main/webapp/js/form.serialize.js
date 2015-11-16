/**
 * 
 */
function form_serialize(jsonChosen,jsonChosenM){
	var json={};
	$(":text,:password,:hidden").each(function(i){
		var key=$(this).attr('name');
		var value=$(this).val();
		json[key]=value;
	});
	$("textarea").each(function(i){
		var key=$(this).attr('name');
		var value=$(this).val();
		json[key]=value;
	});
	//所有的下拉单选
	if(jsonChosen!=null || jsonChosen==''){
		for(var o in jsonChosen){  
	        json[o]=jsonChosen[o];
	    }
	}
	//所有的下拉多选
	if(jsonChosenM!=null|| jsonChosenM==''){
		for(var o in jsonChosenM){  
	        json[o]=jsonChosenM[o].join(',');
	    }
	}
	return json;
}