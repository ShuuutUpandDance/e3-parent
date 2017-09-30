package com.e3mall.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import javax.jms.*;

public class ActiveMqTest {
    /**
     * 点到点发送消息
     * @throws Exception
     */
    @Test
    public void testQueueProducer() throws Exception {
        //创建一个连接工厂对象，指定服务的ip及端口
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.134:61616");
        //使用工厂对象创建一个connection对象
        Connection connection = connectionFactory.createConnection();
        //开启连接，调用start方法
        connection.start();
        //创建一个session对象
        //1.是否开启事务；
        //2.开启时本参数无意义，不开启时代表应答模式，一般自动应答或手动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用session对象创建一个destination对象。有queue和topic，这里用queue
        Queue queue = session.createQueue("test-queue");
        //使用session对象创建一个producer对象
        MessageProducer producer = session.createProducer(queue);
        //创建一个message对象
        TextMessage textMessage = session.createTextMessage("hello activemq");
        //发送消息
        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testQueueConsumer() throws Exception{
        //创建一个connectionfactory对象连接MQ服务器
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.134:61616");
        //创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");

        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //等待接收消息
        System.in.read();

        consumer.close();
        session.close();
        connection.close();
    }
}
