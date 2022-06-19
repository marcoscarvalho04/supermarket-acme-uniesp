package br.com.fraud.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FraudRequest {

    private String description;
    private Long costumerId;
    private Boolean isFraud;
    private String name;
    private LocalDateTime createdAt;
    private String cpf;
}
