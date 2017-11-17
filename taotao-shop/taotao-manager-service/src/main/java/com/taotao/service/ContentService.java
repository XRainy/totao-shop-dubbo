package com.taotao.service;

import com.taotao.common.utils.EasyUIResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * @author : dx
 * @aate : 2017/11/5
 * Description :
 */
public interface ContentService {
    EasyUIResult getContentList(long catId, Integer page, Integer rows) throws Exception;
    TaotaoResult addContent(TbContent content) throws Exception;
}
