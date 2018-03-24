/**
 * 头部页面和左侧菜单栏所使用的js代码
 * Created by dingxiaochi on 2018/3/10.
 */
/* ------------------------  变量定义  ----------------------------------*/


/*------------------------- 方法定义 ------------------------------------*/
var menuAndNavbar =  {
    //初始化方法
    init : function () {
        $(document).ready(function () {
            //登出按钮点击
            $("#logout").click(function(){
                $("#logout_form").submit();
            });
        });
    },
};

menuAndNavbar.init();




