package com.e3mall.content.service.impl;

import com.e3mall.common.utils.E3Result;
import com.e3mall.common.utils.JsonUtils;
import com.e3mall.content.service.ContentService;
import com.e3mall.jedis.JedisClient;
import com.e3mall.mapper.TbContentMapper;
import com.e3mall.pojo.EasyUIDataGridResult;
import com.e3mall.pojo.TbContent;
import com.e3mall.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private JedisClient jedisClient;
    @Autowired
    public void setJedisClient(JedisClient jedisClient) {
        this.jedisClient = jedisClient;
    }

    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

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
        //缓存同步——删除原来缓存中的数据
        jedisClient.hdel(CONTENT_LIST,content.getCategoryId().toString());
        return E3Result.ok();
    }

    /**
     * 根据内容分类id查询内容列表
     * @param cid  内容分类id
     * @return
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        //先查缓存
        try {
            //如果缓存中有，直接响应结果
            String json = jedisClient.hget(CONTENT_LIST, cid + "");
            if (StringUtils.isNotBlank(json)) {
                return JsonUtils.jsonToList(json,TbContent.class);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //如果没有，查询数据库
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> contentList = contentMapper.selectByExampleWithBLOBs(example);

        try {
            //把结果添加到缓存
            jedisClient.hset(CONTENT_LIST,cid+"", JsonUtils.objectToJson(contentList));
        }catch (Exception e) {
            e.printStackTrace();
        }

        return contentList;
    }
}
