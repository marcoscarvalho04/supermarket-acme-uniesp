package br.com.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitMQMessageProducer {

    private AmqpTemplate amqpTemplate;


    public void publish(Object payload, String exchange, String routingKey){
      log.info("publishing to: {} using routingKey {} the message {}", exchange,routingKey, exchange);
      this.amqpTemplate.convertAndSend(exchange, routingKey, payload);
      log.info("published to: {} using routingKey {} the message {}", exchange,routingKey, exchange);
    }


}
