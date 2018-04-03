/**
 * Created by dingxiaochi on 2018/4/3.
 */

/**
 * 鼠标滑过 edit_info 显示编辑按钮
 */

$(".edit_info").on('mouseover',function(){
    $(this).find('.edit_btns').show();
});

$(".edit_info").on('mouseout',function(){
    $(this).find('.edit_btns').hide();
});


/**
 * 点击编辑/增加按钮弹出弹出框, 编辑教师页面使用
 */
$(".teacher_edit_btn").on('click',function(){
    var teacherId = $(this).attr('data-teacher');
    var schoolId = $(this).attr('data-school');
    var url = "/school/load_edit_teacher_modal/"+teacherId+"/"+schoolId;
    $.get(url,function(data){
        $('#editTeacherModal').html(data);
        $('#editTeacherModal').modal('show');
    },'html');

});

