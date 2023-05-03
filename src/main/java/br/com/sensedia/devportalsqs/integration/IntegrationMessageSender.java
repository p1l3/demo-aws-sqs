package br.com.sensedia.devportalsqs.integration;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IntegrationMessageSender {
    private static final Logger logger = LoggerFactory.getLogger(IntegrationMessageSender.class);

    @Value("${integration.aws.sqs.producer-queue}")
    private String producerQueueName;

    @Autowired
    private final AmazonSQSAsync amazonSqs;

    @Autowired
    private IntegrationMessageSqsProducer integrationMessageSqsProducer;

    @Autowired
    public IntegrationMessageSender(final AmazonSQSAsync amazonSQSAsync) {
        this.amazonSqs = amazonSQSAsync;
    }

    public void send() {

        IntegrationMessage message = IntegrationMessage.builder().id("1").name("teste").status("DONE").type("CREATED").build();

        integrationMessageSqsProducer.send(message);

    }

}