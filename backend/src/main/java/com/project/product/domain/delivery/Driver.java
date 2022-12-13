package com.project.product.domain.delivery;

import com.project.product.domain.BaseTime;
import com.project.product.dto.delivery.DriverRegisterRequest;
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

    private String phone;

    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Delivery> deliveryList = new ArrayList<>();

    @Builder
    public Driver(Long id, String name, String phone, DriverStatus driverStatus, List<Delivery> deliveryList) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.driverStatus = driverStatus;
        this.deliveryList = deliveryList;
    }

    public static Driver driverBuilder(DriverRegisterRequest driverRegisterRequest){
        return Driver.builder()
                .name(driverRegisterRequest.getName())
                .phone(driverRegisterRequest.getPhone())
                .driverStatus(DriverStatus.WAITING)
                .build();
    }
}
