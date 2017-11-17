package com.taotao.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * @author : dx
 * @aate : 2017/10/31
 * Description :
 */
public interface ItemParamService {
    TaotaoResult getItemParamByCid(long cid);
    TaotaoResult insertItemParam(TbItemParam itemParam);
}
