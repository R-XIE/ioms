jQuery(document).ready(function() {
           $(" input[type=radio], input[type=checkbox]").uniform();
           $("input[name='resId']").click(function(){
        	   	var checked;
        	   	var resId=$(this).val();
        	   	var roleId=$('#hid_roleId').val();
        	   	var checkedInput=$(this);
              	if($(this).attr('checked')=='checked'){
              		checked=true;
                }else{
                	checked=false;
                }
              	//alert("resId:"+resId+",roleId:"+roleId+",checked:"+checked);
              	$.ajax({
					 type:"post",  
		                url:ctx+"/permiss/roleActionPermiss.htm",  
		                data:{roleId:roleId,resourcesId:resId,checked:checked},  
		                success:function(data){ 
		    	    		if(data==true){
		    	    			if(checked==true){
		    	    				gritter($('#span_'+resId).html()+'授权成功.');
		    	    			}else{
		    	    				gritter($('#span_'+resId).html()+'撤销成功.');
		    	    			}
		    	    		}else{
		    	    			if(checked==true){
		    	    				checkedInput.removeAttr('checked');
		    	    				checkedInput.parent().attr('class','');
		    	    			}else{
		    	    				checkedInput.parent().attr('class','checked');
		    	    				checkedInput.attr('checked','true');
		    	    			}
		    	    			gritter($('#span_'+resId).html()+'操作授权失败.');
		    	    		}
		                },  
				});	
           });
       });
       function ck_index(){
    	   	$.post(ctx+'/welcome/main.htm', {}, function(data) {
   				$("#main-content").html(data);
   			});
       }
       function ck_role(){
   	   	$.post(ctx+'/permiss/roleActionList.htm', {}, function(data) {
  				$("#main-content").html(data);
  			});
      }
      function gritter(text){
    	  var unique_id=$.gritter.add({
	            title: '系统提示',
	            text: text,
	            image: ctx+'/img/avatar2.jpg',
	            sticky: true,
	            time: '',
	            class_name: 'my-sticky-class'
	        });
			setTimeout(function(){
		         $.gritter.remove(unique_id, {
			         fade: true,
			         speed: 'slow'
		         });

		    }, 2500);
      }