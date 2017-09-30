package com.e3mall.solrj;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrCloud {
    @Test
    public void testAddDoc() throws Exception {
//        //创建一个集群的连接，应该使用CloudServer创建
//        // zkHost：zookeeper的地址列表
//        CloudSolrServer solrServer = new CloudSolrServer("192.168.25.134:2181,192.168.25.134:2182,192.168.25.134:2183");
//
//        //设置一个defaultCollection属性
//        solrServer.setDefaultCollection("collection2");
//        //创建一个文档对象
//        SolrInputDocument document = new SolrInputDocument();
//        //向文档中添加域
//        document.setField("id","solrcoud01");
//        document.setField("item_title","测试商品01");
//        document.setField("item_price",123);
//        //把文件写入索引库
//        solrServer.add(document);
//        //提交
//        solrServer.commit();
    }
}
