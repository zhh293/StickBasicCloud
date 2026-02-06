package com.tmd.stick.controller;


import com.tmd.common.domain.Result;
import com.tmd.common.util.BaseContext;
import com.tmd.stick.service.FuncServiceimpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



/**
 * @Description
 * @Author Bluegod
 * @Date 2025/9/10
 */
@Slf4j
@RestController
public class FuncController {
    @Autowired
    private FuncServiceimpl funcService;

    @GetMapping("/saying")
    public Result saying(@RequestHeader("authentication") String authorization ) {
        log.info("用户正在获取每日一句");
        log.info("用户授权信息：{}", authorization);
        long uid;
        uid= BaseContext.get();
        log.info("用户ID：{}", uid);
        if (uid != -1){
            return Result.success(funcService.saying());
        }
        return Result.error("验证失败,非法访问");
    }
}
