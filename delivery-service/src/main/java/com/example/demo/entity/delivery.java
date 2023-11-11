package com.example.demo.entity;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery")
public class delivery {

    @Id
    @GeneratedValue
    private int id;
    private String recipientName;
    private String deliveryAddress;
    private LocalDateTime deliveryDate;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public enum DeliveryStatus {
        PENDING,
        IN_TRANSIT,
        DELIVERED,
        FAILED
    }
}
