package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : dx
 * @date : 2017/10/21
 * Description :
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }


    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        logger.debug("查询信息：{}", JsonUtils.objectToJson(page));
        return page;
    }


}
