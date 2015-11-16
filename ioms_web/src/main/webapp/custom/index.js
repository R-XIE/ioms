/**
 * 
 */
jQuery(document).ready(function() {
		   $('#cleanBuffer').click(function(){
			   $.post(ctx+'/welcome/cacheClean.htm');
			   $.post(ctx+'/welcome/main.htm', {}, function(data) {
	    			$("#main-content").html(data);
			   });
		   });
    	   $('#loginout').click(
    				function() {
    					//var site = $('#loginout > a').attr("data-site");
    					var dialog = new DialogFunc("询问", '您是否退出系统', "confirm").init(
    							'confirm', 'hiddenDialog');// loginout
    					dialog.show();
    					dialog.confirm(function() {
    						window.location.href =ctx+'/loginout.htm';
    					});
    		});
    		$('#myprofile').click(
    				function() {
    					var site = ctx+'/welcome/myprofile.htm';
    					blockPost(site);
    		});
    	   $.post(ctx+'/welcome/main.htm', {}, function(data) {
    			$("#main-content").html(data);
    		});
       });