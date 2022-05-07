package br.com.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.fraud")

public class FraudApplication {

    public static void main(String args[]) {
        SpringApplication.run(FraudApplication.class, args);
    }
}
