package com.tmd.api.stick;

import com.tmd.common.domain.PageResult;
import com.tmd.common.domain.Result;
import com.tmd.common.entity.dto.PStickVO;
import com.tmd.common.entity.dto.StickVO;
import com.tmd.common.entity.po.PStickQueryParam;
import com.tmd.common.entity.po.StickQueryParam;

import java.util.List;

public interface StickDubboService {

    PageResult getTiles(StickQueryParam stickQueryParam);

    Long addTile(StickVO stickVO, Long uid);

    StickVO getTile(Long id);

    boolean updateTile(Long tileId, String content);

    boolean deleteTile(Long id);

    List<StickVO> getAllTiles(long uid);



//    PageResult getPTiles(PStickQueryParam pStickQueryParam);
//
//    Long addPTile(PStickVO pStickVO, Long userId);
//
//    PStickVO getPTile(Long id);
//
//    boolean updatePTile(Long pStickId, String content, String name);
//
//    boolean deletePTile(Long id);
//
//
//    public String saying();
//
//
//    void induction(String prompt, Long uid, Long sid);
//
//    String extractMailInsights(String context);
//
//    java.util.List<String> generateReplySuggestions(String context, int count, String style);

}
