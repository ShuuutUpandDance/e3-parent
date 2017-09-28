package com.e3mall.search.service.impl;

import com.e3mall.common.utils.E3Result;
import com.e3mall.pojo.SearchItem;
import com.e3mall.search.mapper.ItemMapper;
import com.e3mall.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 索引库维护service
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    private ItemMapper itemMapper;
    @Autowired
    public void setItemMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    private SolrServer solrServer;
    @Autowired
    public void setSolrServer(SolrServer solrServer) {
        this.solrServer = solrServer;
    }

    @Override
    public E3Result importAllItems() {
        try {
            //查询商品列表
            List<SearchItem> itemList = itemMapper.getItemList();
            //遍历列表
            for (SearchItem searchItem : itemList) {
                //创建文档对象
                SolrInputDocument document = new SolrInputDocument();
                //向文档对象中添加域
                document.addField("id",searchItem.getId());
                document.addField("item_title",searchItem.getTitle());
                document.addField("item_sell_point",searchItem.getSell_point());
                document.addField("item_price",searchItem.getPrice());
                document.addField("item_image",searchItem.getImage());
                document.addField("item_category_name",searchItem.getCategory_name());
                //把文档对象写入索引库
                solrServer.add(document);
            }
            //提交
            solrServer.commit();
            return E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            //返回
            return E3Result.build(500,"导入时发生异常");
        }
    }
}
