package br.com.notification;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication(scanBasePackages = {"br.com.notification"})
@EnableEurekaClient
public class NotificationApp {


    public static void main(String args[]) {
        SpringApplication.run(NotificationApp.class, args);
    }



}
