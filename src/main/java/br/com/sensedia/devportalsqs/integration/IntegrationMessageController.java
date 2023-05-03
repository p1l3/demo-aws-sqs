package br.com.sensedia.devportalsqs.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationMessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationMessageController.class);

    @Autowired
    private IntegrationMessageSender integrationMessageSender;

    @GetMapping("/api/v1/send")
    public ResponseEntity<Void> send() {

        integrationMessageSender.send();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/v1/read")
    public ResponseEntity<Void> read() {

        integrationMessageSender.send();

        return ResponseEntity.noContent().build();
    }
}
