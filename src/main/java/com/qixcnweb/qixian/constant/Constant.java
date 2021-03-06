package com.qixcnweb.qixian.constant;

/**
 * Created by dingxiaochi on 2018/3/10.
 */
public class Constant {

    //用户状态
    public static final Integer USER_STATUS_NORMAL = 1;
    public static final Integer USER_STATUS_DELETE = 0;

    //教师状态
    public static final Integer TEACHER_STATUS_NORMAL = 1;
    public static final Integer TEACHER_STATUS_DELETE = 0;

    //是否推荐教师
    public static final Integer TEACHER_RECOMMEND = 1;
    public static final Integer TEACHER_UN_RECOMMEND = 0;


    //用户类型
    public static final Integer SCHOOL_ADMIN = 3;         //学校法人
    public static final Integer SCHOOL_MANAGER = 2;       //学校管理人

    //学校状态
    public static final Integer SCHOOL_UNABLE = -1;         //审核未通过
    public static final Integer SCHOOL_WAITE = 0;           //待审核
    public static final Integer SCHOOL_ABLE = 1;            //审核通过

    //课程状态
    public static final Integer LESSON_STATUS_DELETE = 0;
    public static final Integer LESSON_STATUS_NORMAL = 1;

    //类目级别
    public static final Integer CATEGORY_LEVEL_1 = 1;
    public static final Integer CATEGORY_LEVEL_2 = 2;
    public static final Integer CATEGORY_LEVEL_3 = 3;

    //阿里云OSS图片存放路径
    public static final String USER_HEAD_IMAGE = "user_head/";      //用户头像目录 OSS服务器存放目录
    public static final String USER_INDENTITY = "user_identity/";    //用户身份证 OSS服务器存放目录
    public static final String SCHOOL_IMAGES = "school_images/";    //学校介绍 OSS服务器存放目录
    public static final String SCHOOL_LICENSE = "school_license/";  //学校(培训机构)营业执照 OSS服务器存放目录


    //阿里云OSS访问样式
    public static final String OSS_STYLE_HEAD_SMALL="style/head_small";             //用户头像(小)
    public static final String OSS_STYLE_HEAD_MID="style/head_mid";                 //用户头像(中)
    public static final String OSS_STYLE_HEAD_BIG="style/head_big";                 //用户头像(大)
    public static final String OSS_STYLE_DROPZONE="style/dropzone";                 //dropzone预览
    public static final String OSS_STYLE_SCHOOL_MAIN = "style/school_main";         //学校详情页面主图样式
    public static final String OSS_STYLE_SCHOOL_IMG_LIST = "style/school_img_list"; //学校详情页面图片列表样式
    public static final String OSS_STYLE_TEACHER_IMAGE = "style/teacher_image";     //教师图片格式 150*150
    public static final String OSS_STYLE_LESSON_EDIT = "style/lesson_edit";         //课程编辑图片格式
    public static final String OSS_STYLE_LESSON_MAIN = "style/lesson_main";       //课程展示图片格式

}
