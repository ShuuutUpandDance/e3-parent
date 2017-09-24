package com.e3mall.pagehelper;

import com.e3mall.mapper.TbItemMapper;
import com.e3mall.pojo.TbItem;
import com.e3mall.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-dao.xml/")
public class PageHelperTest {
    @Autowired
    private TbItemMapper itemMapper;

    @Test
    public void testPageHelper() throws Exception{
        //设置分页信息
        PageHelper.startPage(1,10);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> tbItemList = itemMapper.selectByExample(example);
        //取分页信息,PageInfo
        PageInfo<TbItem>pageInfo = new PageInfo<TbItem>(tbItemList);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(tbItemList.size());
    }
}
