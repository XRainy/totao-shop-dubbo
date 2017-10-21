package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : dx
 * @date : 2017/10/21
 * Description :
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
}
