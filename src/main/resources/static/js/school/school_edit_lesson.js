/**
 * Created by dingxiaochi on 2018/4/3.
 */

/**
 * dropdone图片上传插件初始化
 * @type {boolean}
 */
Dropzone.autoDiscover = false;
var myDropzone = new Dropzone("#lessonDropzone", {
    url: "/school/upload_images",
    addRemoveLinks: true,
    method: 'post',
    maxFiles:1,//一次性上传的文件数量上限
    maxFilesize: 2, //MB
    acceptedFiles: ".jpg,.gif,.png", //上传的类型
    parallelUploads: 3,
    dictMaxFilesExceeded: "您最多只能上传1个文件！",
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
            $("#image").val(response.fileName);
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
 * 选择教师时
 */
$(".teacher_select").click(function(){
    var teacherId = $(this).attr('data-teacher');
    var value = $("#teacherIds").val();
    //判断是否选中
    var cssname = $(this).find('img').attr('class');
    if(cssname=='unchecked_img'){   //未选择
        if(value!="" && value!=undefined){
            $("#teacherIds").val(value+teacherId+',');
        }else{
            $("#teacherIds").val(teacherId+',');
        }
        //改变样式
        $(this).find('img').attr('class','checked_img');
    }else{
        //已选择
        $("#teacherIds").val(value.replace(teacherId+',',''));
        //改变样式
        $(this).find('img').attr('class','unchecked_img');
    }

});

/**
 * 提交表单点击
 */
$("#submit_editLessonForm").on('click',function(){
    $("#editLessonForm").submit();
});

/**
 * 表单验证
 */
$("#editLessonForm").validate({
    onkeyup: false,
    ignore:"hidden",
    rules:{
        name:{
            required:true
        },
        introduce:{
            required:true
        }
    },
    messages:{
        name:{
            required:"请输入课程名称"
        },
        introduce:{
            required:"请输入课程简介"
        }
    },
    errorPlacement: function(error, element) {
        element.after(error);
    }
});

