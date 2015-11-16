var Script = function () {
//    sidebar toggle
    $('i[class=icon-reorder]').click(function () {
        if ($('#sidebar > ul').is(":visible") === true) {
            $('#main-content').css({
                'margin-left': '0px'
            });
            $('#sidebar').css({
                'margin-left': '-180px'
            });
            $('#sidebar > ul').hide();
            $("#container").addClass("sidebar-closed");
        } else {
            $('#main-content').css({
                'margin-left': '180px'
            });
            $('#sidebar > ul').show();
            $('#sidebar').css({
                'margin-left': '0'
            });
            $("#container").removeClass("sidebar-closed");
        }
    });
    
//// custom scrollbar
//    $(".sidebar-scroll").niceScroll({styler:"fb",cursorcolor:"#4A8BC2", cursorwidth: '5', cursorborderradius: '0px', background: '#404040', cursorborder: ''});
//
//    $(".portlet-scroll-1").niceScroll({styler:"fb",cursorcolor:"#4A8BC2", cursorwidth: '5', cursorborderradius: '0px', background: '#404040', cursorborder: ''});
//
//    $(".portlet-scroll-2").niceScroll({styler:"fb",cursorcolor:"#4A8BC2", cursorwidth: '5', cursorborderradius: '0px', autohidemode: false, cursorborder: ''});
//
//    $(".portlet-scroll-3").niceScroll({styler:"fb",cursorcolor:"#4A8BC2", cursorwidth: '5', cursorborderradius: '0px', background: '#404040', autohidemode: false, cursorborder: ''});
//
//    $("html").niceScroll({styler:"fb",cursorcolor:"#4A8BC2", cursorwidth: '8', cursorborderradius: '0px', background: '#404040', cursorborder: '', zindex: '1000'});
//


// widget tools

    jQuery('.widget .tools .icon-chevron-down').click(function () {
        var el = jQuery(this).parents(".widget").children(".widget-body");
        if (jQuery(this).hasClass("icon-chevron-down")) {
            jQuery(this).removeClass("icon-chevron-down").addClass("icon-chevron-up");
            el.slideUp(200);
        } else {
            jQuery(this).removeClass("icon-chevron-up").addClass("icon-chevron-down");
            el.slideDown(200);
        }
    });

    jQuery('.widget .tools .icon-remove').click(function () {
        jQuery(this).parents(".widget").parent().remove();
    });
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
}();