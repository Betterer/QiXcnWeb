<#import "../common/template.ftl" as template>
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
	<div class="banner_child">
		<div class="container">
			<#if user??>
				<@template.loginUser userId="${user.id}" userName="${user.name}" userImage="${user.image!''}"/>
			<#else>
				<@template.unLoginUser/>
			</#if>
				<@template.menu/>
		</div>
	</div>
	<!-- //banner -->
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
</@template.body>