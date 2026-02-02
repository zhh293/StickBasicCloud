package com.tmd.api.content;

import com.tmd.common.domain.Result;
import com.tmd.common.entity.dto.TopicDTO;
import com.tmd.common.entity.dto.TopicVO;

public interface TopicDubboService {

    Result getAllTopics(Integer page, Integer size, String status) throws InterruptedException;

    Result getTopicById(Integer topicId);

    /**
     * 通过话题ID获取话题，带Redis缓存（返回TopicVO，便于内部服务复用）
     */
    TopicVO getTopicCachedById(Integer topicId);

    Result createTopic(TopicDTO topic);

    Result followTopic(Integer topicId);

    Result getTopicFollowers(Integer topicId, Integer page, Integer size);

    Result getTopicPosts(Integer topicId,
                         Integer page,
                         Integer size,
                         String sort,
                         String status) throws InterruptedException;

    void incrementTopicPostCount(Long topicId, Integer delta);
}
