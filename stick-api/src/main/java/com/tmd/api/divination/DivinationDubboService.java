package com.tmd.api.divination;

import com.tmd.common.domain.Result;
//这玩意儿没人调用，也没有service
public interface DivinationDubboService {

    Result recognizeWord(String imageUrl);

    Result predictWord(String word);

    Result getDivinationHistory(Long userId, Integer page, Integer size);
}
