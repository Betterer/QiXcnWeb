<#import "../common/template.ftl" as template>
    <@template.head title="起线科技--学校详情">
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
<div class="portfolio">
    <div class="container">
        <h3>编辑课程信息</h3>
        <div class="portfolio-grids">

        </div>
    </div>
</div>
<!-- //编辑课程表单 -->

</@template.body>