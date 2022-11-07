package com.aucloud.expay.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;
import java.util.List;
import java.util.Map;


/**
 * @projectName: exPay-websocket
 * @package: com.aucloud.expay.websocket.config
 * @className: WebSocketConfig
 * @author: willson·wen
 * @description: TODO
 * @date: 03/11/2022 13:31
 * @version: 1.0
 */
@Configuration
public class WebSocketConfig extends Configurator {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        Map<String, List<String>> parameterMap = request.getParameterMap();
        Map<String, Object> userProperties = sec.getUserProperties();
        userProperties.put("userId", parameterMap.get("id").get(0));
        userProperties.put("userType",parameterMap.get("type").get(0));
    }
}
