package br.com.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.customer")
public class CustomerAplication {

    public static void main(String args[]) {
        SpringApplication.run(CustomerAplication.class, args);
    }

}
