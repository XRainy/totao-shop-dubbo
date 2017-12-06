package com.taotao.dubbo.service;

import com.taotao.dubbo.dto.TbContentDto;

import java.util.List;

/**
 * @author : dx
 * @aate : 2017/11/5
 * Description :
 */
public interface ContentService {
    List<TbContentDto> getContentList(long contentCid);
}
