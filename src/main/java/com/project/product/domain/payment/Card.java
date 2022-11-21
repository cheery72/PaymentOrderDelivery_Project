package com.project.product.domain.payment;

import com.project.product.domain.BaseTime;
import com.project.product.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Getter
@NoArgsConstructor
@Setter
public class Card extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int money;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Card(Long id, String name, int money, CardStatus cardStatus, Member member) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.cardStatus = cardStatus;
        this.member = member;
    }

    public void cardPayment(int money, int paymentMoney, int couponDiscount){
        this.money = money-(paymentMoney+couponDiscount);
    }


    public boolean cardPaymentCheck(int money, int paymentMoney, int couponDiscount){
        if(paymentMoney -(paymentMoney / 10 * couponDiscount) <= money){
            return true;
        }
        return false;
    }
}
