package com.tmd.stick.service;


import com.tmd.api.stick.FuncDubboService;
import com.tmd.stick.mapper.FuncMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Bluegod
 * @Date 2025/9/10
 */
@Service
public class FuncServiceimpl implements FuncDubboService {

    @Autowired
    private FuncMapper funcMapper;
    @Override
    public String saying() {
        return funcMapper.saying();
    }
}
