package io.ab.shop.rocketmq.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @Description mq 生产者
 * @Return
 * @Date 2023/1/30 19:27
 */
public class RocketMQProducer {
    public static void main(String[] args) throws Exception{
        //创建消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("abProducerGroup1");
        //设置NameServer地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //启动生产者
        producer.start();
        //构建消息对象
        Message message = new Message("abTopic1", "abTag1", "Hello RocketMQ".getBytes());
        System.out.println("生产者发出的消息为：" + JSONObject.toJSONString(message));
        //发送消息并接收结果
        SendResult sendResult = producer.send(message);
        //打印结果信息
        System.out.println("生产者收到的发送结果信息为：" + JSONObject.toJSONString(sendResult));
        //关闭生产者
        producer.shutdown();
    }
}
