/**用户注册表单前端验证
 * Created by dingxiaochi on 2018/3/18.
 */

$(document).ready(function(){


    /**
     * 自定义验证:手机号码格式验证
     */
    jQuery.validator.addMethod("phone", function(value, element, param) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, $.validator.format("请正确填写您的手机号码"));


    /**
     * 用户注册表单验证
     */
    $("#register_form").validate({
        onkeyup: false,
        ignore:"hidden",
        rules:{
            name:{
                required:true,
                remote:{
                    url: "/user/findUserByName",        //后台处理程序
                    type: "get",               //数据发送方式
                    dataType: "json",           //接受数据格式
                    data: {                     //要传递的数据
                        name: function() {
                            return $("#user_name").val();
                        }
                    }
                }
            },
            password:{
                required:true,
                minlength:6
            },
            confirm_password:{
                required: true,
                minlength: 6,
                equalTo: "#user_password"
            },
            phone:{
                required:true,
                phone:true,
                remote:{
                    url: "/user/findUserByPhone",        //后台处理程序
                    type: "get",               //数据发送方式
                    dataType: "json",           //接受数据格式
                    data: {                     //要传递的数据
                        phone: function() {
                            return $("#user_phone").val();
                        }
                    }
                }
            },
            email:{
                required:true,
                email:true,
                remote:{
                    url: "/user/findUserByEmail",        //后台处理程序
                    type: "get",               //数据发送方式
                    dataType: "json",           //接受数据格式
                    data: {                     //要传递的数据
                        email: function() {
                            return $("#user_email").val();
                        }
                    }
                }
            },
            gender:{
                required:true
            }
        },
        messages:{
            name:{
                required:"请输入用户账号",
                remote:"该账号已经注册"
            },
            password:{
                required: "请输入密码",
                minlength: "密码长度至少为6位"
            },
            confirm_password:{
                required:"请确认密码",
                minlength:"密码长度至少为6位",
                equalTo:"两次密码输入不一致"
            },
            phone:{
                required:"请输入电话号码",
                remote:"该电话号码已经注册",
                phone:"电话号码不正确"
            },
            email:{
                required:"请输入电子邮箱",
                email:"电子邮箱格式不正确",
                remote:"该邮箱已经注册"
            },
            gender:{
                required:"请选择用户性别"
            }
        },
        errorPlacement: function(error, element) {
            element.after(error);
        }
    });
});
