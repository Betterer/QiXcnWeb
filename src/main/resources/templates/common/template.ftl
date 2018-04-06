<#compress>
    <!-- 指定项目路径,方便静态文件读取-->
    <#assign base = "${springMacroRequestContext.contextPath}" />
    <!-- 引入Spring Security 标签,控制显示-->
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
    <#-- 头部信息 -->
        <#macro head title>
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
                <title>${title}</title>

                <!-- CSS -->
                <link rel="stylesheet" href="${base}/css/bootstrap.min.css">
                <link rel="stylesheet" href="${base}/css/font-awesome.min.css">

                <!-- Javascript -->
                <script src="${base}/js/common/jquery-1.11.1.min.js"></script>
                <script src="${base}/js/common/bootstrap.min.js"></script>
                <script src="${base}/js/common/menuAndNavbar.js"></script>
                <#nested>
            </head>
        </#macro>

        <#-- body -->
        <#macro body>
            <body>
            <#nested>
            <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 0;"></span>To Top</a>
            <!-- here stars scrolling icon -->
            <script type="text/javascript">
                $(document).ready(function() {

                    var defaults = {
                        containerID: 'toTop', // fading element id
                        containerHoverID: 'toTopHover', // fading element hover id
                        scrollSpeed: 1200,
                        easingType: 'linear'
                    };


                    $().UItoTop({ easingType: 'easeOutQuart' });

                });
            </script>
            <!-- //here ends scrolling icon -->
            </body>
            </html>
        </#macro>

        <!-- 用户头像 -->
        <#macro loginUser userId userName='游客' messageCount=12 userImage=''>
            <div class="banner_top">
                <div class="banner_top_left">
                    <div class="dropdown">
                        <a href="javascript:void(0);" class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <#if userImage != ''>
                                <img class="user_head_mid" src="${userImage}"/>
                            <#else>
                                <img class="user_head_mid" src="${base}/images/default_user.png"/>
                            </#if>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="${base}/system_message">系统消息<span class="badge">${messageCount}</span></a></li>
                            <li><a href="${base}/person_setting">个人设置</a></li>
                            <li><a href="${base}/school_enter">机构入驻</a></li>
                            <li role="separator" class="divider"></li>
                            <li>
                                <a href="javascript:void(0);" id="logout">登出</a>
                                <form id="logout_form" action="/logout" method="post" style="display: none;">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                            </li>
                        </ul>
                        <span style="color: white;">欢迎登录,${userName}.</span>
                    </div>
                </div>
                <div class="clearfix"> </div>
            </div>
        </#macro>

        <!-- 用户头像 未登录-->
        <#macro unLoginUser>
            <div class="banner_top">
                <div class="banner_top_left">
                    <img class="user_head_mid" src="${base}/images/default_user.png"/>
                    <span style="color: white;">你好游客, <a href="${base}/login">登录</a>或者<a href="${base}/register">注册</a>.</span>
                </div>
                <div class="clearfix"> </div>
            </div>
        </#macro>


        <!--菜单栏 首页-->
        <#macro menu>
            <nav class="navbar navbar-default">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav cl-effect-14">
                        <li><a href="${base}/index">首页</a></li>
                        <li><a href="${base}/school/list">学校列表</a></li>
                        <li><a href="features.html">机构列表(地图)</a></li>
                        <li><a href="portfolio.html">关于我们</a></li>
                        <li><a href="codes.html">联系我们</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </nav>
        </#macro>

        <!--菜单栏 学校-->
        <#macro menu_school>
        <nav class="navbar navbar-default">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav cl-effect-14">
                    <li><a href="${base}/index">返回首页</a></li>
                    <li><a href="#environment" class="scroll">校园环境</a></li>
                    <li><a href="#lessons" class="scroll">开设课程</a></li>
                    <li><a href="#teachers" class="scroll">师资力量</a></li>
                    <li><a href="#contact" class="scroll">联系我们</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
        </#macro>

        <#macro banner>
            <div class="logo">
                <a href="index.html">Qi Xian<span>为孩子画出更好的起跑线</span></a>
            </div>
            <div class="dummy_text">
                <p>Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur,
                    adipisci velit, sed quia non numquam eius modi.</p>
            </div>
        </#macro>

        <!-- 系统页面头部导航-->
        <#macro system_header>
            <header role="header">
                <div class="container">
                    <!-- logo -->
                    <h1>
                        <a href="${base}/index" title="avana LLC"><img src="${base}/images/logo.png" title="avana LLC" alt="avana LLC"/></a>
                    </h1>
                    <!-- logo -->
                    <!-- nav -->
                    <nav role="header-nav" class="navy">
                        <ul>
                            <li><a href="${base}/system_message" title="Work">系统消息</a></li>
                            <li><a href="${base}/person_setting" title="About">个人设置</a></li>
                            <li><a href="${base}/school_enter" title="Blog">学校入驻</a></li>
                        </ul>
                    </nav>
                    <!-- nav -->
                </div>
            </header>
        </#macro>

        <!--系统页面底部-->
        <#macro system_footer>
            <footer role="footer">
            <!-- logo -->
            <h1>
                <a href="${base}/index" title="avana LLC"><img src="${base}/images/logo.png" title="avana LLC" alt="avana LLC"/></a>
            </h1>
            <!-- logo -->
            <!-- nav -->
            <nav role="footer-nav">
                <ul>
                    <li><a href="${base}/system_message" title="Work">系统消息</a></li>
                    <li><a href="${base}/person_setting" title="About">个人设置</a></li>
                    <li><a href="${base}/school_enter" title="Blog">学校入驻</a></li>
                </ul>
            </nav>
            <!-- nav -->
        </footer>
        </#macro>

</#compress>