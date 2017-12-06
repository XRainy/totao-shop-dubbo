package com.taotao.rest.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.dubbo.dto.TbContentDto;
import com.taotao.dubbo.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : dx
 * @aate : 2017/11/5
 * Description :
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    @Override
    public List<TbContentDto> getContentList(long contentCid) {
        //根据内容分类id查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExample(example);

        return JsonUtils.jsonToPojo(JsonUtils.objectToJson(list),List.class);
    }

}
