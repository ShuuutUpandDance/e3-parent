package com.e3mall.controller;

import com.e3mall.pojo.EasyUITreeNode;
import com.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {
    private ItemCatService itemCatService;
    @Autowired
    public void setItemCatService(ItemCatService itemCatService) {
        this.itemCatService = itemCatService;
    }

    /**
     * 请求里面叫id，所以要注明name；
     * 第一次请求时不携带id，所以默认为根节点0
     * @param parentId
     * @return
     */
    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(
            @RequestParam(name = "id", defaultValue = "0") Long parentId)
    {
        return itemCatService.getItemCatList(parentId);
    }
}
