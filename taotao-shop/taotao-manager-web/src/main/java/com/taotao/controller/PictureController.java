package com.taotao.controller;

import com.taotao.common.utils.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : dx
 * @aate : 2017/10/22
 * Description :
 */
@Controller
@RequestMapping("/pic")

public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/upload")
    @ResponseBody
    public PictureResult uploda(MultipartFile uploadFile) throws Exception {
        //调用service上传图片
        PictureResult pictureResult = pictureService.uploadFile(uploadFile);
        //返回上传结果
        return pictureResult;

    }

}
