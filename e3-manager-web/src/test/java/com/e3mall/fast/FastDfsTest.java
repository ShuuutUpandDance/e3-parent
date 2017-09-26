package com.e3mall.fast;

import com.e3mall.common.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

public class FastDfsTest {
    @Test
    public void testUpload() throws Exception {
        //创建一个配置文件。名称任意。就是tracker服务器的地址
        //使用全局对象加载配置文件
        ClientGlobal.init("C:\\Users\\Zhang\\IdeaProjects\\e3-parent\\e3-manager-web\\src\\main\\resources\\conf\\client.conf");
        //创建一个trackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrakerClient获得一个TrackerServer对象
        //创建一个StorageServer的引用，是null
        //创建一个StorageClient，参数需要TrackerServer和StorageServer
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //使用StorageClient上传文件
        String[] strings = storageClient.upload_file("C:\\Users\\Zhang\\Pictures\\fish.jpg", "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void testFastDfsClient() throws Exception{
        FastDFSClient fastDFSClient = new FastDFSClient("C:\\Users\\Zhang\\IdeaProjects\\e3-parent\\e3-manager-web\\src\\main\\resources\\conf\\client.conf");
        String s = fastDFSClient.uploadFile("C:\\Users\\Zhang\\Pictures\\1.png");
        System.out.println(s);
    }
}
