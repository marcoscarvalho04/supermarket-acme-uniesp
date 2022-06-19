package br.com.notification.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationRequest {

    private String cpfCustomer;
    private String message;
    private String sender;
    private String customerMail;
}
