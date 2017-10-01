package com.e3mall.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-jedis.xml"})
public class JedicClientTest {
    @Autowired
    private JedisClient jedisClient;

    @Test
    public void testClient() throws Exception{
        jedisClient.set("mytest","jedisClient");
        System.out.println(jedisClient.get("mytest"));
    }
}
