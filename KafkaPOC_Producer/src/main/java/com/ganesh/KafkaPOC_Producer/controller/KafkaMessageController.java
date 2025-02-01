package com.ganesh.KafkaPOC_Producer.controller;

import com.ganesh.KafkaPOC_Producer.dao.entity.Customer;
import com.ganesh.KafkaPOC_Producer.service.KafkaMessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaMessageController {

    @Autowired
    private KafkaMessageProducerService kafkaMessageProducerService;

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody String message) {
        try {
            kafkaMessageProducerService.sendMessage(message);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while publishing message");
        }
        return ResponseEntity.ok("Message published successfully");
    }

    @PostMapping("/publishCustomer")
    public ResponseEntity<String> sendCustomerData(@RequestBody Customer customer) {
        try {
            kafkaMessageProducerService.sendCustomerData(customer);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while publishing message");
        }
        return ResponseEntity.ok("Message published successfully");
    }
}
