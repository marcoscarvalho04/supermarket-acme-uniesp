package br.com.fraud.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FraudResponse {

    private Long id;
    private String description;
    private Long costumerId;
    private Boolean isFraud;
    private String name;
    private LocalDateTime createdAt;
    private String cpf;

}
