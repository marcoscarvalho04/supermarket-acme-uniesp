package br.com.customer.controller;

import br.com.customer.config.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.CUSTOMER_API)
public class CustomerController {

    @GetMapping("/hello")
    public String hello() {
        return "my call";
    }


}
