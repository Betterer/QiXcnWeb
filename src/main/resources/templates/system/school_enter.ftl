<#import "../common/template.ftl" as template>
<@template.head title="系统--个人设置">
<link href="${template.base}/css/system_style.css" rel="stylesheet" type="text/css" />
<link href="${template.base}/css/responsive.css" rel="stylesheet" type="text/css" />
<link href="${template.base}/css/dropzone.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${template.base}/js/system/system_nav.js"></script>
<script type="text/javascript" src="${template.base}/js/common/dropzone.js"></script>
<script type="text/javascript" src="${template.base}/js/common/jquery.validate.js"></script>
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
                    <h2><span>学校入驻</span>  信息录入.</h2>
                </header>
            </article>
        </div>
        <div class="clearfix"></div>
        <!-- 学校图片上传 -->
        <div class="contat-from-wrapper" id="crop-avatar">

            <section>
                <form action="${template.base}/school/upload_images" class="dropzone dz-clickable" id="myDropzone" enctype="multipart/form-data">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <#if school.schoolImageUrlMap??>
                        <#list school.schoolImageUrlMap?keys as key>
                            <input type="hidden" class="schoolImageUrl" data="${key}" value="${school.schoolImageUrlMap[key]}"/>
                        </#list>
                    <#else>
                        <div class="dz-message needsclick">
                            点击此处上传学校图片.<br>
                            <span class="note needsclick">(最多上传10张图片,且每张图片大小不能超过2M)</span>
                        </div>
                    </#if>
                </form>
            </section>
        </div>

        <!-- 学校基本信息录入 -->
        <!-- contat-from-wrapper -->
        <div class="contat-from-wrapper" style="margin-top: 70px;">
            <form action="${template.base}/school/enter" name="school_enter" id="school_enter" enctype="multipart/form-data" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="${school.id!''}"/>
                <div class="row sys_row">
                    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                        <input  name="name" id="name" type="text" placeholder="学校名称" value="${school.name!''}">
                    </div>
                    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                        <input  name="email" id="email" type="text"  placeholder="电子邮箱" value="${school.email!''}">
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row sys_row">
                    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                        <input  name="cellphone" id="cellphone" type="text" placeholder="座机号码(例:0735-2833564)" value="${school.cellphone!''}">
                    </div>
                    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                        <input  name="telephone" id="telephone" type="text"  placeholder="手机号码" value="${school.telephone!''}">
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row sys_row">
                    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                        <input  name="webSite" id="webSite" type="text" placeholder="学校网站(例:http://www.baidu.com)" value="${school.webSite!''}">
                    </div>
                    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                        <input  name="image" id="image" type="hidden" value="${school.image!''}" />
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row sys_row">
                    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                        <input name="identity_path" type="text" readonly placeholder="上传法人身份证" onclick="document.school_enter.identityImage.click()">
                        <input type="file" name="identityImage" id="identityImage" style="display:none" onChange="document.school_enter.identity_path.value=this.value">
                        <#if school.managerIdentityImageUrl??>
                            <img src="${school.managerIdentityImageUrl}" class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                        </#if>

                    </div>
                    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                        <input name="license_path" type="text" readonly placeholder="上传营业执照" onclick="document.school_enter.licenseImage.click()">
                        <input type="file" name="licenseImage" id="licenseImage" style="display:none" onChange="document.school_enter.license_path.value=this.value">
                        <#if school.licenseImageUrl??>
                            <img src="${school.licenseImageUrl}" class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
                        </#if>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row sys_row">
                    <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                        <input  name="address" id="address" type="text" placeholder="学校地址" value="${school.address!''}">
                    </div>
                </div>
                <div class="clearfix"></div>
                <textarea name="introduce" id="introduce" cols="" rows="" placeholder="学校简介">${school.introduce!''}</textarea>
                <div class="clearfix"></div>
                <input name="" type="submit" value="确认提交">
                <div id="simple-msg"></div>
            </form>
        </div>
        <!-- contat-from-wrapper -->
    </div>
    <script type="text/javascript" src="${template.base}/js/system/school_enter.js"></script>
</main>
<!-- main -->
<!-- footer -->
    <@template.system_footer></@template.system_footer>
</@template.body>