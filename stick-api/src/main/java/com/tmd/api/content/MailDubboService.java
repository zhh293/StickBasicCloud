package com.tmd.api.content;

import com.tmd.common.domain.PageResult;
import com.tmd.common.domain.ScrollResult;
import com.tmd.common.entity.dto.MailDTO;
import com.tmd.common.entity.dto.MailPackage;
import com.tmd.common.entity.dto.MailVO;
import com.tmd.common.entity.dto.Result;

public interface MailDubboService {

    MailVO getMailById(String mailId);

    ScrollResult getAllMails(MailPackage mailPackage);

    PageResult getSelfMails(Integer page, Integer size, String status);

    void sendMail(MailDTO mailDTO);

    com.tmd.common.entity.dto.Result comment(Long mailId, MailDTO mailDTO, Boolean isFirst);

    PageResult getReceivedMails(Integer page, Integer size, String status);

    PageResult getSelfCommentMails(Integer page, Integer size);

    com.tmd.common.entity.dto.Result agentInsight(Long mailId);

    Result agentSuggest(Long mailId, Integer count, String style);
}
