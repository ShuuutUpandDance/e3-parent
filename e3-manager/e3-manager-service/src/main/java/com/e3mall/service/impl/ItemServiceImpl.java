package com.e3mall.service.impl;

import com.e3mall.common.utils.E3Result;
import com.e3mall.common.utils.IDUtils;
import com.e3mall.mapper.TbItemDescMapper;
import com.e3mall.mapper.TbItemMapper;
import com.e3mall.pojo.EasyUIDataGridResult;
import com.e3mall.pojo.TbItem;
import com.e3mall.pojo.TbItemDesc;
import com.e3mall.pojo.TbItemExample;
import com.e3mall.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private TbItemMapper itemMapper;
    @Autowired
    public void setItemMapper(TbItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    private TbItemDescMapper itemDescMapper;
    @Autowired
    public void setItemDescMapper(TbItemDescMapper itemDescMapper) {
        this.itemDescMapper = itemDescMapper;
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

    @Override
    public E3Result addItem(TbItem item, String desc) {
        //生成商品id
        long itemId = IDUtils.genItemId();
        //补全item属性
        item.setId(itemId);
        //1.正常；2.下架；3.删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //向商品表插入数据
        itemMapper.insert(item);
        //补全desc属性
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        //向描述表插入数据
        itemDescMapper.insert(itemDesc);
        //返回成功
        return E3Result.ok();
    }

    @Override
    public E3Result deleteItem(Long[] ids) {
        //删除只是把status修改为3
        for (long id : ids) {
            TbItem item = itemMapper.selectByPrimaryKey(id);
            item.setStatus((byte) 3); //3代表删除
            item.setUpdated(new Date());
            itemMapper.updateByPrimaryKey(item);
        }
        return E3Result.ok();
    }
}
