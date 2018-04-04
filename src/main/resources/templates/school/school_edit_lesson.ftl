<#import "../common/template.ftl" as template>
    <@template.head title="起线科技--编辑课程">
    <link href="${template.base}/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${template.base}/css/chocolat.css" rel="stylesheet" type="text/css" media="all" />
    <script type="text/javascript" src="${template.base}/js/index/move-top.js"></script>
    <script type="text/javascript" src="${template.base}/js/index/easing.js"></script>
    <script type="text/javascript" src="${template.base}/js/school/jquery.chocolat.js"></script>
    <script type="text/javascript" charset="utf-8">
        $(function() {
            $('.img-top a').Chocolat();
        });
    </script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
            });
        });
    </script>
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

<!-- 编辑课程表单 -->
<#if lessonList??>
<div class="blog">
    <div class="container">
        <h3>课程列表</h3>
        <div class="blog-grids">
            <#list lessonList as lesson>
                <div class="col-md-6 blog-grid" style="margin-top: 80px;">
                    <div class="col-xs-4 blog-grid-left">
                        <#if lesson.imageUrl??>
                            <a href="javascript:void(0);"><img src="${lesson.imageUrl}" alt=" " class="img-responsive"></a>
                        <#else>
                            <a href="javascript:void(0);"><img src="${template.base}/images/school_main_default.jpg" alt=" " class="img-responsive"></a>
                        </#if>
                    </div>
                    <div class="col-xs-8 blog-grid-right">
                        <a href="javascript:void(0);">${lesson.name}</a>
                        <h4>${lesson.price}</h4>
                        <p>${lesson.introduce}</p>
                    </div>
                    <div class="clearfix"> </div>
                    <div class="more m1 edit_btns" style="margin-top: 0px;">
                    <a href="javascript:void(0);" class="teacher_update_btn teacher_edit_btn" data-lesson="${lesson.id}" data-school="${schoolId}">
                    <i class="glyphicon glyphicon-pencil"></i>编辑
                    </a>
                    <a href="javascript:void(0);" class="teacher_delete_btn" data-lesson="${lesson.id}" data-school="${schoolId}">
                    <i class="glyphicon glyphicon-remove"></i>删除
                    </a>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>
</#if>

<!-- //编辑课程表单 -->

<!-- 添加课程信息 -->
<div class="portfolio">
    <div class="container text-center">
        <button type="button" class="btn btn-primary lesson_edit_btn" data-lesson="0" data-school="${schoolId}">
            点击添加新课程
        </button>
    </div>
</div>
<!-- //添加课程信息-->

<!-- 弹出框 -->
<div class="modal fade" id="editLessonModal" tabindex="-1" role="dialog" aria-labelledby="editLessonModal">

</div>
<!-- //弹出框 -->

<script type="text/javascript" src="${template.base}/js/school/school_edit.js"></script>

</@template.body>