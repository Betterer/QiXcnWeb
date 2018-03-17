<#import "common/template.ftl" as template>
<@template.head title="起线科技--登录">
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
                    	<div class="col-sm-6 phone">
                    		<img src="../static/images/iphone.png" alt="">
                    	</div>
                        <div class="col-sm-5 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>用户登录</h3>
                            		<p>Fill in the form below to get instant access:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-pencil"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="" method="post" class="registration-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="user_account">用户账号</label>
			                        	<input type="text" name="account" placeholder="用户账号" class="form-first-name form-control" id="user_account">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="user_password">用户密码</label>
			                        	<input type="text" name="password" placeholder="用户密码" class="form-last-name form-control" id="user_password">
			                        </div>
									<div class="form-group">
										<input type="checkbox" name="remember_me"/> 记住我
									</div>
			                        <button type="submit" class="btn">点击登录!</button>
									<span style="margin-left: 15px; font-size: 14px;">没有账号?<a href="${template.base}/register">点击这里</a>,进行注册.</span>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>


</@template.body>