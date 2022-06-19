package br.com.api.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"br.com.api.gateway"})
@EnableEurekaClient
public class GatewayApp {

    public static void main(String args[]) {
        SpringApplication.run(GatewayApp.class, args);
    }
}
