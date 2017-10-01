package com.e3mall.activemq;

import javax.jms.Destination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-activemq.xml"})
public class ActiveMqSpring {
    //初始化spring容器，加载对象
    //加载jmsTemplate对象
    @Autowired
    private JmsTemplate jmsTemplate;
    //加载Destination对象
    @Autowired
    private Destination topicDestination;


    @Test
    public void sendMessage() throws Exception {
        //发送消息
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("send activemq message");
            }
        });
    }


}
