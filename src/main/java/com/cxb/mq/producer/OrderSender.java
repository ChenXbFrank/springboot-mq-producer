package com.cxb.mq.producer;

import com.cxb.mq.entity.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *  发送消息
     * @param order
     * @throws Exception
     *  http://127.0.0.1:15672 在这里面创建order-exchange 和 order-queue
     */
    public void send(Order order) throws Exception{
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend(
                "order-exchange", // exchange
                "order.abcd", // routingKey
                order, // 消息体内容
                correlationData // 消息唯一ID
        );
    }
}
