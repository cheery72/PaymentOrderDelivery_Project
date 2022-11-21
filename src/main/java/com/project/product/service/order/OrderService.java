package com.project.product.service.order;

import com.project.product.domain.event.Coupon;
import com.project.product.domain.event.CouponStatus;
import com.project.product.domain.member.Member;
import com.project.product.domain.order.Order;
import com.project.product.domain.order.OrderStatus;
import com.project.product.domain.order.PayType;
import com.project.product.domain.payment.Card;
import com.project.product.domain.payment.CardStatus;
import com.project.product.domain.product.Product;
import com.project.product.dto.OrderCreate;
import com.project.product.exception.NotPaymentCardException;
import com.project.product.exception.NotPaymentPointException;
import com.project.product.repository.coupon.CouponRepository;
import com.project.product.repository.member.MemberRepository;
import com.project.product.repository.order.OrderRepository;
import com.project.product.repository.payment.CardRepository;
import com.project.product.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final CardRepository cardRepository;

    @Transactional
    public Order createOrder(OrderCreate orderCreate) throws RuntimeException {
        List<Product> products = productRepository.findAllById(orderCreate.getProductId());
        Coupon coupon = couponRepository.findById(orderCreate.getCouponId())
                .orElseThrow(NoSuchElementException::new);
        Card card = cardRepository.findById(orderCreate.getCardId())
                .orElseThrow(NoSuchElementException::new);

        int discount = coupon.couponExpiryCheck(coupon);

        if (PayType.CARD.name().equals(orderCreate.getPayType())) {
            paymentCardOrder(card, orderCreate.getTotalPrice(), discount);
        }else if (PayType.ALL.name().equals(orderCreate.getPayType())) {
            paymentPointOrder(orderCreate.getPurchaser(), orderCreate.getUsePoint(),discount);
            paymentCardOrder(card, orderCreate.getTotalPrice() - orderCreate.getUsePoint(), discount);
        }else if (PayType.POINT.name().equals(orderCreate.getPayType())) {
            paymentPointOrder(orderCreate.getPurchaser(),orderCreate.getUsePoint(),discount);
        }

        // 결제 성공
        Order order = Order.orderBuilder(card.getCreateAt(), orderCreate, products);
        orderRepository.save(order);

        return order;
    }

    @Transactional
    public void paymentCardOrder(Card card, int totalPrice, int discount) throws NotPaymentCardException {
        //Todo: 결제 가능 여부, 결제 금액 확인
        if(CardStatus.TRANSACTION_POSSIBILITY.equals(card.getCardStatus())
            && card.cardPaymentCheck(card.getMoney(),totalPrice,discount)){
            //Todo: 결제 진행
            card.cardPayment(card.getMoney(),totalPrice,discount);
            return;
        }
        throw new NotPaymentCardException("카드 금액이 부족합니다.");
    }

    @Transactional
    public void paymentPointOrder(Long purchaser,int usePoint,int discount) throws NotPaymentPointException {
        Member member = memberRepository.findById(purchaser)
                .orElseThrow(NoSuchElementException::new);
        //Todo: 포인트 확인
        if(member.memberPointCheck(usePoint,member.getPoint())){
            member.memberPointPayment(usePoint,member,discount);
            return;
        }
        throw new NotPaymentPointException("포인트가 부족합니다.");
    }
}

