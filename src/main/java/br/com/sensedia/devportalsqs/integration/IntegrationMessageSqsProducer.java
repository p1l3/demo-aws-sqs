package br.com.sensedia.devportalsqs.integration;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class IntegrationMessageSqsProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationMessageSqsProducer.class);

    private final String queueName;
    private final QueueMessagingTemplate sqsTemplate;

    public IntegrationMessageSqsProducer(QueueMessagingTemplate sqsTemplate,
                                         @Value("${integration.aws.sqs.producer-queue}") String queueName) {
        this.sqsTemplate = sqsTemplate;
        this.queueName = queueName;
    }

    public void send(@Valid IntegrationMessage message) {

        /**
         * Tip: you can write your business logic here before sending the message to SQS
         */
        if (message == null) {
            throw new IllegalArgumentException("message can not be null");
        }

        LOGGER.info("Sending a new integration message to SQS queue: {}", message);
        sqsTemplate
            .convertAndSend(queueName, message);
    }
}
