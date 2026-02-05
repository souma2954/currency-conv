package com.currency.conversion.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.currency.conversion.model.ConversionModel;

@Service
public class MessageProducer {

    private final KafkaTemplate<String, ConversionModel> kafkaTemplate;

    public MessageProducer(KafkaTemplate<String, ConversionModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createMessage(ConversionModel conversionModel) {
        kafkaTemplate.send("currencyConversion", conversionModel.getId(), conversionModel);
    }
}

