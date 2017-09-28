package com.e3mall.solrj;

import com.e3mall.common.utils.E3Result;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestSolrJ {
//    /**
//     * 添加文档
//     * @throws Exception
//     */
//    @Test
//    public void addDocument() throws Exception {
//        //创建一个SolrServer对象，创建一个连接。参数solr服务的url
//        SolrServer solrServer = new HttpSolrServer("http://192.168.25.134:8080/solr/collection1");
//        //创建一个文档对象SolrInputDocument
//        SolrInputDocument document = new SolrInputDocument();
//        //向文档中添加域，文档中必须包含一个id域，所有域的名称必须在schema.xml中定义
//        document.addField("id","doc01");
//        document.addField("item_title","测试商品01");
//        document.addField("item_price",1000);
//        //把文档写入索引库
//        solrServer.add(document);
//        //提交
//        solrServer.commit();
//    }
//
//    @Test
//    public void deleteDocument() throws Exception {
//        SolrServer solrServer = new HttpSolrServer("http://192.168.25.134:8080/solr/collection1");
//        //删除文档
////        solrServer.deleteById("doc01");
//        solrServer.deleteByQuery("id:doc01");
//        //提交
//        solrServer.commit();
//    }
//
//    @Test
//    public void testQueryIndex() throws Exception{
//        //创建一个solrServer对象
//        SolrServer solrServer = new HttpSolrServer("http://192.168.25.134:8080/solr/collection1");
//        //创建一个solrQuery对象
//        SolrQuery solrQuery = new SolrQuery();
//        //设置查询条件
//        solrQuery.setQuery("*:*");
//        //执行查询，queryResponse对象
//        QueryResponse response = solrServer.query(solrQuery);
//        //取文档列表。取查询结果的总记录数
//        SolrDocumentList solrDocumentList = response.getResults();
//        System.out.println("总记录数："+solrDocumentList.getNumFound());
//        //遍历文档列表，从文档中取域的内容
//        for (SolrDocument solrDocument : solrDocumentList) {
//            System.out.println(solrDocument.get("id"));
//            System.out.println(solrDocument.get("item_title"));
//            System.out.println(solrDocument.get("item_sell_point"));
//        }
//    }
//
//    @Test
//    public void queryComplex() throws Exception {
//        SolrServer solrServer = new HttpSolrServer("http://192.168.25.134:8080/solr/collection1");
//        SolrQuery query = new SolrQuery();
//
//        query.setQuery("手机");
//        query.setStart(0);
//        query.setRows(20);
//        query.set("df","item_title");
//        query.setHighlight(true);
//        query.addHighlightField("item_title");
//        query.setHighlightSimplePre("<em>");
//        query.setHighlightSimplePost("</em>");
//
//        //执行查询，queryResponse对象
//        QueryResponse response = solrServer.query(query);
//        //取文档列表。取查询结果的总记录数
//        SolrDocumentList solrDocumentList = response.getResults();
//        System.out.println("总记录数："+solrDocumentList.getNumFound());
//        //遍历文档列表，从文档中取域的内容
//        for (SolrDocument solrDocument : solrDocumentList) {
//            System.out.println(solrDocument.get("id"));
//            //取高亮显示
//            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
//            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
//            String title = "";
//            if (list != null && list.size() > 0) {
//                title = list.get(0);
//            } else {
//                title = (String) solrDocument.get("item_title");
//            }
//
//            System.out.println(solrDocument.get("item_sell_point"));
//        }
//    }
}
