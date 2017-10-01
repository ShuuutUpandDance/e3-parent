package com.e3mall.search.message;

import com.e3mall.pojo.SearchItem;
import com.e3mall.search.mapper.ItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 监听商品添加信息，接收消息后，将对应的商品信息同步到索引库
 */
public class ItemAddMessageListener implements MessageListener {

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
    public void onMessage(Message message) {
        try {
            //从消息中取商品id
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            Long itemId = Long.parseLong(text);
            //等待事务提交
            Thread.sleep(1000);
            //根据商品id查询商品信息
            SearchItem searchItem = itemMapper.getItemById(itemId);
            //创建一个文档对象
            SolrInputDocument document = new SolrInputDocument();
            //向文档中添加域
            document.addField("id",searchItem.getId());
            document.addField("item_title",searchItem.getTitle());
            document.addField("item_sell_point",searchItem.getSell_point());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_category_name",searchItem.getCategory_name());
            //把文档写入索引库
            solrServer.add(document);
            //提交
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
