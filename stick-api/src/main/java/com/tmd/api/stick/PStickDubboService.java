package com.tmd.api.stick;

import com.tmd.common.domain.PageResult;
import com.tmd.common.entity.dto.PStickVO;
import com.tmd.common.entity.po.PStickQueryParam;

public interface PStickDubboService {
    PageResult getPTiles(PStickQueryParam pStickQueryParam);

    Long addPTile(PStickVO pStickVO, Long userId);

    PStickVO getPTile(Long id);

    boolean updatePTile(Long pStickId, String content, String name);

    boolean deletePTile(Long id);
}
