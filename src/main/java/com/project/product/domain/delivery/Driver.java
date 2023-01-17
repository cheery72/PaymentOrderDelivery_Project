package com.project.product.domain.delivery;

import com.project.product.domain.BaseTime;
import com.project.product.dto.delivery.DriverRegisterRequest;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Builder
public class Driver extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;

    private String addressCity;

    private String addressGu;

    private String addressDong;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Delivery> deliveryList = new ArrayList<>();

    public static Driver driverBuilder(DriverRegisterRequest driverRegisterRequest){
        return Driver.builder()
                .name(driverRegisterRequest.getName())
                .phone(driverRegisterRequest.getPhone())
                .driverStatus(DriverStatus.WAITING)
                .addressCity(driverRegisterRequest.getAddressCity())
                .addressGu(driverRegisterRequest.getAddressGu())
                .addressDong(driverRegisterRequest.getAddressDong())
                .build();
    }
}
