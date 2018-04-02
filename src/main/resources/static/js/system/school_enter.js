/** 系统 - 学校入驻页面使用的js
 * Created by dingxiaochi on 2018/3/29.
 */


/**
 * dropdone图片上传插件初始化
 * @type {boolean}
 */
Dropzone.autoDiscover = false;
var myDropzone = new Dropzone("#myDropzone", {
    url: "/school/upload_images",
    addRemoveLinks: true,
    method: 'post',
    maxFiles:10,//一次性上传的文件数量上限
    maxFilesize: 20, //MB
    acceptedFiles: ".jpg,.gif,.png", //上传的类型
    parallelUploads: 3,
    dictMaxFilesExceeded: "您最多只能上传10个文件！",
    dictResponseError: '文件上传失败!',
    dictInvalidFileType: "你不能上传该类型文件,文件类型只能是*.jpg,*.gif,*.png。",
    dictFallbackMessage:"浏览器不受支持",
    dictFileTooBig:"文件过大上传文件最大支持.",
    sending: function(file, xhr, formData) {
        formData.append("filesize", file.size);
    },
    success: function (file, response, e) {
        //上传图片如果失败,显示"X"号,如果成功,将文件名存到隐藏input中
        if (response.status == 200) {
            //将上传成功的文件名存到隐藏的<input name="image" id="image">中,方便表单上传
            var value = $("#image").val();
            if(value=='' || value==null){
                $("#image").val(response.fileName);
            }else{
                $("#image").val(value+","+response.fileName);
            }
            //将dropzone中图片的原名替换
            $(file.previewTemplate).children('.dz-details').children('.dz-filename').children('span').text(response.fileName);
        } else {
            $(file.previewTemplate).children('.dz-error-mark').css('opacity', '1')
        }
    },
    removedfile:function(file){
        //将图片预览删除
        $(file.previewTemplate).remove();
        //获得图片名称(存到OSS服务器中的新名称)
        var imageName = $(file.previewTemplate).children('.dz-details').children('.dz-filename').children('span').text();
        //将图片从隐藏input删除
        $("#image").val($("#image").val().replace(","+imageName,''));
        //todo:将图片从OSS服务器中删除
    },
    init:function(){
        //图片回显
        var myDropzone = this;
        //获取已存的图片
        $(".schoolImageUrl").each(function () {
            var fileName = $(this).attr("data");
            var path = $(this).val();
            var mockFile = { name: fileName, accepted:true };
            myDropzone.emit("addedfile", mockFile);
            myDropzone.emit("thumbnail", mockFile, path);
            myDropzone.emit("complete", mockFile);
        });
    }
});

/**
 * dropzone图片回显
 */
// var mockFile = { name: "123.jpg", accepted:true };
// myDropzone.emit("addedfile", mockFile);
// myDropzone.emit("thumbnail", mockFile, "http://edms.kitesky.com/upload/image/20170422/52edf3c2aabf171315d968d9af814d0c.jpg");
// myDropzone.emit("complete", mockFile);


/**
 * 自定义验证:网址格式验证
 */
jQuery.validator.addMethod("webSite", function(value, element, param) {
    var webSite = /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
    return this.optional(element) || webSite.test(value);
}, $.validator.format("请正确填写您的网站地址"));


/**
 * 自定义验证:座机格式验证
 */
jQuery.validator.addMethod("telphone", function(value, element, param) {
    var telphone = /^\d{3,4}-?\d{7,9}$/;
    return this.optional(element) || telphone.test(value);
}, $.validator.format("请正确填写您的座机号码"));

/**
 * 自定义验证:手机号码格式验证
 */
jQuery.validator.addMethod("phone", function(value, element, param) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, $.validator.format("请正确填写您的手机号码"));

/**
 * 学校信息录入表单验证
 */
$("#school_enter").validate({
    onkeyup: false,
    ignore:"hidden",
    rules:{
        name:{
            required:true
        },
        email:{
            required:true,
            email:true
        },
        cellphone:{
            telphone:true
        },
        telephone:{
            required:true,
            phone:true
        },
        webSite:{
            webSite:true
        },
        address:{
            required:true
        },
        introduce:{
            required:true
        }
    },
    messages:{
        name:{
            required:"请输入学校名称"
        },
        email:{
            required:"请输入邮箱地址",
            email:"电子邮箱格式不正确"
        },
        cellphone:{
            telphone:"请输入正确的座机号码"
        },
        telephone:{
            required:"请输入电话号码",
            phone:"请输入正确的电话号码"
        },
        webSite:{
            webSite:"请输入正确的网址"
        },
        address:{
            required:"请输入学校地址"
        },
        introduce:{
            required:"请输入学校简介"
        }
    },
    errorPlacement: function(error, element) {
        element.after(error);
    }
});
