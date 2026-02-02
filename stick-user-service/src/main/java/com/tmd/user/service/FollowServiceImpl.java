package com.tmd.user.service;

import com.tmd.api.user.FollowDubboService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;


@DubboService
public class FollowServiceImpl implements FollowDubboService {
    @Override
    public boolean followUser(Long followerId, Long followingId) {
        return false;
    }

    @Override
    public boolean unfollowUser(Long followerId, Long followingId) {
        return false;
    }

    @Override
    public boolean isFollowing(Long followerId, Long followingId) {
        return false;
    }

    @Override
    public List<Long> getFollowingIds(Long userId) {
        return List.of();
    }

    @Override
    public List<Long> getFollowerIds(Long userId) {
        return List.of();
    }

    @Override
    public int getFollowingCount(Long userId) {
        return 0;
    }

    @Override
    public int getFollowerCount(Long userId) {
        return 0;
    }
}
