//blockUI
function blockUI(el, centerY) {
	var el = $(el);
	el.block({
		message : '<img src="'+ctx+'/img/loading.gif" align="middle">',
		centerY : centerY != undefined ? centerY : true,
		css : {
			top : '10%',
			border : 'none',
			padding : '2px',
			backgroundColor : 'none'
		},
		overlayCSS : {
			backgroundColor : '#000',
			opacity : 0.05,
			cursor : 'wait'
		}
	});
}
function unblockUI(el) {
	$(el).unblock({
		onUnblock : function() {
			$(el).removeAttr("style");
		}
	});
}

function blockPost(url,param,div){
	var len=arguments.length;
	if(len!=3)
		div="main-content";
//	if(div.length==0){
//		div="main-content";
//	}
	var blockDIV = $('.loading-page');
	blockUI(blockDIV,false);
	$.ajax({
        url: url,
        type: "post",
        data: param,
        success: function(data) {
        	$("#"+div).html(data);
    		unblockUI(blockDIV);
        },
        error: function() {
            unblockUI(blockDIV);
            var dialog = new DialogFunc("警告", '系统繁忙,请稍后再试!', "alert").init(
					'alert', 'hiddenDialog');// loginout
            dialog.show();
        }
    });
}
function blockGet(url,param,div){
	var len=arguments.length;
	if(len!=3)
		div="main-content";
//	if(div.length==0){
//		div="main-content";
//	}
	var blockDIV = $('.loading-page');
	blockUI(blockDIV,false);
	$.ajax({
        url: url,
        type: "get",
        data: param,
        success: function(data) {
        	$("#"+div).html(data);
    		unblockUI(blockDIV);
        },
        error: function() {
            unblockUI(blockDIV);
            var dialog = new DialogFunc("警告", '系统繁忙,请稍后再试!', "alert").init(
					'alert', 'hiddenDialog');// loginout
            dialog.show();
        }
    });
}
var Script = function() {

	// sidebar dropdown menu

	jQuery('#sidebar .sub-menu > a').click(function() {
		var last = jQuery('.sub-menu.open', $('#sidebar'));
		last.removeClass("open");
		jQuery('.arrow', last).removeClass("open");
		jQuery('.sub', last).slideUp(200);
		var sub = jQuery(this).next();
		if (sub.is(":visible")) {
			jQuery('.arrow', jQuery(this)).removeClass("open");
			jQuery(this).parent().removeClass("open");
			sub.slideUp(200);
		} else {
			jQuery('.arrow', jQuery(this)).addClass("open");
			jQuery(this).parent().addClass("open");
			sub.slideDown(200);
		}
		var o = ($(this).offset());
		diff = 200 - o.top;
		if (diff > 0)
			$(".sidebar-scroll").scrollTo("-=" + Math.abs(diff), 500);
		else
			$(".sidebar-scroll").scrollTo("+=" + Math.abs(diff), 500);
	});
	// menu a bind
	jQuery('#sidebar .sub-menu > .sub > li > a').click(function() {
		var url = $(this).attr("data-url");
		blockPost(url);
	});

	// sidebar toggle

	$('.icon-reorder').click(function() {
		if ($('#sidebar > ul').is(":visible") === true) {
			//隐藏时间
			$('#main-content').css({
				'margin-left' : '0px'				
			});
			$('#sidebar').css({
				'margin-left' : '-180px',
			});
			$('.sidebar-scroll').css({
				'z-index':'-100'
			});
			//'z-index':'-100'
			$('#sidebar > ul').hide();
			$("#container").addClass("sidebar-closed");
		} else {
			//显示事件
			$('#main-content').css({
				'margin-left' : '180px'
			});
			$('#sidebar > ul').show();
			$('#sidebar').css({
				'margin-left' : '0'				
			});
			$('.sidebar-scroll').css({
				'z-index':'100'
			});
			$("#container").removeClass("sidebar-closed");
		}
	});

	// custom scrollbar
	$(".sidebar-scroll").niceScroll({
		styler : "fb",
		cursorcolor : "#4A8BC2",
		cursorwidth : '5',
		cursorborderradius : '0px',
		background : '#404040',
		cursorborder : ''
	});

	$(".portlet-scroll-1").niceScroll({
		styler : "fb",
		cursorcolor : "#4A8BC2",
		cursorwidth : '5',
		cursorborderradius : '0px',
		background : '#404040',
		cursorborder : ''
	});

	$(".portlet-scroll-2").niceScroll({
		styler : "fb",
		cursorcolor : "#4A8BC2",
		cursorwidth : '5',
		cursorborderradius : '0px',
		autohidemode : false,
		cursorborder : ''
	});

	$(".portlet-scroll-3").niceScroll({
		styler : "fb",
		cursorcolor : "#4A8BC2",
		cursorwidth : '5',
		cursorborderradius : '0px',
		background : '#404040',
		autohidemode : false,
		cursorborder : ''
	});

	$("html").niceScroll({
		styler : "fb",
		cursorcolor : "#4A8BC2",
		cursorwidth : '8',
		cursorborderradius : '0px',
		background : '#404040',
		cursorborder : '',
		zindex : '1000'
	});

	// theme switcher

	var scrollHeight = '60px';
	jQuery('#theme-change')
			.click(
					function() {
						if ($(this).attr("opened") && !$(this).attr("opening")
								&& !$(this).attr("closing")) {
							$(this).removeAttr("opened");
							$(this).attr("closing", "1");

							$("#theme-change")
									.css("overflow", "hidden")
									.animate(
											{
												width : '20px',
												height : '22px',
												'padding-top' : '3px'
											},
											{
												complete : function() {
													$(this).removeAttr(
															"closing");
													$("#theme-change .settings")
															.hide();
												}
											});
						} else if (!$(this).attr("closing")
								&& !$(this).attr("opening")) {
							$(this).attr("opening", "1");
							$("#theme-change").css("overflow", "visible")
									.animate({
										width : '226px',
										height : scrollHeight,
										'padding-top' : '3px'
									}, {
										complete : function() {
											$(this).removeAttr("opening");
											$(this).attr("opened", 1);
										}
									});
							$("#theme-change .settings").show();
						}
					});

	jQuery('#theme-change .colors span').click(function() {
		var color = $(this).attr("data-style");
		var site = $(this).attr("data-site");
		setColor(color, site);
	});

	jQuery('#theme-change .layout input').change(function() {
		setLayout();
	});

	var setColor = function(color, site) {
		$('#style_color').attr("href", site + "/css/style-" + color + ".css");
	};

	// widget tools

	jQuery('.widget .tools .icon-chevron-down').click(
			function() {
				var el = jQuery(this).parents(".widget").children(
						".widget-body");
				if (jQuery(this).hasClass("icon-chevron-down")) {
					jQuery(this).removeClass("icon-chevron-down").addClass(
							"icon-chevron-up");
					el.slideUp(200);
				} else {
					jQuery(this).removeClass("icon-chevron-up").addClass(
							"icon-chevron-down");
					el.slideDown(200);
				}
			});

	jQuery('.widget .tools .icon-remove').click(function() {
		jQuery(this).parents(".widget").parent().remove();
	});

	// tool tips

	$('.element').tooltip();

	$('.tooltips').tooltip();

	// popovers

	$('.popovers').popover();

	// scroller

	$('.scroller').slimscroll({
		height : 'auto'
	});
	//
}();