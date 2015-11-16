jQuery(document).ready(function() {
           $(" input[type=radio], input[type=checkbox]").uniform();
           $("input[name='staffId']").click(function(){
        	   	var checked;
        	   	var staffId=$(this).val();
        	   	var pwdId=$('#hid_pwdId').val();
        	   	var checkedInput=$(this);
              	if($(this).attr('checked')=='checked'){
              		checked=true;
                }else{
                	checked=false;
                }
              	//alert("resId:"+resId+",roleId:"+roleId+",checked:"+checked);
              	$.ajax({
					 type:"post",  
		                url:ctx+"/operation/pwdActionPermiss.htm",  
		                data:{staffId:staffId,pwdId:pwdId,checked:checked},  
		                success:function(data){ 
		    	    		if(data==true){
		    	    			if(checked==true){
		    	    				gritter($('#span_'+staffId).html()+'设置成功.');
		    	    			}else{
		    	    				gritter($('#span_'+staffId).html()+'撤销成功.');
		    	    			}
		    	    		}else{
		    	    			if(checked==true){
		    	    				checkedInput.removeAttr('checked');
		    	    				checkedInput.parent().attr('class','');
		    	    			}else{
		    	    				checkedInput.parent().attr('class','checked');
		    	    				checkedInput.attr('checked','true');
		    	    			}
		    	    			gritter($('#span_'+staffId).html()+'操作设置失败.');
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
       function ck_pwd(){
   	   	$.post(ctx+'/operation/pwdActionList.htm', {}, function(data) {
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