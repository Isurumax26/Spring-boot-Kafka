package com.nexus.kafka.services;

import com.nexus.kafka.model.FareWell;
import com.nexus.kafka.model.Greeting;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "multiGroup", topics = "multiType")
public class MultiTypeKafkaListener {

    @KafkaHandler
    public void handleGreetings(Greeting greeting) {
        System.out.println("Greeting received: " + greeting);
    }

    @KafkaHandler
    public void handleFarewell(FareWell fareWell) {
        System.out.println("Greeting received: " + fareWell);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println("Unkown type received: " + object);
    }
}
