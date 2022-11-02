package com.nttdata.nova.bookStore.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final KafkaStringProducer stringProducer;

    public CommandLineAppStartupRunner(KafkaStringProducer stringProducer) {
        this.stringProducer = stringProducer;
    }

    @Override
    public void run(String... args) throws Exception {
        stringProducer.sendMessage(new String("Waiting for some petition..."));
    }
}