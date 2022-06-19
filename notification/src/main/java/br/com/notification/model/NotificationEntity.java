package br.com.notification.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_notification")
@Data
public class NotificationEntity {

    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    private Long id;

    private LocalDateTime sendAt = LocalDateTime.now();

    @Column(name = "cpf_customer")
    private String cpfCustomer;

    private String message;
    private String sender;
    private String customerMail;


}
