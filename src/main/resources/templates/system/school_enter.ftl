<#import "../common/template.ftl" as template>
<@template.head title="系统--学校入驻">
<link href="${template.base}/css/system_style.css" rel="stylesheet" type="text/css" />
<link href="${template.base}/css/responsive.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${template.base}/js/system/system_nav.js"></script>
</@template.head>

<@template.body>
    <!-- header -->
    <@template.system_header></@template.system_header>
    <!-- header -->
    <!-- main -->
    <main role="main-inner-wrapper" class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 ">
                <article role="pge-title-content" class="contact-header">
                    <header>
                        <h2><span>学校入驻</span>  录入学校信息.</h2>
                    </header>
                </article>
            </div>
            <!-- map -->
            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                <div class="demo-wrapper">
                    <div id="surabaya"></div>
                </div>
            </div>
            <!-- map -->
            <div class="clearfix"></div>
            <!-- contat-from-wrapper -->
            <div class="contat-from-wrapper">
                <div id="message"></div>
                <form method="post" action="" name="cform" id="cform">
                    <div class="row">
                        <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                            <input  name="name" id="name" type="text" placeholder="Whats your name">
                        </div>
                        <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                            <input  name="email" id="email" type="email"  placeholder="Whats your email">
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <textarea name="comments" id="comments" cols="" rows="" placeholder="Whats in your mind"></textarea>
                    <div class="clearfix"></div>
                    <input name="" type="submit" value="Send email">
                    <div id="simple-msg"></div>
                </form>
            </div>
            <!-- contat-from-wrapper -->
        </div>
    </main>
    <!-- main -->
    <!-- footer -->
    <@template.system_footer></@template.system_footer>
</@template.body>