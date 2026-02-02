package com.tmd.api.user;

import com.tmd.common.domain.Result;
import com.tmd.common.entity.dto.UserProfile;
import com.tmd.common.entity.dto.UserUpdateDTO;
import com.tmd.common.entity.po.UserData;

import java.util.List;

public interface UserDubboService {

    public boolean register(UserData userData);

    public UserData login(UserData userData);

    UserProfile getProfile(Long userId);

    boolean updatePassword(long uid, String oldPassword, String newPassword);

    void updateUserProfile(Long id, UserUpdateDTO userUpdateDTO);

    boolean softDeleteUser(Long userId);



}
