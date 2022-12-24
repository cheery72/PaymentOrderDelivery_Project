package com.project.product.domain.delivery;

import com.project.product.domain.BaseTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table
@Entity
@NoArgsConstructor
public class Delivery extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String gu;

    private String dong;

    private String addressDetail;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private LocalDateTime completeTime;

    private LocalDateTime takeOverTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
