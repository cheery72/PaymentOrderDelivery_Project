package com.project.product.service.order;

import com.project.product.domain.event.Coupon;
import com.project.product.domain.member.Member;
import com.project.product.domain.order.Order;
import com.project.product.domain.order.PayType;
import com.project.product.domain.payment.Card;
import com.project.product.domain.payment.CardStatus;
import com.project.product.domain.product.Product;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.dto.product.OrderProductListResponse;
import com.project.product.exception.NotFindMemberException;
import com.project.product.exception.NotPaymentCardException;
import com.project.product.exception.NotPaymentPointException;
import com.project.product.repository.event.CouponRepository;
import com.project.product.repository.member.MemberRepository;
import com.project.product.repository.order.OrderRepository;
import com.project.product.repository.payment.CardRepository;
import com.project.product.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Order createOrder(OrderCreateRequest orderCreateRequest) throws RuntimeException {
        List<Product> products = productRepository.findAllById(orderCreateRequest.getProductId());
        Coupon coupon = couponRepository.findById(orderCreateRequest.getCouponId())
                .orElseThrow(NoSuchElementException::new);
        Card card = cardRepository.findById(orderCreateRequest.getCardId())
                .orElseThrow(NoSuchElementException::new);

        int discount = coupon.couponExpiryCheck(coupon);

        if (PayType.CARD.name().equals(orderCreateRequest.getPayType())) {
            paymentCardOrder(card, orderCreateRequest.getTotalPrice(), discount);
        }else if (PayType.ALL.name().equals(orderCreateRequest.getPayType())) {
            paymentPointOrder(orderCreateRequest.getPurchaser(), orderCreateRequest.getUsePoint(),discount);
            paymentCardOrder(card, orderCreateRequest.getTotalPrice() - orderCreateRequest.getUsePoint(), discount);
        }else if (PayType.POINT.name().equals(orderCreateRequest.getPayType())) {
            paymentPointOrder(orderCreateRequest.getPurchaser(), orderCreateRequest.getUsePoint(),discount);
        }

        // 결제 성공
        Order order = Order.orderBuilder(card.getCreateAt(), orderCreateRequest, products);
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
                .orElseThrow(() -> new NotFindMemberException("요청한 멤버를 찾을 수 없습니다."));
        //Todo: 포인트 확인
        if(member.memberPointCheck(usePoint,member.getPoint())){
            member.memberPointPayment(usePoint,member,discount);
            return;
        }
        throw new NotPaymentPointException("포인트가 부족합니다.");
    }

    //Todo: 주문한 물품 전체 조회
    public Page<OrderProductListResponse> findMemberOrderList(Long memberId, Pageable pageable){
        memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFindMemberException("요청한 멤버를 찾을 수 없습니다."));

        return orderRepository.findAllByMemberOrderList(memberId, pageable);
    }

    @Transactional
    public void deleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }

    //Todo: 배송 중으로 변경하기 위해서는 택배사랑 연결이 되어야되는 로직 구성



}

