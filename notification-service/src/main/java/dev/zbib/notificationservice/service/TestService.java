package dev.zbib.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class TestService {


    @RabbitListener(queues = "notification")
    public void receiveMessage(String message) {
        log.info(message);
        sendEmail("mohammad.habib.zbib@gmail.com", "NEW NOTF", message);
    }

    private void sendEmail(
            String to,
            String subject,
            String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mohammad.habib.zbib@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        log.info("message sent");
    }
}
