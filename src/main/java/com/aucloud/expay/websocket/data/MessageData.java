package com.aucloud.expay.websocket.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @projectName: exPay-websocket
 * @package: com.aucloud.expay.websocket.data
 * @className: MessageData
 * @author: willson·wen
 * @description: TODO
 * @date: 03/11/2022 15:27
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageData implements Serializable {


    private String userId;

    /**
     * @author willson·wen
     * @description 1:商户 2:管理员 3:交易员
     * @date 07/11/2022 14:43
     */
    private String userType;

    private String message;

}
