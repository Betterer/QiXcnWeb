<#import "../common/template.ftl" as template>

<link href="${template.base}/css/dropzone.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${template.base}/js/common/dropzone.js"></script>
<script type="text/javascript" src="${template.base}/js/common/jquery.validate.js"></script>

<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="exampleModalLabel">
                新增/修改 教师信息
            </h4>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <label for="name" class="control-label">教师图片:</label>
                <form action="${template.base}/school/upload_images" class="dropzone dz-clickable" id="teacherDropzone" enctype="multipart/form-data">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <#if (teacher.imageUrl??) && (teacher.image??)>
                    <input type="hidden" class="schoolImageUrl" data="${teacher.image}" value="${teacher.imageUrl}"/>
                <#else>
                    <div class="dz-message needsclick">
                        点击此处上传教师图片.<br>
                        <span class="note needsclick">(最多上传1张图片,且每张图片大小不能超过2M)</span>
                    </div>
                </#if>
                </form>
            </div>


            <form method="post" action="${template.base}/school/edit_teacher" id="editTeacherForm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="schoolId" id="schoolId" value="${schoolId}"/>
                <input type="hidden" name="id" id="id" value="${teacher.id!''}"/>
                <input type="hidden" name="image" id="image" value="${teacher.image!''}"/>
                <div class="form-group">
                    <label for="name" class="control-label">教师名称:</label>
                    <input type="text" class="form-control" id="name" name="name" value="${teacher.name!''}">
                </div>
                <div class="form-group">
                    <label for="introduce" class="control-label">教师简介:</label>
                    <textarea class="form-control" id="introduce" name="introduce">${teacher.introduce!''}</textarea>
                </div>
                <div class="form-group">
                    <label for="recipient-name" class="control-label">是否推荐:</label>
                    <#if teacher.recommend??>
                        <#if teacher.recommend==1>
                            <input type="radio" name="recommend"  id='recommend' value="1" checked> 是
                            <input type="radio" name="recommend" value="0"> 否
                        <#else>
                            <input type="radio" name="recommend"  id='recommend' value="1"> 是
                            <input type="radio" name="recommend" value="0" checked> 否
                        </#if>
                    <#else>
                        <input type="radio" name="recommend"  id='recommend' value="1" checked > 是
                        <input type="radio" name="recommend" value="0"> 否
                    </#if>
                    <span>该字段决定教师是否显示在学校页面</span>
                </div>
            </form>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="submit_editTeacherForm">提交</button>
        </div>
    </div>
</div>

<script type="text/javascript" src="${template.base}/js/school/school_edit_teacher.js"></script>