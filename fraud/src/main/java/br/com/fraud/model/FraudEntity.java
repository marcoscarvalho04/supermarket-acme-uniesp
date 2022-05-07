package br.com.fraud.model;

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
@Table(name = "tb_fraud")
@Data
public class FraudEntity {

    @Id
    @SequenceGenerator(
            name = "fraud_id_sequence",
            sequenceName = "fraud_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_sequence"
    )
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "costumer_id")
    private Long costumerId;

    @Column(name = "is_fraud")
    private Boolean isFraud;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
