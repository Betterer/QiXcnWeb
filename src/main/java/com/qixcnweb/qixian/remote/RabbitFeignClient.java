package com.qixcnweb.qixian.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 此类用于eureka远程调用rabbitmq方法
 * Created by dingxiaochi on 2018/4/1.
 */
@FeignClient(name = "qixian-rabbitmq")
public interface RabbitFeignClient {


    /**
     * 发布消息
     * @param exchangeName
     * @param routingKey
     * @param message
     * @return
     */
    @RequestMapping(value = "/rabbit/send_message")
    String sendMessage(@RequestParam("exchangeName") String exchangeName, @RequestParam("routingKey") String routingKey, @RequestParam("message") Object message);

    /**
     * 发送消息(不使用交换机)
     * @param routingKey
     * @param message
     * @return
     */
    @RequestMapping(value = "/rabbit/send_message_no_exchange")
    String sendMessageWithoutExchange(@RequestParam("routingKey") String routingKey,@RequestParam("message") Object message);


    /**
     * 发布公共消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/rabbit/publish_message")
    String publishMessage(@RequestParam("message") Object message);

}
