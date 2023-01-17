package com.project.product.domain.delivery;

import com.project.product.domain.BaseTime;
import com.project.product.domain.order.Order;
import com.project.product.dto.order.OrderPurchaserAddressResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery extends BaseTime{

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

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public static Delivery toDelivery(Order order, Driver driver){
        return Delivery.builder()
                .city(order.getMember().getAddressCity())
                .gu(order.getMember().getAddressGu())
                .dong(order.getMember().getAddressDong())
                .addressDetail(order.getMember().getAddressDetail())
                .deliveryStatus(DeliveryStatus.SHIPPING)
                .takeOverTime(LocalDateTime.now())
                .driver(driver)
                .order(order)
                .build();
    }

    public void setDeliveryStatus(){
        this.deliveryStatus = DeliveryStatus.COMPLETE;
        this.completeTime = LocalDateTime.now();
    }
}
