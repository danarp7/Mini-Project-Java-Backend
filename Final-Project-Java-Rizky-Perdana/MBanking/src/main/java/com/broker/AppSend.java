package com.broker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.stereotype.Service;

@Service
public class AppSend {
    private static final String EXCHANGE_NAME = "request";

    public void sendToDB(String msg) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);

            String message = msg;
            channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN,
                                message.getBytes("UTF-8"));
            System.out.println(" [x] AppSend '" + message + "'");
        }
    }
}
