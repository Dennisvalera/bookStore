package com.nttdata.nova.bookStore.kafka;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.nttdata.nova.bookStore.dto.BookRegistryDTO;
import com.nttdata.nova.bookStore.service.IBookRegistryService;

@Component
public class KafkaStringConsumer {

	@Autowired
	private IBookRegistryService iBookRegistryService;
	
    Logger logger = LoggerFactory.getLogger(KafkaStringConsumer.class);

    @KafkaListener(topics = "registry" , groupId = "group_id")
    public void consumer(String message) {
        logger.info("Consuming Message {}", message);
        iBookRegistryService.save(new BookRegistryDTO(message, new Date()));
    }

}