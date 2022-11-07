package com.aucloud.expay.websocket;

import com.aucloud.expay.websocket.config.WebSocketConfig;
import com.aucloud.expay.websocket.data.WebSocketData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName: exPay-websocket
 * @package: com.aucloud.expay.websocket.config
 * @className: WebSocketServer
 * @author: willson·wen
 * @description: TODO
 * @date: 03/11/2022 11:02
 * @version: 1.0
 */
@Slf4j
@Component
@ServerEndpoint(value = "/websocket", configurator = WebSocketConfig.class)
public class WebSocketServer  {

    private static Map<String, WebSocketData> servers = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session){
        String userId = (String)session.getUserProperties().get("userId");
        String userType = (String)session.getUserProperties().get("userType");
        servers.put(userId,new WebSocketData(userId,userType,session));
    }

    @OnClose
    public void onClose(Session session) {
        servers.remove(session.getId());
    }

    @OnMessage
    public void onMessage(Session session,String msg) {
        log.info("session id:" + session.getId() + ", message:" +msg);
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        servers.remove(session.getId());
        log.error(throwable.getMessage(),throwable);
    }

    /**
     * @param userId:
     * @param msg:
     * @return void
     * @author willson·wen
     * @description 发送消息
     * @date 03/11/2022 13:59
     */
    public void sendMessage(String userId,String userType, String msg) {
        servers.values().forEach(f -> {
            if (userId.equals(f.getUserId()) && userType.equals(f.getUserType())) {
                try {
                    f.getSession().getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


}
