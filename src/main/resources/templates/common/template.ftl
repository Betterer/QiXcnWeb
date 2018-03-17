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
                <title>${title}</title>

                <!-- CSS -->
                <link rel="stylesheet" href="${base}/css/bootstrap.min.css">
                <link rel="stylesheet" href="${base}/css/font-awesome.min.css">
                <link rel="stylesheet" href="${base}/css/form-elements.css">
                <link rel="stylesheet" href="${base}/css/login.css">

                <!-- Javascript -->
                <script src="${base}/js/jquery-1.11.1.min.js"></script>
                <script src="${base}/js/bootstrap.min.js"></script>
                <script src="${base}/js/jquery.backstretch.min.js"></script>
                <script src="${base}/js/retina-1.1.0.min.js"></script>
                <script src="${base}/js/login.js"></script>
                <#nested>
            </head>
        </#macro>

        <#-- body -->
        <#macro body>
            <body>
            <#nested>
            </body>
            </html>
        </#macro>


</#compress>