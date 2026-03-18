package com.tmd.user.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.tmd.api.user.UserDubboService;
import com.tmd.common.config.RedisCache;
import com.tmd.common.entity.dto.UserProfile;
import com.tmd.common.entity.dto.UserUpdateDTO;
import com.tmd.common.entity.po.LoginUser;
import com.tmd.common.entity.po.UserData;
import com.tmd.common.util.JwtUtil;
import com.tmd.user.mapper.UserMapper;
import com.tmd.user.publisher.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@DubboService
@Slf4j
public class UserServiceImpl implements UserDubboService , UserDetailsService{
    @Autowired
    private ObjectProvider<org.springframework.security.authentication.AuthenticationManager> authenticationManagerProvider;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean register(UserData userData) {
        if(userMapper.findByUsername(userData) == null)
        {
            userMapper.register(userData);
            return true;
        }
        return false;
    }

    @Override
    public UserData login(UserData userData) {
        Authentication authenticate = authenticationManagerProvider.getObject()
                .authenticate(new UsernamePasswordAuthenticationToken(userData.getUsername(), userData.getPassword()));
        if(authenticate==null){
            throw new RuntimeException("用户名或密码错误");
        }
        Object principal = authenticate.getPrincipal();
        LoginUser loginUser = (LoginUser) principal;
        //生成jwt令牌，并且存入Redis中
        Map<String,Long> map=new HashMap<>();
        map.put("id",loginUser.getUser().getId());
        String jwt = JwtUtil.makeToken(userData.getId());
        userData.setToken(jwt);
        redisCache.setCacheObject("login:"+jwt,loginUser);
        UserData user = loginUser.getUser();
        user.setToken(jwt);
        user.setId(user.getId());
        log.info("用户登陆成功{}",user);
        return user;
    }

    @Override
    public UserProfile getProfile(Long userId) {
        // 优先从缓存读取基础资料
        log.info("正在读取用户基础资料: userId={}", userId);
        String profileKey = "user:profile:" + userId;
        String s = stringRedisTemplate.opsForValue().get(profileKey);
        UserProfile userProfile;
        log.info(":读取的数据{}", s);
        if (StrUtil.isBlank(s)) {
            userProfile = userMapper.getProfile(userId);
            log.info(":读取的数据{}", userProfile);
            if (userProfile != null) {
                // 缓存基础资料 10 分钟，避免频繁 DB 访问
                stringRedisTemplate.opsForValue().set("user:profile:" + userId, JSONUtil.toJsonStr(userProfile), 10, TimeUnit.MINUTES);
            }
        }else
            userProfile = JSONObject.parseObject(s, UserProfile.class);
        log.info(":反序列化用户数据{}", userProfile);
        String dailyBookmark=stringRedisTemplate.opsForValue().get("bookmark:" + userId);
        log.info(":读取的书签{}", dailyBookmark);
        if(StrUtil.isNotBlank(dailyBookmark)){
            userProfile.setIsFirst(false);
            userProfile.setDailyBookmark(dailyBookmark);
            return userProfile;
        }else{
            //生成书签
            log.info("生成书签: userId={}", userId);
            messageProducer.sendDirectMessage(userId, true);
            userProfile.setIsFirst(true);
            return userProfile;
        }
    }

    @Override
    public boolean updatePassword(long uid, String oldPassword, String newPassword) {
        try {
            userMapper.updatePassword(uid, oldPassword, newPassword);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void updateUserProfile(Long id, UserUpdateDTO userUpdateDTO) {
        userMapper.update(id, userUpdateDTO);
        // 更新资料后主动失效缓存，确保读取到最新信息
        try {
            redisCache.deleteObject("user:profile:" + id);
        } catch (Exception e) {
            log.error("更新用户资料后主动失效缓存时发生异常: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean softDeleteUser(Long userId) {
        try {
            log.info("执行软删除用户: userId={}", userId);
            userMapper.softDelete(userId);
            return true;
        } catch (Exception e) {
            log.error("软删除用户失败: userId={}", userId, e);
            return false;
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[用户登录] 尝试登录用户: {}", username);
        //开始匹配并且放入
        if(!StringUtils.hasText(username)){
            throw new UsernameNotFoundException("不要填空值");
        }
        UserData user = userMapper.check(username);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        LoginUser loginUser = new LoginUser(user);
        return loginUser;
    }
}
