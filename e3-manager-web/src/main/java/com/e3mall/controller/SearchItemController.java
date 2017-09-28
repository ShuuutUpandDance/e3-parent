package com.e3mall.controller;

import com.e3mall.common.utils.E3Result;
import com.e3mall.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 导入商品数据到索引库
 */
@Controller
public class SearchItemController {

    private SearchItemService searchItemService;
    @Autowired
    public void setSearchItemService(SearchItemService searchItemService) {
        this.searchItemService = searchItemService;
    }

    @RequestMapping("/index/item/import")
    @ResponseBody
    public E3Result importItemList() {
        return searchItemService.importAllItems();
    }
}
