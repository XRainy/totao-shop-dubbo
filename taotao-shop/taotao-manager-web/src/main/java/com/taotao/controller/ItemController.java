package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : dx
 * @aate : 2017/10/22
 * Description :
 */
@RequestMapping("/item")
@Controller
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    ItemService itemService;
    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        return itemService.getItemBiId(itemId);
    }
    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult saveItem(TbItem item, String desc,String itemParams) throws Exception {
        //添加商品信息
        TaotaoResult taotaoResult = itemService.saveItem(item, desc,itemParams);
        return taotaoResult;
    }

}
