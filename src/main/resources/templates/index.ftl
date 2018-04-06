<#import "common/template.ftl" as template>
<@template.head title="起线科技--首页">
	<link href="${template.base}/css/style.css" rel="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" src="${template.base}/js/index/move-top.js"></script>
	<script type="text/javascript" src="${template.base}/js/index/easing.js"></script>
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); }
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
	<div class="banner">
		<div class="container">
			<#if currentUser??>
				<@template.loginUser userId="${currentUser.id}" userName="${currentUser.name}" userImage="${currentUser.image!''}"/>
			<#else>
				<@template.unLoginUser/>
			</#if>
			<@template.menu/>
			<@template.banner/>
		</div>
	</div>
	<!-- //banner -->
	<!-- banner-bottom-position -->
	<div class="banner-bottom-position">
		<div class="col-md-6 banner-bottom-position-grid">
			<div class="banner-bottom-position-grid-left">
				<img src="${template.base}/images/feature1.jpg" alt=" " class="img-responsive" />
				<div class="banner-bottom-position-grid-left-pos">
					<h4>产品特点一,查找距离最近的培训学校</h4>
				</div>
			</div>
		</div>
		<div class="col-md-6 banner-bottom-position-gridr">
			<h3>01.</h3>
			<p>下面的图片可以展示距离最近的培训学校,undertakes laborious physical
				exercise, except to obtain some advantage from it? But who has any right to
				find fault with a man who chooses to enjoy a pleasure.</p>
		</div>
		<div class="clearfix"> </div>
	</div>
	<!-- //banner-bottom-position -->
	<!-- banner-bottom -->
	<div class="banner-bottom">
		<div class="container">
			<div class="banner-bottom-grids">
				<div class="col-md-4 banner-bottom-grid">
					<img src="images/1.jpg" alt=" " class="img-responsive" />
					<div class="social_icons social_icons_sub">
						<ul>
							<li><a href="#" class="p"></a></li>
							<li><a href="#" class="facebook"></a></li>
							<li><a href="#" class="g"></a></li>
						</ul>
						<p>Voluptates repudiandae sint et
							molestiae non recusandae.</p>
					</div>
					<div class="banner-bottom-grid1">
						<h3>Mountains In Alaska</h3>
						<p>Temporibus autem quibusdam et aut officiis debitis aut rerum
							necessitatibus saepe eveniet ut et voluptates repudiandae sint et
							molestiae non recusandae.</p>
					</div>
				</div>
				<div class="col-md-4 banner-bottom-grid">
					<div class="banner-bottom-grid1 banner-bottom-grid1-sub">
						<h3>Salmon Fish In Alaska</h3>
						<p>Temporibus autem quibusdam et aut officiis debitis aut rerum
							necessitatibus saepe eveniet ut et voluptates repudiandae sint et
							molestiae non recusandae.</p>
					</div>
					<img src="images/2.jpg" alt=" " class="img-responsive" />
					<div class="social_icons social_icons_sub1">
						<ul>
							<li><a href="#" class="p"></a></li>
							<li><a href="#" class="facebook"></a></li>
							<li><a href="#" class="g"></a></li>
						</ul>
						<p>Voluptates repudiandae sint et
							molestiae non recusandae.</p>
					</div>
				</div>
				<div class="col-md-4 banner-bottom-grid">
					<div class="banner-bottom-grid-box">
						<div class="banner-bottom-grid-box1">
							<h1>dolorem eum fugiat quo</h1>
							<p>Neque porro quisquam est, qui dolorem ipsum quia.</p>
						</div>
					</div>
					<div class="banner-bottom-grid-box banner-bottom-grid-boxs">
						<div class="banner-bottom-grid-box1">
							<h2>dolorem eum fugiat quo</h2>
							<p>Neque porro quisquam est, qui dolorem ipsum quia qui dolorem eum
								fugiat quo voluptas nulla pariatu.</p>
						</div>
					</div>
					<div class="banner-bottom-grid-box banner-bottom-grid-boxs1">
						<div class="banner-bottom-grid-box1 banner-bottom-grid-box1-sub">
							<h2>porro quisquam est fugiat quo ipsum quia</h2>
							<p>Neque porro quisquam est, qui dolorem ipsum quia.</p>
						</div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!-- //banner-bottom -->
	<!-- banner-bottom1 -->
	<div class="banner-bottom1">
		<div class="container">
			<div class="banner-bottom1-grids">
				<div class="col-md-8 banner-bottom1-grid-left">
					<h3>vel illum qui dolorem eum fugiat quo voluptas nulla</h3>
					<p>Quis autem vel eum iure reprehenderit qui in ea voluptate velit
						esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat
						quo voluptas nulla pariatur</p>
				</div>
				<div class="col-md-4 banner-bottom1-grid-right">
					<div class="more">
						<a class="btn effect6" href="single.html">Learn More</a>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!-- //banner-bottom1 -->
	<!-- slider -->
	<div class="slider">
		<div class="container">
			<div class="sliderfig">
				<ul id="flexiselDemo1">
					<li>
						<div class="sliderfig-grids">
							<div class="sliderfig-grid">
								<img src="images/5.jpg" alt=" " class="img-responsive" />
							</div>
						</div>
					</li>
					<li>
						<div class="sliderfig-grids">
							<div class="sliderfig-grid">
								<img src="images/6.jpg" alt=" " class="img-responsive" />
							</div>
						</div>
					</li>
					<li>
						<div class="sliderfig-grids">
							<div class="sliderfig-grid">
								<img src="images/8.jpg" alt=" " class="img-responsive" />
							</div>
						</div>
					</li>
					<li>
						<div class="sliderfig-grids">
							<div class="sliderfig-grid">
								<img src="images/7.jpg" alt=" " class="img-responsive" />
							</div>
						</div>
					</li>
				</ul>
				<script type="text/javascript">
					$(window).load(function() {
						$("#flexiselDemo1").flexisel({
							visibleItems: 4,
							animationSpeed: 1000,
							autoPlay: true,
							autoPlaySpeed: 3000,
							pauseOnHover: true,
							enableResponsiveBreakpoints: true,
							responsiveBreakpoints: {
								portrait: {
									changePoint:480,
									visibleItems: 1
								},
								landscape: {
									changePoint:640,
									visibleItems:3
								},
								tablet: {
									changePoint:768,
									visibleItems: 3
								}
							}
						});

					});
				</script>
				<script type="text/javascript" src="${template.base}/js/index/jquery.flexisel.js"></script>
			</div>
		</div>
	</div>
	<!-- //slider -->
	<!-- banner-bottom-position -->
	<div class="banner-bottom-position">
		<div class="col-md-7 banner-bottom-position-gridr right">
			<h3>02.</h3>
			<p>下面的图片可以展示口碑好或者价格有优惠的培训学校,us ever undertakes laborious physical
				exercise, except to obtain some advantage from it? But who has any right to
				find fault with a man who chooses to enjoy a pleasure.</p>
		</div>
		<div class="col-md-5 banner-bottom-position-grid">
			<div class="banner-bottom-position-grid-left Voluptates">
				<img src="${template.base}/images/feature2.jpg" alt=" " class="img-responsive" />
				<div class="banner-bottom-position-grid-left-pos repudiandae">
					<h4>产品特点二,查找口碑好的或者有优惠的培训学校</h4>
				</div>
			</div>
		</div>
		<div class="clearfix"> </div>
	</div>
	<!-- //banner-bottom-position -->
	<!-- contact -->
	<div class="contact" id="contact">
		<div class="container">
			<h3>Contact Us</h3>
			<div class="col-md-4 contact-grid">
				<i class="glyphicon glyphicon-home" aria-hidden="true"></i>
				<h4>Address</h4>
				<p>JI.Paulnadwam 2nd D.No:23-50-903.<span>United States</span></p>
			</div>
			<div class="col-md-4 contact-grid">
				<i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>
				<h4>Mail</h4>
				<a href="mailto:info@example.com">info@example.com</a>
			</div>
			<div class="col-md-4 contact-grid">
				<i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>
				<h4>Phone</h4>
				<p>+020 456 9696</p>
			</div>
			<div class="clearfix"> </div>
			<!-- footer -->
			<div class="footer-copy">
			</div>
		</div>
	</div>
	<!-- //contact -->
</@template.body>