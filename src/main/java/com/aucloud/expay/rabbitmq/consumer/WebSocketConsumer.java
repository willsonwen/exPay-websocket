package com.aucloud.expay.rabbitmq.consumer;

import com.aucloud.expay.rabbitmq.constant.RabbitMqConstant;
import com.aucloud.expay.websocket.WebSocketServer;
import com.aucloud.expay.websocket.data.MessageData;
import com.aucloud.expay.websocket.data.WebSocketData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @projectName: exPay-websocket
 * @package: com.aucloud.expay.rabbitmq.consumer
 * @className: WebSocketConsumer
 * @author: willson·wen
 * @description: TODO
 * @date: 03/11/2022 15:21
 * @version: 1.0
 */
@Component
@Slf4j
public class WebSocketConsumer {

    @Autowired
    private WebSocketServer webSocketServer;

    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConstant.WEB_SOCKET_QUEUE))
    @RabbitHandler
    public void webSocket(MessageData data) {
        log.info("websocket consumer message:" + data.getMessage() + "userId:" + data.getUserId());
        webSocketServer.sendMessage(data.getUserId(),data.getUserType(),data.getMessage());
    }

}
