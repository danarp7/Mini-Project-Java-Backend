package com.broker;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class AppRecv {
    private final static String QUEUE_NAME = "response";

    public String recvFromDB() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        GetResponse response;

        do {
            response = channel.basicGet(QUEUE_NAME, true);
        } while (response == null);

        String message = new String(response.getBody(), "UTF-8");
        channel.close();
        connection.close();

        System.out.println(" [x] AppRecv '" + message + "'");
        return message;
    }
}
