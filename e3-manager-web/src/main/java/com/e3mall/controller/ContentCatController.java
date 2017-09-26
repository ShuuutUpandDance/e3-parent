package com.e3mall.controller;

import com.e3mall.common.utils.E3Result;
import com.e3mall.content.service.ContentCategoryService;
import com.e3mall.pojo.EasyUITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类管理
 */
@Controller
public class ContentCatController {

    private ContentCategoryService contentCategoryService;
    @Autowired
    public void setContentCategoryService(ContentCategoryService contentCategoryService) {
        this.contentCategoryService = contentCategoryService;
    }

    /**
     * 分类tree展示
     * @param parentId
     * @return
     */
    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(
            @RequestParam(name = "id", defaultValue = "0") Long parentId) {
        return contentCategoryService.getContentCatList(parentId);
    }

    @RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
    @ResponseBody
    public E3Result createContentCategory(Long parentId, String name) {
        //调用服务添加节点
        return contentCategoryService.addContentCategory(parentId, name);
    }
}
