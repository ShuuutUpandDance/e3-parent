package com.e3mall.freemarker;

import com.sun.javafx.collections.MappingChange;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerTest {
    @Test
    public void testFreeMarker() throws Exception {
        //创建一个模板文件
        //创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板文件保存的目录
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Zhang\\IdeaProjects\\e3-parent\\e3-item-web\\src\\main\\webapp\\WEB-INF\\ftl"));
        //模板文件的编码格式
        configuration.setDefaultEncoding("utf-8");
        //加载一个模板文件，创建一个模板对象
        Template template = configuration.getTemplate("hello.ftl");
        //创建一个数据集，可以是pojo也可以使map
        Map data = new HashMap<>();
        data.put("hello","hello freemarker!");
        //创建一个writer对象，执行输出文件的路径及文件名
        Writer out = new FileWriter(new File("E:\\hello.txt"));
        //生成静态页面
        template.process(data,out);
        //关闭流
        out.close();
    }
}
