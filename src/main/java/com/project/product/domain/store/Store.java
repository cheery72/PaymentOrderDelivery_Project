package com.project.product.domain.store;

import com.project.product.domain.review.Review;
import com.project.product.dto.product.StoreRegisterRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String name;

    private String city;

    private String gu;

    private String dong;

    private String phone;

    private String detail;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Store(Long id, String name, String city, String gu, String dong, String phone, String detail, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.phone = phone;
        this.detail = detail;
        this.reviews = reviews;
    }

    public static Store toStore(StoreRegisterRequest storeRegisterRequest){
        return Store.builder()
                .city(storeRegisterRequest.getCity())
                .gu(storeRegisterRequest.getGu())
                .dong(storeRegisterRequest.getDong())
                .detail(storeRegisterRequest.getDetail())
                .name(storeRegisterRequest.getName())
                .phone(storeRegisterRequest.getPhone())
                .build();
    }
}
