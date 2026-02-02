package com.tmd.user.service;

import com.tmd.api.user.UserDubboService;
import com.tmd.common.entity.dto.UserProfile;
import com.tmd.common.entity.dto.UserUpdateDTO;
import com.tmd.common.entity.po.UserData;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class UserServiceImpl implements UserDubboService {

    @Override
    public boolean register(UserData userData) {
        return false;
    }

    @Override
    public UserData login(UserData userData) {
        return null;
    }

    @Override
    public UserProfile getProfile(Long userId) {
        return null;
    }

    @Override
    public boolean updatePassword(long uid, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public void updateUserProfile(Long id, UserUpdateDTO userUpdateDTO) {

    }

    @Override
    public boolean softDeleteUser(Long userId) {
        return false;
    }
}
