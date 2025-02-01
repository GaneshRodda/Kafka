package com.ganesh.KafkaPOC_Consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "test-demo", groupId = "consumer-group1")
    public void consumeMessage(String message){
        System.out.println("Consumed message: "+message);
    }

    @KafkaListener(topics = "test-demo", groupId = "consumer-group1")
    public void consumeCustomerData(Customer customer){
        System.out.println("Consumed customer data: "+customer);
    }
}
