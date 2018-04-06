<#import "../common/template.ftl" as template>
    <@template.head title="起线科技--编辑教师">
    <link rel="stylesheet" type="text/css" href="${template.base}/css/sweetalert2.min.css">
    <link href="${template.base}/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${template.base}/css/responsive.css" rel="stylesheet" type="text/css" />
    <script src="${template.base}/js/common/sweetalert2.min.js"></script>
    </@template.head>

<@template.body>
<!-- banner -->
<div class="banner_child">
    <div class="container">
        <#if user??>
            <@template.loginUser userId="${user.id}" userName="${user.name}" userImage="${user.image!''}"/>
        <#else>
            <@template.unLoginUser/>
        </#if>
    </div>
</div>
<!-- //banner -->


<!--教师列表-->
<#if teacherList?? && (teacherList?size>0)>
<div class="portfolio">
    <div class="container">
        <h3>教师列表</h3>
        <#list teacherList as teacher>
            <div class="col-md-6 testimonials-grid edit_info">
                <div class="col-md-4 testimonials-grd-right">
                    <#if teacher.image??>
                        <img src="${teacher.image}" alt=" " class="img-responsive">
                    <#else>
                        <img src="${template.base}/images/default_user.png" alt=" " class="img-responsive">
                    </#if>
                    <div class="edit_btns text-center">
                        <a href="javascript:void(0);" class="teacher_update_btn teacher_edit_btn" data-teacher="${teacher.id}" data-school="${schoolId}">
                            <i class="fa fa-pencil fa-2"></i>编辑
                        </a>
                        <a href="javascript:void(0);" class="teacher_delete_btn" data-teacher="${teacher.id}" data-school="${schoolId}">
                            <i class="fa fa-times fa-2"></i>删除
                        </a>
                    </div>
                </div>
                <div class="col-md-8 testimonials-grd">
                    <div class="testimonials-grid1 testimonials-grid1-second">
                        <h4>${teacher.name}</h4>
                        <p>${teacher.introduce}</p>
                    </div>
                </div>
                <div class="clearfix"> </div>
            </div>
            <div class="cleanfix"></div>
        </#list>
    </div>
</div>
</#if>

<!-- //教师列表 -->

<!-- 添加教师信息-->
<div class="portfolio">
    <div class="container text-center">
        <button type="button" class="btn btn-primary teacher_edit_btn" data-teacher="0" data-school="${schoolId}">
            点击添加新教师
        </button>

        <button type="button" class="btn btn-danger edit_back" data-lesson="0" data-school="${schoolId}">
            返回
        </button>
    </div>
</div>
<!-- //添加教师信息-->

<!-- 弹出框 -->
<div class="modal fade" id="editTeacherModal" tabindex="-1" role="dialog" aria-labelledby="editTeacherModal">

</div>
<!-- //弹出框 -->


<script type="text/javascript" src="${template.base}/js/school/school_edit.js"></script>


</@template.body>