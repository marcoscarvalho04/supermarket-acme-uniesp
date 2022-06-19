package br.com.notification.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {

    private Long id;
    private LocalDateTime sendAt;
    private String cpfCustomer;
    private String message;
    private String sender;
    private String customerMail;
}
