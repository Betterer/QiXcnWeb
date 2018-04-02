package com.qixcnweb.qixian.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/** 测试eureka远程调用
 * Created by dingxiaochi on 2018/3/23.
 */
@FeignClient(name = "qixian-mail")
public interface TestFeignClient {

    @RequestMapping(value = "/email/send_email")
    String sendEmail();

    @RequestMapping(value = "/email/test")
    String testEmail();
}
