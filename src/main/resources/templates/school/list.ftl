<#import "../common/template.ftl" as template>
<@template.head title="起线科技--学习列表">
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
			<@template.menu active="list"/>
	</div>
</div>
<!-- //banner -->

<!--搜索框-->
<!--//搜索框-->


<!-- banner-bottom -->
<div class="banner-bottom">
	<!-- 搜索框-->
	<div class="container">
		<div class="banner-bottom-grids">
            <div class="col-md-4"></div>
            <div class="col-md-3"></div>
            <div class="col-md-5 text-right search_div">
                <form id="school_search" method="get" action="${template.base}/school/list">
					<input type="hidden" name="searchBy" id="searchBy" value="${searchBy!'name'}"/>
					<input type="hidden" name="currentPage" id="current_page" value="${page.number+1}"/>
					<button type="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						<span id="search_by">
							<#if searchBy == "name">
                                学校名称
							</#if>
							<#if searchBy == "telephone">
                                手机号码
							</#if>
							<#if searchBy == "email">
                                电子邮箱
							</#if>
						</span>
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu search_dropdown_menu">
						<li><a href="javascript:void(0);" class="action_options" data="name">学校名称</a></li>
						<li><a href="javascript:void(0);" class="action_options" data="telephone">手机号码</a></li>
						<li><a href="javascript:void(0);" class="action_options" data="email">电子邮箱</a></li>
					</ul>
                    <input type="text" placeholder="Search Here..." name="condition" value="${condition!''}">
                    <a href="javascript:void(0);" class="school_search_btn"><i class="glyphicon glyphicon-search" aria-hidden="true"></i></a>
                </form>
            </div>
		</div>
	</div>
    <!--//搜索框-->

	<!-- 内容 -->
	<div class="container">
		<div class="banner-bottom-grids">
			<#if schoolList??>
			    <#list schoolList as school>
                    <div class="col-md-4 banner-bottom-grid" style="margin-top: 50px;">
						<#if school.imageUrl??>
                            <a href="${template.base}/school/index/${school.id}"><img src="${school.imageUrl}" alt=" " class="img-responsive" /></a>
						<#else>
                            <a  href="${template.base}/school/index/${school.id}"><img src="${template.base}/images/1.jpg" alt=" " class="img-responsive" /></a>
						</#if>
                        <div class="social_icons social_icons_sub">
                            <ul>
                                <li><a href="#" class="p"></a></li>
                                <li><a href="#" class="facebook"></a></li>
                                <li><a href="#" class="g"></a></li>
                            </ul>
                            <p>电话:${school.telephone}</p>
                        </div>
                        <div class="banner-bottom-grid1">
                            <h3><a href="${template.base}/school/index/${school.id}">${school.name}</a></h3>
                            <p>${school.introduce}</p>
                        </div>
                    </div>
			    </#list>
			</#if>
			<div class="clearfix"> </div>
		</div>
	</div>
	<!-- //内容-->
	<!--分页-->
	<@template.pageing formId="school_search" currentPageInputId="current_page" currentPage="${page.number+1}" rowsPerPage="${page.size}" maxRowsPerPage="${page.size}" totalPages="${page.totalPages}" totalRows="${page.totalElements}"></@template.pageing>
	<!--//分页-->
</div>
<!-- //banner-bottom -->



<script type="text/javascript" src="${template.base}/js/school/school_list.js"></script>

</@template.body>