package com.e3mall.service;

import com.e3mall.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
