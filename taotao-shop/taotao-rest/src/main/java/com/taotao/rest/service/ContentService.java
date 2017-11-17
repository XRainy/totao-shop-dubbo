package com.taotao.rest.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * @author : dx
 * @aate : 2017/11/5
 * Description :
 */
public interface ContentService {
    List<TbContent> getContentList(long contentCid);
}
