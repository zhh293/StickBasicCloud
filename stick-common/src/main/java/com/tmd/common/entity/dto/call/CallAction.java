package com.tmd.common.entity.dto.call;

/**
 * WebSocket信令事件类型
 */
public enum CallAction {
    INITIATE,
    RINGING,
    ANSWER,
    REJECT,
    CANCEL,
    END,
    ICE,
    SDP_OFFER,
    SDP_ANSWER,
    HEARTBEAT,
    ERROR,
    METRIC;
}
