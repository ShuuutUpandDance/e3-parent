package com.e3mall.content.service.impl;

import com.e3mall.common.utils.E3Result;
import com.e3mall.content.service.ContentService;
import com.e3mall.mapper.TbContentMapper;
import com.e3mall.pojo.EasyUIDataGridResult;
import com.e3mall.pojo.TbContent;
import com.e3mall.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    private TbContentMapper contentMapper;
    @Autowired
    public void setContentMapper(TbContentMapper contentMapper) {
        this.contentMapper = contentMapper;
    }

    @Override
    public EasyUIDataGridResult getContentList(long categoryId, int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //按分类id查询内容
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> contentList = contentMapper.selectByExampleWithBLOBs(example);
        //取分页结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(contentList);
        PageInfo<TbContent>pageInfo = new PageInfo<TbContent>(contentList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public E3Result addContent(TbContent content) {
        //将内容数据插入表中
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入
        contentMapper.insert(content);
        return E3Result.ok();
    }

    /**
     * 根据内容分类id查询内容列表
     * @param cid  内容分类id
     * @return
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        return contentMapper.selectByExampleWithBLOBs(example);
    }
}
