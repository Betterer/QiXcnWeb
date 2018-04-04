<#import "../common/template.ftl" as template>

<link href="${template.base}/css/dropzone.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${template.base}/js/common/dropzone.js"></script>
<script type="text/javascript" src="${template.base}/js/common/jquery.validate.js"></script>

<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="exampleModalLabel">
                <#if lesson.id??>
                    修改
                <#else>
                    新增
                </#if>
                课程信息
            </h4>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <label for="name" class="control-label">课程图片:</label>
                <form action="${template.base}/school/upload_images" class="dropzone dz-clickable" id="lessonDropzone" enctype="multipart/form-data">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <#if (lesson.imageUrl??) && (lesson.image??)>
                    <input type="hidden" class="schoolImageUrl" data="${lesson.image}" value="${lesson.imageUrl}"/>
                <#else>
                    <div class="dz-message needsclick">
                        点击此处上传课程图片.<br>
                        <span class="note needsclick">(最多上传1张图片,且每张图片大小不能超过2M)</span>
                    </div>
                </#if>
                </form>
            </div>


            <form method="post" action="${template.base}/school/edit_lesson" id="editLessonForm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="schoolId" id="schoolId" value="${schoolId}"/>
                <input type="hidden" name="id" id="id" value="${lesson.id!''}"/>
                <input type="hidden" name="image" id="image" value="${lesson.image!''}"/>
                <div class="form-group">
                    <label for="name" class="control-label">课程名称:</label>
                    <input type="text" class="form-control" id="name" name="name" value="${lesson.name!''}">
                </div>
                <div class="form-group">
                    <label for="name" class="control-label">课程价格:</label>
                    <input type="text" class="form-control" id="price" name="price" value="${lesson.price!''}">
                </div>
                <div class="form-group">
                    <label for="name" class="control-label">学习周期:</label>
                    <input type="text" class="form-control" id="duration" name="duration" value="${lesson.duration!''}">
                </div>
                <div class="form-group">
                    <label for="introduce" class="control-label">课程简介:</label>
                    <textarea class="form-control" id="introduce" name="introduce">${lesson.introduce!''}</textarea>
                </div>
                <div class="form-group">
                    <label for="" class="control-label">所属类目:</label>
                    <div class="btn-group" role="group" aria-label="">
                        <#list categoryList as category>
                            <input type="radio" name="categoryId" id="categoryId" value="${category.id}"> ${category.name}
                        </#list>
                    </div>
                </div>
                <div class="form-group">
                    <label for="introduce" class="control-label">任课老师:</label>
                    <div class="btn-group" role="group" aria-label="">
                        <input type="hidden" name="teacherIds" id="teacherIds"/>
                        <#list teacherList as teacher>
                            <a href="javascript:void(0);" class="teacher_select" data-teacher="${teacher.id}">
                                <img class="unchecked_img" src="${teacher.imageUrl}"/>
                            </a>
                        </#list>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="submit_editLessonForm">提交</button>
        </div>
    </div>
</div>

<script type="text/javascript" src="${template.base}/js/school/school_edit_lesson.js"></script>