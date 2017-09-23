package com.e3mall.service.impl;

import com.e3mall.mapper.TbItemMapper;
import com.e3mall.pojo.TbItem;
import com.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
