package com.qixcnweb.qixian.constant;

/**
 * Created by dingxiaochi on 2018/3/10.
 */
public class Constant {

    //用户状态
    public static final Integer USER_STATUS_NORMAL = 1;
    public static final Integer USER_STATUS_DELETE = 0;


    //用户类型
    public static final Integer SCHOOL_ADMIN = 3;         //学校法人
    public static final Integer SCHOOL_MANAGER = 2;       //学校管理人

    //学校状态
    public static final Integer SCHOOL_UNABLE = -1;         //审核未通过
    public static final Integer SCHOOL_WAITE = 0;           //待审核
    public static final Integer SCHOOL_ABLE = 1;            //审核通过

    //阿里云OSS图片存放路径
    public static final String USER_HEAD_IMAGE = "user_head/";      //用户头像目录 OSS服务器存放目录
    public static final String USER_INDENTITY = "user_identity/";    //用户身份证 OSS服务器存放目录
    public static final String SCHOOL_IMAGES = "school_images/";    //学校介绍 OSS服务器存放目录
    public static final String SCHOOL_LICENSE = "school_license/";  //学校(培训机构)营业执照 OSS服务器存放目录


    //阿里云OSS访问样式
    public static final String OSS_STYLE_HEAD_SMALL="style/head_small";       //用户头像(小)
    public static final String OSS_STYLE_HEAD_MID="style/head_mid";       //用户头像(中)
    public static final String OSS_STYLE_HEAD_BIG="style/head_big";       //用户头像(大)
    public static final String OSS_STYLE_DROPZONE="style/dropzone";       //dropzone预览

}
