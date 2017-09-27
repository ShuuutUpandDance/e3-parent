package com.e3mall.portal.controller;

import com.e3mall.content.service.ContentService;
import com.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 首页展示
 */
@Controller
public class IndexController {
    private ContentService contentService;
    @Autowired
    public void setContentService(ContentService contentService) {
        this.contentService = contentService;
    }
    @Value(value = "${CONTENT_LUNBO_ID}")
    private Long CONTENT_LUNBO_ID;

    @RequestMapping(value = "/index")
    public String showIndex(Model model) {
        //先查询内容列表，就固定一个测试着
        List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
        //结果传递给页面
        model.addAttribute("ad1List", ad1List);
        return "index";
    }
}
