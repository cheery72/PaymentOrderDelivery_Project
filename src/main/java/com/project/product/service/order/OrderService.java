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
import java.util.UUID;

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
    public Order createOrder(OrderCreate orderCreate){
        List<Product> products = productRepository.findAllById(orderCreate.getProductId());
        Member member = memberRepository.findById(orderCreate.getPurchaser())
                .orElseThrow(NoSuchElementException::new);
        Coupon coupon = couponRepository.findById(orderCreate.getCouponId())
                .orElseThrow(NoSuchElementException::new);

        Card card = cardRepository.findById(orderCreate.getCardId())
                .orElseThrow(NoSuchElementException::new);

        //Todo: 쿠폰 확인
        int discount = coupon.couponExpiryCheck(coupon);
        //Todo: 결제 수단 확인
        if(PayType.CARD.name().equals(orderCreate.getPayType())){
            //Todo: 결제 가능 여부
            if(CardStatus.TRANSACTION_POSSIBILITY.equals(card.getCardStatus())){
                //Todo: 결제 금액 확인
                if(card.CardPayment(card.getMoney(),orderCreate.getTotalPrice(),discount)){
                    // 결제 성공
                    Order order = Order.orderBuilder(card.getCreateAt(), orderCreate, products, member.getId());
                    orderRepository.save(order);

                    return order;
                }
            }
        }else if(PayType.POINT.name().equals(orderCreate.getPayType())){
            //Todo: 포인트 확인
            if(member.memberPointCheck(orderCreate.getUsePoint(),member.getPoint())){
                member.memberPointChange(orderCreate.getUsePoint(),member,discount);
                // 결제 성공
                Order order = Order.orderBuilder(card.getCreateAt(), orderCreate, products, member.getId());
                orderRepository.save(order);

                return order;
            }
        }else if(PayType.ALL.name().equals(orderCreate.getPayType())){
            //Todo: 포인트 확인
            if(member.memberPointCheck(orderCreate.getUsePoint(),member.getPoint())
            //Todo: 결제 수단 확인
            && (CardStatus.TRANSACTION_POSSIBILITY.equals(card.getCardStatus()))){
                member.memberPointChange(orderCreate.getUsePoint(),member,discount);
                if(card.CardPayment(card.getMoney(), orderCreate.getTotalPrice()-orderCreate.getUsePoint(),coupon.getDiscount())){
                    // 결제 성공
                    Order order = Order.orderBuilder(card.getCreateAt(), orderCreate, products, member.getId());
                    orderRepository.save(order);

                    return order;
                }
            }
        }

    return null;
    }
}

