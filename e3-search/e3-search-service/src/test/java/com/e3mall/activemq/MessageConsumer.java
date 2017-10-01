package com.e3mall.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-activemq.xml"})
public class MessageConsumer {
    @Test
    public void testConsumer() throws Exception {
        //等待
        System.in.read();
    }
}
