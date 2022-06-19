package br.com.customer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"br.com.customer", "br.com.rabbitmq"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "br.com.clients")
@EnableRabbit
public class CustomerAplication {

    public static void main(String args[]) {
        SpringApplication.run(CustomerAplication.class, args);
    }

}
