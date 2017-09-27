package com.e3mall.content.service;

import com.e3mall.common.utils.E3Result;
import com.e3mall.pojo.EasyUIDataGridResult;
import com.e3mall.pojo.TbContent;

import java.util.List;

public interface ContentService {
    EasyUIDataGridResult getContentList(long categoryId, int page, int rows);
    E3Result addContent(TbContent content);
    List<TbContent> getContentListByCid(long cid);
}
