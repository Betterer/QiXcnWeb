<#import "../common/template.ftl" as template>
    <@template.head title="起线科技--学校详情">
    <link href="${template.base}/css/style.css" rel="stylesheet" type="text/css" media="all" />
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

<!-- 编辑教师表单 -->
<div class="portfolio">
    <div class="container">
        <h3>编辑教师信息</h3>
        <div class="portfolio-grids">
            <form action="${template.base}/edit_teacher" method="post" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="school_edit">
                    <div class="col-md-5 text-right">
                        <label for="name">教师名称:</label>
                    </div>
                    <div class="col-md-7">
                        <input type="text" name="name" id="name"/>
                    </div>
                    <div class="clearfix"></div>
                </div>


                <div class="school_edit">
                    <div class="col-md-5 text-right">
                        <label for="introduce" style="font-size: 2em; color: #9d9d9d; line-height: 23px;">教师简介:</label>
                    </div>
                    <div class="col-md-7">
                        <textarea name="introduce" id="introduce"></textarea>
                    </div>
                    <div class="clearfix"></div>
                </div>

                <div class="school_edit">
                    <div class="col-md-5 text-right">
                        <label style="font-size: 2em; color: #9d9d9d; line-height: 23px;">是否推荐:</label>
                    </div>
                    <div class="col-md-7">
                        <div class="school_edit_radio">
                            <input type="radio" name="recommend" value="1">是
                        </div>
                        <div class="school_edit_radio">
                            <input type="radio" name="recommend" value="0">否
                        </div>
                        <div class="school_edit_radio" style="color: #9d9d9d; font-size: 14px;">
                           (此选项将决定该教师是否在学校页面展示)
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>

                <div class="school_edit">
                    <div class="col-md-6 text-right">
                        <a href="javascript:void(0);" class="confirm_preview" id="confirm_preview"><i class="glyphicon glyphicon-open"></i>提交</a>
                    </div>
                    <div class="col-md-6">
                        <a href="javascript:void(0);" class="confirm_add" id="confirm_add" style="margin-left: 30px;"><i class="glyphicon glyphicon-plus"></i>提交并继续新增</a>
                    </div>
                    <div class="clearfix"></div>
                </div>

            </form>
        </div>
    </div>
</div>
<!-- //编辑教师表单 -->

</@template.body>