<#import "../common/template.ftl" as template>
<@template.head title="系统--个人设置">
<link href="${template.base}/css/system_style.css" rel="stylesheet" type="text/css" />
<link href="${template.base}/css/responsive.css" rel="stylesheet" type="text/css" />
<link href="${template.base}/css/cropper.min.css" rel="stylesheet" type="text/css" />
<link href="${template.base}/css/upload_plugin.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="${template.base}/js/system/system_nav.js"></script>
<script type="text/javascript" src="${template.base}/js/common/cropper.min.js"></script>
<script type="text/javascript" src="${template.base}/js/system/upload_plugin.js"></script>
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
                        <h2><span>个人设置</span>  修改头像.</h2>
                    </header>
                </article>
            </div>

            <div class="clearfix"></div>

            <div class="contat-from-wrapper" id="crop-avatar">
                <!-- Current avatar -->
                <div class="avatar-view" title="Change the avatar">
                    <#if user.image??>
                        <img src="${template.base}/images/picture.jpg"/>
                    <#else>
                        <img src="${template.base}/images/default_user.png"/>
                    </#if>
                </div>

                <!-- Cropping modal -->
                <div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <form class="avatar-form" action="/user/update_user_head" enctype="multipart/form-data" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <div class="modal-header">
                                    <button class="close" data-dismiss="modal" type="button">&times;</button>
                                    <h4 class="modal-title" id="avatar-modal-label">更换头像</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="avatar-body">

                                        <!-- Upload image and data -->
                                        <div class="avatar-upload">
                                            <input class="avatar-src" name="src" type="hidden">
                                            <input class="avatar-data" name="data" type="hidden">
                                            <label for="avatarInput">本地上传</label>
                                            <input class="avatar-input" id="avatarInput" name="file" type="file">
                                        </div>

                                        <!-- Crop and preview -->
                                        <div class="row">
                                            <div class="col-md-9">
                                                <div class="avatar-wrapper"></div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="avatar-preview preview-lg"></div>
                                                <div class="avatar-preview preview-md"></div>
                                                <div class="avatar-preview preview-sm"></div>
                                            </div>
                                        </div>

                                        <div class="row avatar-btns">
                                            <div class="col-md-9">
                                            </div>
                                            <div class="col-md-3">
                                                <button class="btn btn-primary btn-block avatar-save" type="submit">完成</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- <div class="modal-footer">
                                  <button class="btn btn-default" data-dismiss="modal" type="button">Close</button>
                                </div> -->
                            </form>
                        </div>
                    </div>
                </div><!-- /.modal -->

                <!-- Loading state -->
                <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
            </div>







            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 ">
                <article role="pge-title-content" class="contact-header">
                    <header>
                        <h2>修改密码.</h2>
                    </header>
                </article>
            </div>
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