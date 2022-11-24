package com.lagou.edu.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @author cck
 * @date 2022/11/18 14:53
 */
@EnableBinding(Sink.class)
public class MessageConsumerService {

    @StreamListener(Sink.INPUT)
    public void recevieMessage(Message<String> message){
        System.out.println("======接收到的消息是========>"+message);
    }
}
