package com.e3mall.service;

import com.e3mall.common.utils.E3Result;
import com.e3mall.pojo.EasyUIDataGridResult;
import com.e3mall.pojo.TbItem;

public interface ItemService {
    TbItem getItemById(long itemId);
    EasyUIDataGridResult getItemList(int page, int rows);
    E3Result addItem(TbItem item, String desc);
    E3Result deleteItem(Long[] ids);
}
