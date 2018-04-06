<#import "../common/template.ftl" as template>
    <@template.head title="起线科技--学校详情">
    <link href="${template.base}/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${template.base}/css/chocolat.css" rel="stylesheet" type="text/css" media="all" />
    <script type="text/javascript" src="${template.base}/js/index/move-top.js"></script>
    <script type="text/javascript" src="${template.base}/js/index/easing.js"></script>
    <script type="text/javascript" src="${template.base}/js/school/jquery.chocolat.js"></script>
    <script type="text/javascript" src="${template.base}/js/school/jquery.wmuSlider.js"></script>
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
            <@template.menu_school/>
    </div>
</div>
<!-- //banner -->

<#if school?? && user?? && (school.user.id == user.id)>
<div class="about">
    <div class="container">
        <div class="about-grids">
            <div class="col-md-6 text-center">
                <a href="${template.base}/school/edit_lesson_page/${school.id}" class="school_edit_a"><i class="glyphicon glyphicon-book" aria-hidden="true"></i>&nbsp;&nbsp;编辑课程信息</a>
            </div>
            <div class="col-md-6 text-center">
                <a href="${template.base}/school/edit_teacher_page/${school.id}" class="school_edit_a"><i class="glyphicon glyphicon-user" aria-hidden="true"></i>&nbsp;&nbsp;编辑教师信息</a>
            </div>
        </div>
    </div>
</div>
</#if>

<!-- 学校简介 -->
<div class="single">
    <div class="container">
        <div class="single-page-artical">
            <div class="artical-content">
                <h3>${school.name}</h3>
                <#if schoolMainImg??>
                    <img src="${schoolMainImg}" alt="" class="img-responsive">
                <#else>
                    <img src="${template.base}/images/school_main_default.jpg" alt="" class="img-responsive">
                </#if>
                <p>${school.introduce}</p>
            </div>
            <div class="artical-links">
                <ul>
                    <li><i class="fa fa-wechat fa-2"></i>${school.wechat!''}</li>
                    <li><i class="fa fa-qq fa-2"></i> ${school.QQ!''}</a></li>
                    <li><i class="fa fa-envelope fa-2"></i>${school.email!''}</a></li>
                    <li><i class="fa fa-phone-square fa-2"></i>${school.telephone!''}</a></li>
                    <li><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>permalink</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- //学校简介 -->

<!-- 学校图片 -->
<#if (schoolImgMap??) && (schoolImgMap?size > 0) >
<div class="blog" id="environment">
    <div class="container">
        <h3>校园环境</h3>
        <div class="portfolio-grids">
            <div class="sap_tabs">
                <div class="tab-1 resp-tab-content">
                    <div class="main">
                        <#list schoolImgMap?keys as key>
                            <div class="view effect">
                                <div class="img-top">
                                    <a href="${schoolImgMap[key]}" rel="title" class="b-link-stripe b-animate-go  thickbox">
                                        <img src="${schoolImgMap[key]}" class="img-responsive" alt="" />
                                        <div class="mask"></div>
                                        <div class="content">
                                            <span class="info" title="Full Image"> </span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</#if>
<!-- //学校图片 -->

<!-- 课程相关 -->
<#if (school.lessonList??) && (school.lessonList?size > 0)>
<div class="blog"  id="lessons">
    <div class="container">
        <h3>开设课程</h3>
        <div class="blog-grids">
            <#list school.lessonList as lesson>
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
                    <div class="more m1 edit_btns">
                        <a class="btn effect6" href="single.html">查看详情</a>
                    </div>
                </div>
            </#list>

        </div>

    </div>
</div>
</#if>

<!-- //课程相关 -->

<!-- 老师相关 -->
<#if (school.teacherList??) && (school.teacherList?size>0)>
<div class="blog" id="teachers">
        <div class="container">
            <h3>师资力量</h3>
            <#list school.teacherList as teacher>
                <div class="col-md-6 testimonials-grid edit_info">
                    <div class="col-md-4 testimonials-grd-right">
                        <#if teacher.image??>
                            <img src="${teacher.imageUrl}" alt=" " class="img-responsive">
                        <#else>
                            <img src="${template.base}/images/default_user.png" alt=" " class="img-responsive">
                        </#if>
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
<!-- //老师相关 -->

<!-- 联系我们 -->
<div class="contact" id="contact">
    <div class="container">
        <h3>联系我们</h3>
        <div class="col-md-3 contact-grid">
            <i class="glyphicon glyphicon-home" aria-hidden="true"></i>
            <h4>Address</h4>
            <p>${school.address}</p>
        </div>
        <div class="col-md-3 contact-grid">
            <i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>
            <h4>Mail</h4>
            <a href="mailto:info@example.com">${school.email}</a>
        </div>
        <div class="col-md-3 contact-grid">
            <i class="glyphicon glyphicon-phone-alt" aria-hidden="true"></i>
            <h4>Phone</h4>
            <p>${school.cellphone}</p>
        </div>
        <div class="col-md-3 contact-grid">
            <i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>
            <h4>Phone</h4>
            <p>${school.telephone}</p>
        </div>
        <div class="clearfix"> </div>
        <!-- footer -->
        <div class="footer-copy">
        </div>
    </div>
</div>
<!-- //联系我们 -->
</@template.body>