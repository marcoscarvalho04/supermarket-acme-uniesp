package br.com.notification.consumer;

import br.com.clients.notification.request.NotificationPayload;
import br.com.notification.request.NotificationRequest;
import br.com.notification.response.NotificationResponse;
import br.com.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationPayload notificationResponseMessage){
        log.info("Consumed {} from queue", notificationResponseMessage);
        this.notificationService.saveNotification(this.createRequestFromNotificationPayload(notificationResponseMessage));
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    private NotificationRequest createRequestFromNotificationPayload(NotificationPayload notificationPayload) {
        return NotificationRequest.builder()
                .cpfCustomer(notificationPayload.getCustomerCpf())
                .customerMail(notificationPayload.getCustomerMail())
                .message(notificationPayload.getMessage())
                .sender(notificationPayload.getSender())
                .build();
    }
}
