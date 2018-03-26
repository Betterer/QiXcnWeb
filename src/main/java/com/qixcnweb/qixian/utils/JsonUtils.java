package com.qixcnweb.qixian.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Json工具类
 * Created by dingxiaochi on 2018/3/27.
 */
@Component
public class JsonUtils {

    /**
     * 将Json字符串转换成Map
     * @param jsonStr
     * @return
     */
    public Map<String,Object> json2Map(String jsonStr){
        Map jsonMap = (Map) JSON.parse(jsonStr);
        return jsonMap;
    }
}
