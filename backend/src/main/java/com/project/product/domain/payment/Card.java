package com.project.product.domain.payment;

import com.project.product.domain.BaseTime;
import com.project.product.domain.member.Member;
import com.project.product.dto.CardRegister;
import lombok.*;

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

    @Builder
    public Card(Long id, String name, int money, CardStatus cardStatus, Member member) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.cardStatus = cardStatus;
        this.member = member;
    }

    public static Card cardBuilder(CardRegister cardRegister){
        return Card.builder()
                .name(cardRegister.getName())
                .money(50000)
                .cardStatus(CardStatus.TRANSACTION_POSSIBILITY)
                .build();
    }

    public void cardPayment(int money, int paymentMoney, int couponDiscount){
        this.money = money-(paymentMoney - (paymentMoney / 100 * couponDiscount));
    }


    public boolean cardPaymentCheck(int money, int paymentMoney, int couponDiscount){
        if(paymentMoney - (paymentMoney / 100 * couponDiscount) <= money){
            return true;
        }
        return false;
    }
}
