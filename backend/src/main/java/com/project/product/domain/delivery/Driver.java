package com.project.product.domain.delivery;

import com.project.product.domain.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@NoArgsConstructor
public class Driver extends BaseTime {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Delivery> deliveryList = new ArrayList<>();

    @Builder
    public Driver(Long id, String name, DriverStatus driverStatus, List<Delivery> deliveryList) {
        this.id = id;
        this.name = name;
        this.driverStatus = driverStatus;
        this.deliveryList = deliveryList;
    }
}
