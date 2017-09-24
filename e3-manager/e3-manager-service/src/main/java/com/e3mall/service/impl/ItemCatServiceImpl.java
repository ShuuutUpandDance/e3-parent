package com.e3mall.service.impl;

import com.e3mall.mapper.TbItemCatMapper;
import com.e3mall.mapper.TbItemMapper;
import com.e3mall.pojo.EasyUITreeNode;
import com.e3mall.pojo.TbItemCat;
import com.e3mall.pojo.TbItemCatExample;
import com.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    private TbItemCatMapper itemCatMapper;
    @Autowired
    public void setItemCatMapper(TbItemCatMapper itemCatMapper) {
        this.itemCatMapper = itemCatMapper;
    }

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        //根据parentId查询子节点列表
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> itemCatList = itemCatMapper.selectByExample(example);
        //把子节点列表转换成treenode列表
        List<EasyUITreeNode>resultList = new ArrayList<>();
        for (TbItemCat itemCat : itemCatList) {
            EasyUITreeNode node = new EasyUITreeNode();
            //设置节点属性
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            node.setState(itemCat.getIsParent()?"closed":"open");

            resultList.add(node);
        }
        return resultList;
    }
}
