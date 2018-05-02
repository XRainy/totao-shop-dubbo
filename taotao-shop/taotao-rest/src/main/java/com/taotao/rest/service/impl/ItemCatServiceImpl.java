package com.taotao.rest.service.impl;

import com.taotao.dubbo.service.ItemCatService;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.dubbo.dto.CatNode;
import com.taotao.dubbo.dto.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : dx
 * @date : 2018/2/11
 * Description :
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public CatResult queryAllCategory() throws Exception {

        CatResult result = new CatResult();
        result.setData(getItemCatList(0L));

        return result;
    }
    /**
     * 查询分类列表
     * <p>Title: getItemCatList</p>
     * <p>Description: </p>
     * @param parentid
     * @return
     */
    private List<?> getItemCatList(long parentid) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        //查询parentid为0的分类信息
        criteria.andParentIdEqualTo(parentid);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List dataList = new ArrayList();
        for (TbItemCat tbItemCat : list) {
            //判断是否为父节点
            if (tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                catNode.setU("/category/" + tbItemCat.getId() + ".html");
                catNode.setN(tbItemCat.getName());
                //递归调用
                catNode.setI(getItemCatList(tbItemCat.getId()));
                //添加到列表
                dataList.add(catNode);
            } else {
                String catItem = "/item/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
                dataList.add(catItem);
            }
        }
        return dataList;
    }

}
