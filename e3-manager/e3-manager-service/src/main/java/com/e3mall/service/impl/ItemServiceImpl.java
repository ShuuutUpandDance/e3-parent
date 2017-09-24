package com.e3mall.service.impl;

import com.e3mall.mapper.TbItemMapper;
import com.e3mall.pojo.EasyUIDataGridResult;
import com.e3mall.pojo.TbItem;
import com.e3mall.pojo.TbItemExample;
import com.e3mall.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private TbItemMapper itemMapper;
    @Autowired
    public void setItemMapper(TbItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public TbItem getItemById(long itemId) {
        return itemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //查询
        TbItemExample example = new TbItemExample();
        List<TbItem>itemList = itemMapper.selectByExample(example);
        //取分页结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(itemList);
        PageInfo<TbItem>pageInfo = new PageInfo<TbItem>(itemList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
