var Script = function () {
//    sidebar toggle


// widget tools
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