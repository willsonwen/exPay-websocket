package com.aucloud.expay.websocket.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.websocket.Session;
import java.io.Serializable;

/**
 * @projectName: exPay-websocket
 * @package: com.aucloud.expay.websocket.data
 * @className: WebSocketData
 * @author: willson·wen
 * @description: TODO
 * @date: 03/11/2022 13:48
 * @version: 1.0
 */
@Data
@AllArgsConstructor
public class WebSocketData {

    private String userId;

    /**
     * @author willson·wen
     * @description 0:管理员 1:交易员 2:商户
     * @date 07/11/2022 14:43
     */
    private String userType;

    private Session session;

}
