package com.e3mall.controller;

import com.e3mall.common.utils.E3Result;
import com.e3mall.content.service.ContentService;
import com.e3mall.pojo.EasyUIDataGridResult;
import com.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    private ContentService contentService;
    @Autowired
    public void setContentService(ContentService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
        return contentService.getContentList(categoryId, page, rows);
    }

    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result addContent(TbContent content) {
        return contentService.addContent(content);
    }
}
