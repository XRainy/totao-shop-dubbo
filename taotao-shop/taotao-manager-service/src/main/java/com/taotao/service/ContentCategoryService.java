package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;

import java.util.List;

/**
 * @author : dx
 * @aate : 2017/11/4
 * Description :
 */
public interface ContentCategoryService {
    List<EUTreeNode> getCategoryList(long parentId);
    TaotaoResult insertContentCategory(long parentId, String name);
    TaotaoResult updateContentCategory(long id, String name);
    TaotaoResult deleteContentCategory(Long id) ;

}
