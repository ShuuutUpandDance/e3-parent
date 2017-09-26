package com.e3mall.controller;

import com.e3mall.common.utils.FastDFSClient;
import com.e3mall.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    //同时指定了响应结果的contentType
    @RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) {

        try {
            //把图片上传到图片服务器
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
            //获取文件扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            //得到图片的地址和文件名
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            //补充为完整的url
            url = IMAGE_SERVER_URL + url;
            //封装返回
            Map result = new HashMap();
            result.put("error",0);
            result.put("url",url);
            String json = JsonUtils.objectToJson(result);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            //封装返回
            Map result = new HashMap();
            result.put("error",1);
            result.put("message","图片上传失败");
            String json = JsonUtils.objectToJson(result);
            return json;
        }
    }
}
