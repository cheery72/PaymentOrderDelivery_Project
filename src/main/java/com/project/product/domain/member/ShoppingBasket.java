package com.project.product.domain.member;

import com.project.product.domain.member.Member;
import com.project.product.domain.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShoppingBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalPrice;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "shoppingBasket",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> product;
}
