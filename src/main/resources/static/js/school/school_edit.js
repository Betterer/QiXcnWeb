/**
 * Created by dingxiaochi on 2018/4/3.
 */

/*************************************************  编辑教师 ***********************************************************/

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

/**
 * 点击删除安你要弹出确认框,编辑教师页面使用
 */
$(".teacher_delete_btn").on('click',function(){
    var teacherId = $(this).attr('data-teacher');
    var schoolId = $(this).attr('data-school');
    var url = "/school/delete_teacher/"+teacherId+"/"+schoolId;
    swal({
        title: '删除用户',
        text: "确定删除该教师么!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '是的,删除!',
        cancelButtonText: '不,再想想.',
        closeOnConfirm: false
    }).then(function(isConfirm) {
        if (!isConfirm) return;
        $.get(url,function(data){
            window.location.reload();
        },'json');
    })

});



/*************************************************  编辑课程 ***********************************************************/



/**
 * 点击编辑/增加按钮弹出弹出框,编辑课程页面使用
 */
$(".lesson_edit_btn").on('click',function(){
    var lessonId = $(this).attr('data-lesson');
    var schoolId = $(this).attr('data-school');
    var url = "/school/load_edit_lesson_modal/"+lessonId+"/"+schoolId;
    $.get(url,function(data){
        $('#editLessonModal').html(data);
        $('#editLessonModal').modal('show');
    },'html');

});
