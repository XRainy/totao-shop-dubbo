package com.taotao.service;

import com.taotao.pojo.TbItemCat;

import java.util.List;

/**
 * @author : dx
 * @aate : 2017/10/22
 * Description :
 */
public interface ItemCatService {
    List<TbItemCat> getItemCatList(Long parentId) throws Exception;
}
