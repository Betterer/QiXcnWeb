<#import "common/template.ftl" as template>
<@template.head title="起线科技--注册">
	<link rel="stylesheet" href="${template.base}/css/login.css">
	<link rel="stylesheet" href="${template.base}/css/form-elements.css">
	<script src="${template.base}/js/common/jquery.validate.js"></script>
	<script src="${template.base}/js/common/jquery.backstretch.min.js"></script>
	<script src="${template.base}/js/common/retina-1.1.0.min.js"></script>
	<script src="${template.base}/js/index/login.js"></script>
</@template.head>
<@template.body>

		<!-- Top menu -->
		<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.ftl">Bootstrap Login Form Template</a>
				</div>
			</div>
		</nav>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">

                    <div class="row">
						<div class="col-sm-6 form-box col-md-offset-3">
								<div class="form-top">
									<div class="form-top-left">
										<h3>用户注册</h3>
										<p>Fill in the form below to get instant access:</p>
									</div>
									<div class="form-top-right">
										<i class="fa fa-pencil"></i>
									</div>
								</div>
								<div class="form-bottom">
									<form role="form" action="${template.base}/user_register" method="post" class="registration-form" id="register_form">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<div class="form-group">
											<label class="sr-only" for="user_account">用户账号</label>
											<input type="text" name="name" placeholder="用户账号" class="form-control" id="user_name">
										</div>
										<div class="form-group">
											<label class="sr-only" for="user_password">用户密码</label>
											<input type="password" name="password" placeholder="用户密码" class="form-control" id="user_password">
										</div>
										<div class="form-group">
											<label class="sr-only" for="user_password">确认密码</label>
											<input type="password" name="confirm_password" placeholder="确认密码" class="form-control" id="confirm_password">
										</div>
										<div class="form-group">
											<label class="sr-only" for="user_password">电话号码</label>
											<input type="text" name="phone" placeholder="电话号码" class="form-control" id="user_phone">
										</div>
										<div class="form-group">
											<label class="sr-only" for="user_password">电子邮箱</label>
											<input type="text" name="email" placeholder="电子邮箱" class="form-control" id="user_email">
										</div>
										<div class="form-group">
											<select name="gender">
												<option value="1">用户性别</option>
												<option value="1">男</option>
												<option value="0">女</option>
											</select>
										</div>
										<div class="form-group">
											<button type="submit" class="btn">点击注册!</button>
											<span style="margin-left: 15px; font-size: 14px;">已有账号?<a href="${template.base}/login">点击这里</a>,进行登录.</span>
										</div>
									</form>
								</div>
							</div>
                    </div>
                </div>
            </div>
            
        </div>
		<!-- 注册表单前端验证js -->
		<script src="${template.base}/js/index/register_validate.js"></script>
</@template.body>