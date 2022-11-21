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

    public boolean CardPayment(int money, int paymentMoney, int couponDiscount){
        paymentMoney = paymentMoney -(paymentMoney / 10 * couponDiscount);
        if(paymentMoney <= money){
            this.money = money-paymentMoney;
            return true;
        }else{
            return false;
        }
    }
}
