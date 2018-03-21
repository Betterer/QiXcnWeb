package com.qixcnweb.qixian.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
public class CommonUtils {

    /**
     * 判断是否是电话号码格式
     * @return
     */
    public static Boolean isPhone(String str){
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    /**
     * 判断是否是电子邮箱格式
     * @return
     */
    public static Boolean isEmail(String str){
        String regExp = "\\w+@(\\w+\\.){1,3}\\w+";
        Pattern p = Pattern.compile(regExp,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
