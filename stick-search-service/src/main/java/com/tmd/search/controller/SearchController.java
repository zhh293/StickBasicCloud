package com.tmd.search.controller;

import com.tmd.common.domain.Result;

import com.tmd.search.service.SearchServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class SearchController {

    private final SearchServiceImpl searchService;

    @GetMapping("/search")
    public Result search(@RequestParam String keyword,
                         @RequestParam(required = false, defaultValue = "all") String type,
                         @RequestParam(required = false, defaultValue = "1") Integer page,
                         @RequestParam(required = false, defaultValue = "10") Integer size,
                         @RequestParam(required = false, defaultValue = "hot") String sort,
                         @RequestParam(required = false) Long topicId,
                         @RequestParam(required = false) Long startTime,
                         @RequestParam(required = false) Long endTime) {
        log.info("全文搜索: keyword={}, type={}, page={}, size={}, sort={}, topicId={}, startTime={}, endTime={}",
                keyword, type, page, size, sort, topicId, startTime, endTime);
        return searchService.search(keyword, type, page, size, sort, topicId, startTime, endTime);
    }
}
