package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import org.springframework.stereotype.Service;

/**
 * @author : dx
 * @date : 2017/10/22
 * Description :
 */
@Service
public interface ItemService {
    /**
     *
     * @param id
     * @return
     */
    TbItem getItemBiId(Long id);
    /**
     * 商品列表查询
     * <p>Title: getItemList</p>
     * <p>Description: </p>
     * @param page
     * @param rows
     * @return
     */
    EUDataGridResult getItemList(int page, int rows);

    /**
     *
     * @param item
     * @param desc
     * @throws Exception
     */
    TaotaoResult saveItem(TbItem item, String desc,String itemParams) throws Exception;

}
