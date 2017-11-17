package com.taotao.service;

import com.taotao.common.utils.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : dx
 * @aate : 2017/10/22
 * Description :
 */
public interface PictureService {
    PictureResult uploadFile(MultipartFile uploadFile) throws Exception;
}
