package com.nexus.kafka.services;

import com.nexus.kafka.model.FareWell;
import com.nexus.kafka.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    public void sendMessage(String topicName, String message) {

        kafkaTemplate.send(topicName, message);

        // if we want to check the acknowledgment from quue we can do it by return object
        // but it will slow down the performance - queue are for asynchronous not synchronous
        //CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
       /* future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });*/

        // send custom objects of multiple types
        multiTypeKafkaTemplate.send(topicName, new Greeting("Greetings", "World!"));
        multiTypeKafkaTemplate.send(topicName, new FareWell("Farewell", 25));
        multiTypeKafkaTemplate.send(topicName, "Simple string message");

    }
}
