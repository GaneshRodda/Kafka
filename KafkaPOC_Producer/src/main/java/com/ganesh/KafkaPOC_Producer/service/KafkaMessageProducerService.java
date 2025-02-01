package com.ganesh.KafkaPOC_Producer.service;

import com.ganesh.KafkaPOC_Producer.dao.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessageProducerService {

    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendMessage(String message) {
        final CompletableFuture<SendResult<String, Object>> future = template.send("test-demo", message);
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                throw new RuntimeException("Unable to send message=[" + message + "] due to : " + exception.getMessage());
            }
        });
    }

    public void sendCustomerData(Customer customer){
        final CompletableFuture<SendResult<String, Object>> future = template.send("test-demo",2,"Ganesh", customer);
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("Sent message=[" + customer + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                throw new RuntimeException("Unable to send message=[" + customer + "] due to : " + exception.getMessage());
            }
        });
    }
}
