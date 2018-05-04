package com.qixcnweb.qixian.controller;

import org.apache.solr.client.solrj.response.RangeFacet;
import org.springframework.beans.factory.annotation.Autowired;

import com.qixcnweb.qixian.core.JacksonUtil;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/wx")
public class WxAuthController {

    /**
     * 账号登录
     *
     * @param body 请求内容，{ username: xxx, password: xxx }
     * @param request 请求对象
     * @return 登录结果
     *   成功则
     *  {
     *      errno: 0,
     *      errmsg: '成功',
     *      data:
     *          {
     *              token: xxx,
     *              tokenExpire: xxx,
     *              userInfo: xxx
     *          }
     *  }
     *   失败则 { errno: XXX, errmsg: XXX }
     */
    @PostMapping(value="/wxlogin",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map login(@RequestBody String body) {

        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");

        Map<Object, Object> result = new HashMap<Object, Object>();
//        result.put("token", userToken.getToken());
//        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("username", username);
        result.put("password", password);
        result.put("timestamp", new Date().getTime());
        return result;
    }
}
