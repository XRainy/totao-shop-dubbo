package com.taotao.portal.controller;

import com.taotao.dubbo.dto.CatResult;
import com.taotao.dubbo.service.ContentService;
import com.taotao.dubbo.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : dx
 * @aate : 2017/11/3
 * Description :
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private ContentService contentService;
    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/category")
    @ResponseBody
    public CatResult queryAll(String callback) throws Exception {
        //查询分类列表
        CatResult result =null;
        try{
            result = itemCatService.queryAllCategory();
        }catch (Exception e){
            logger.error("获取品类信息错误：",e);
        }

        return result;
    }

}
