package com.e3mall.content.service.impl;

import com.e3mall.common.utils.E3Result;
import com.e3mall.content.service.ContentCategoryService;
import com.e3mall.mapper.TbContentCategoryMapper;
import com.e3mall.pojo.EasyUITreeNode;
import com.e3mall.pojo.TbContentCategory;
import com.e3mall.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    private TbContentCategoryMapper contentCategoryMapper;
    @Autowired
    public void setContentCategoryMapper(TbContentCategoryMapper contentCategoryMapper) {
        this.contentCategoryMapper = contentCategoryMapper;
    }

    @Override
    public List<EasyUITreeNode> getContentCatList(long parentId) {
        //根据Parentid查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> categoryList = contentCategoryMapper.selectByExample(example);
        //转换成TreeNode列表
        List<EasyUITreeNode>nodeList = new ArrayList<>();
        for (TbContentCategory contentCategory : categoryList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent()?"closed":"open");

            nodeList.add(node);
        }
        return nodeList;
    }

    @Override
    public E3Result addContentCategory(long parentId, String name) {
        //创建pojo对象，设置属性
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //1.正常；2.删除
        contentCategory.setStatus(1);
        //默认排序属性为1
        contentCategory.setSortOrder(1);
        //新添加的必然为叶子节点
        contentCategory.setIsParent(false);
        contentCategory.setUpdated(new Date());
        contentCategory.setCreated(new Date());
        //插入到数据库
        contentCategoryMapper.insert(contentCategory);
        //判断父节点的isparent属性，如果不是true，则改为true
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (! parent.getIsParent()){
            parent.setIsParent(true);
            //更新到数据库
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        //返回包含pojo的e3result
        return E3Result.ok(contentCategory);
    }
}
