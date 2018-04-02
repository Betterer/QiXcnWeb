package com.qixcnweb.qixian.remote;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 此类用于接收rabbitmq中间件消息
 * Created by dingxiaochi on 2018/3/31.
 */
@Component
public class rabbitReceiver {

    @RabbitHandler
    @RabbitListener(queues = "queue_no_1")
    public void testReceiver(String message){
        System.out.println("起线科技接受端  : " + message);
    }
}
