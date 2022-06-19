package br.com.clients.notification.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationPayload {

    @JsonProperty("customerCpf")
    private String customerCpf;

    @JsonProperty("message")
    private String message;

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("customerMail")
    private String customerMail;
}
