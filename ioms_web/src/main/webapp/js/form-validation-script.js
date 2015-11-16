var Script = function () {

    $.validator.setDefaults({
        submitHandler: function() { alert("submitted!"); }
    });

    $().ready(function() {
        // validate the comment form when it is submitted
        $("#commentForm").validate();
        
        // validate signup form on keyup and submit
        $("#signupForm").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 2
                },
                password: {
                    minlength: 5
                },
                confirm_password: {
                    minlength: 5,
                    equalTo: "#password"
                },
                email: {
                    required: true,
                    email: true
                },
                topic: {
                    required: "#newsletter:checked",
                    minlength: 2
                },
                agree: "required"
            },
            messages: {
                username: {
                    required: "用户名不能为空",
                    minlength: "用户名不得少于2个字符"
                },
                password: {
                    minlength: "密码最少5个字符"
                },
                confirm_password: {
                    minlength: "重复密码最少5个字符",
                    equalTo: "2次密码输入不一致"
                },
                email: "请输入正确的邮箱地址",
                agree: "请确认信息后同意"
            }
        });

        //code to hide topic selection, disable for demo
        var newsletter = $("#newsletter");
        // newsletter topics are optional, hide at first
        var inital = newsletter.is(":checked");
        var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
        var topicInputs = topics.find("input").attr("disabled", !inital);
        // show when newsletter is checked
        newsletter.click(function() {
            topics[this.checked ? "removeClass" : "addClass"]("gray");
            topicInputs.attr("disabled", !this.checked);
        });
    });


}();