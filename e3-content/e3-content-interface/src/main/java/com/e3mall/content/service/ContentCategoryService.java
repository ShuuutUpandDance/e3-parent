package com.e3mall.content.service;

import com.e3mall.common.utils.E3Result;
import com.e3mall.pojo.EasyUITreeNode;

import java.util.List;

public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(long parentId);
    E3Result addContentCategory(long parentId, String name);
}
