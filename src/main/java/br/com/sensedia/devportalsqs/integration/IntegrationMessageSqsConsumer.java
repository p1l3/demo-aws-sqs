package br.com.sensedia.devportalsqs.integration;

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Simple condition to avoid initializing this listener when running integration tests that don't
 * care about it.
 *
 * Unfortunately, this is necessary because Spring Cloud tries to resolve the @SqsListener's queue URL
 * on startup, and if there's no SQS server up and running it crashes the application.
 */

@ConditionalOnProperty(
        name = "cloud.aws.sqs.listener.auto-startup", havingValue = "true"
)
@Component
public class IntegrationMessageSqsConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationMessageSqsConsumer.class);

    @SqsListener(
            value = "${integration.aws.sqs.consumer-queue}",
            deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS
    )
    public void receive(IntegrationMessage message, @Header("MessageId") String messageId) {

        LOGGER.info(
            "Receiving a IntegrationMessage (MessageId=\"{}\") from SQS queue: {}",
                messageId, message
        );
    }
}

