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
            <@template.menu/>
    </div>
</div>
<!-- //banner -->

<#if school?? && user?? && (school.user.id == user.id)>
<div class="about">
    <div class="container">
        <div class="about-grids">
            <div class="col-md-6 text-center">
                <a href="${template.base}/school/edit_lesson_page/${school.id}" target="_blank" class="school_edit_a"><i class="glyphicon glyphicon-book" aria-hidden="true"></i>&nbsp;&nbsp;编辑课程信息</a>
            </div>
            <div class="col-md-6 text-center">
                <a href="${template.base}/school/edit_teacher_page/${school.id}" target="_blank" class="school_edit_a"><i class="glyphicon glyphicon-user" aria-hidden="true"></i>&nbsp;&nbsp;编辑教师信息</a>
            </div>
        </div>
    </div>
</div>
</#if>


<!-- 学校简介 -->
<div class="about">
    <div class="container">
        <div class="about-grids">
            <div class="col-md-6 about-grid-left">
                <div class="grid">
                    <figure class="effect-julia">
                        <#if schoolMainImg??>
                            <img src="${schoolMainImg}" alt="" class="img-responsive">
                        <#else>
                            <img src="${template.base}/images/school_main_default.jpg" alt="" class="img-responsive">
                        </#if>
                        <figcaption>
                            <h2><span>${school.name}</span></h2>
                            <div>
                                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 更优质的教学. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 更近的距离. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 更优惠的价格. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                            </div>
                            <a href="#">View more</a>
                        </figcaption>
                    </figure>
                </div>
            </div>
            <div class="col-md-6 about-grid-right">
                <h3>${school.name}</h3>
                <p class="great">让每一个学生都得到发展,让每一个家长都能够满意.</p>
                <p>${school.introduce}</p>
                <div class="social_icons social_icons1">
                    <ul>
                        <li><a href="#" class="p"></a></li>
                        <li><a href="#" class="facebook"></a></li>
                        <li><a href="#" class="g"></a></li>
                        <li><a href="#" class="instagram"></a></li>
                    </ul>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!-- //学校简介 -->

<!-- 学校图片 -->
<#if (schoolImgMap??) && (schoolImgMap?size > 0) >
<div class="portfolio">
    <div class="container">
        <h3>学校环境</h3>
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
<div class="blog">
    <div class="container">
        <h3>开设课程</h3>
        <div class="blog-grids">
            <div class="col-md-6 blog-grid">
                <div class="col-xs-4 blog-grid-left">
                    <a href="single.html"><img src="images/16.jpg" alt=" " class="img-responsive" /></a>
                </div>
                <div class="col-xs-8 blog-grid-right">
                    <a href="single.html">sint occaecat cupidatat non proident</a>
                    <h4>27 November 2015</h4>
                    <p>Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur.</p>
                </div>
                <div class="clearfix"> </div>
                <div class="more m1">
                    <a class="btn effect6" href="single.html">Learn More</a>
                </div>
            </div>
            <div class="col-md-6 blog-grid">
                <div class="col-xs-4 blog-grid-left">
                    <a href="single.html"><img src="images/17.jpg" alt=" " class="img-responsive" /></a>
                </div>
                <div class="col-xs-8 blog-grid-right">
                    <a href="single.html">sint occaecat cupidatat non proident</a>
                    <h4>28 November 2015</h4>
                    <p>Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur.</p>
                </div>
                <div class="clearfix"> </div>
                <div class="more m1">
                    <a class="btn effect6" href="single.html">Learn More</a>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="blog-grids">
            <div class="col-md-6 blog-grid">
                <div class="col-xs-4 blog-grid-left">
                    <a href="single.html"><img src="images/18.jpg" alt=" " class="img-responsive" /></a>
                </div>
                <div class="col-xs-8 blog-grid-right">
                    <a href="single.html">sint occaecat cupidatat non proident</a>
                    <h4>27 November 2015</h4>
                    <p>Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur.</p>
                </div>
                <div class="clearfix"> </div>
                <div class="more m1">
                    <a class="btn effect6" href="single.html">Learn More</a>
                </div>
            </div>
            <div class="col-md-6 blog-grid">
                <div class="col-xs-4 blog-grid-left">
                    <a href="single.html"><img src="images/19.jpg" alt=" " class="img-responsive" /></a>
                </div>
                <div class="col-xs-8 blog-grid-right">
                    <a href="single.html">sint occaecat cupidatat non proident</a>
                    <h4>28 November 2015</h4>
                    <p>Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur.</p>
                </div>
                <div class="clearfix"> </div>
                <div class="more m1">
                    <a class="btn effect6" href="single.html">Learn More</a>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="blog-grids">
            <div class="col-md-6 blog-grid">
                <div class="col-xs-4 blog-grid-left">
                    <a href="single.html"><img src="images/16.jpg" alt=" " class="img-responsive" /></a>
                </div>
                <div class="col-xs-8 blog-grid-right">
                    <a href="single.html">sint occaecat cupidatat non proident</a>
                    <h4>27 November 2015</h4>
                    <p>Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur.</p>
                </div>
                <div class="clearfix"> </div>
                <div class="more m1">
                    <a class="btn effect6" href="single.html">Learn More</a>
                </div>
            </div>
            <div class="col-md-6 blog-grid">
                <div class="col-xs-4 blog-grid-left">
                    <a href="single.html"><img src="images/17.jpg" alt=" " class="img-responsive" /></a>
                </div>
                <div class="col-xs-8 blog-grid-right">
                    <a href="single.html">sint occaecat cupidatat non proident</a>
                    <h4>28 November 2015</h4>
                    <p>Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur.</p>
                </div>
                <div class="clearfix"> </div>
                <div class="more m1">
                    <a class="btn effect6" href="single.html">Learn More</a>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
</#if>

<!-- //课程相关 -->

<!-- 老师相关 -->
<#if (school.teacherList??) && (school.teacherList?size>0)>
<div class="about testimonials">
    <div class="container">
        <h3>优秀教师</h3>
        <div class="my-trip">
            <div class="wmuSlider example1" style="height: 284px;">
                <div class="wmuSliderWrapper">
                    <#list school.teacherList as teacher>
                        <article>
                            <div class="banner-wrap">
                                <div class="my-trip-grids">
                                    <div class="col-xs-4 my-trip-left">
                                        <h3>About <span>My</span> Trip. (Alaska)</h3>
                                    </div>
                                    <div class="col-xs-8 my-trip-right">
                                        <div class="my-trip-rightl">
                                            <#if teacher.imageUrl?? && teacher.imageUrl!=''>
                                                <img src="${teacher.imageUrl}" alt=" " class="img-responsive">
                                            <#else>
                                                <img src="${template.base}/images/default_user.png" alt=" " class="img-responsive">
                                            </#if>
                                        </div>
                                        <div class="my-trip-rightr">
                                            <p>${teacher.introduce}
                                                <span>${teacher.name}</span></p>
                                        </div>
                                        <div class="clearfix"> </div>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                            </div>
                        </article>
                    </#list>
                </div>
                <ul class="wmuSliderPagination"><li><a href="#" class="">0</a></li><li><a href="#" class="">1</a></li><li><a href="#" class="wmuActive">2</a></li></ul></div>
            <script>
                $('.example1').wmuSlider();
            </script>
        </div>
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