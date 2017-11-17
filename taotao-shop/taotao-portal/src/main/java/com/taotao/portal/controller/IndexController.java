package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : dx
 * @aate : 2017/11/3
 * Description :
 */
@Controller
public class IndexController {
    @Autowired
    ContentService contentService;
    @RequestMapping("/")
    public String index(Model model){
        String adJson = contentService.getContentList();
        model.addAttribute("ad1", adJson);
        return "index";
    }
}
