package br.com.clients.fraud.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFraud {

    private Long id;
    private String description;
    private Long costumerId;
    private Boolean isFraud;
    private String name;
    private LocalDateTime createdAt;
    private String cpf;
}
