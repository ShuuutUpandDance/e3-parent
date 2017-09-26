package com.e3mall.controller;

import com.e3mall.common.utils.E3Result;
import com.e3mall.pojo.EasyUIDataGridResult;
import com.e3mall.pojo.TbItem;
import com.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    private ItemService itemService;
    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 测试用，根据itemId返回json
     * @param itemId
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        return itemService.getItemById(itemId);
    }

    /**
     * 返回带了分页的列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        return itemService.getItemList(page, rows);
    }

    /**
     * 添加商品和商品描述
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result addItem(TbItem item, String desc) {
        return itemService.addItem(item, desc);
    }

    /**
     * 删除复选商品
     * @param ids
     * @return
     */
    @RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
    @ResponseBody
    public E3Result deleteItem(Long[] ids) {
        return itemService.deleteItem(ids);
    }
}
