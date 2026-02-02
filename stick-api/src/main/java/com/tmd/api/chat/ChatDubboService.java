package com.tmd.api.chat;

import com.tmd.common.domain.Result;

public interface ChatDubboService {

    Result send(Long fromUserId, Long toUserId, String content);

    Result pullOffline(Integer page, Integer size);

    Result history(Long userId, Long otherUserId, Integer size, Long max, Integer offset);

    Result markRead(Long userId, Long messageId);

    Result recall(Long userId, Long messageId);

    Result ack(Long userId, Long messageId);
}
