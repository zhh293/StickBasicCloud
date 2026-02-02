package com.tmd.api.search;

import com.tmd.common.domain.Result;

public interface SearchDubboService {

    Result search(String keyword,
                  String type,
                  Integer page,
                  Integer size,
                  String sort,
                  Long topicId,
                  Long startTime,
                  Long endTime);
}
