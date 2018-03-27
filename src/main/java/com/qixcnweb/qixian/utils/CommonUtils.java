package com.qixcnweb.qixian.utils;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
@Component
public class CommonUtils {

    /**
     * 判断是否是电话号码格式
     * @return
     */
    public Boolean isPhone(String str){
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    /**
     * 判断是否是电子邮箱格式
     * @return
     */
    public Boolean isEmail(String str){
        String regExp = "\\w+@(\\w+\\.){1,3}\\w+";
        Pattern p = Pattern.compile(regExp,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 将图片的坐标等参数由字符串转int (图片剪裁使用)
     * @param imagePosition
     * @return
     */
    public Integer ImagePosition2Int(String imagePosition){
        Integer position = null;
        //如果字符串中带"."
        if(imagePosition.indexOf(".")!=-1){
            String substring = imagePosition.substring(0, imagePosition.indexOf("."));
            position = Integer.parseInt(substring);
        }else{
            position =  Integer.parseInt(imagePosition);
        }

        return position<0?0:position;
    }

}
