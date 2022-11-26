package com.project.product.service.order;

import com.project.product.domain.event.Coupon;
import com.project.product.domain.member.Member;
import com.project.product.domain.order.Order;
import com.project.product.domain.order.OrderStatus;
import com.project.product.domain.payment.Card;
import com.project.product.domain.payment.CardStatus;
import com.project.product.domain.product.Product;
import com.project.product.dto.OrderCreate;
import com.project.product.exception.NotPaymentCardException;
import com.project.product.exception.NotPaymentPointException;
import com.project.product.repository.event.CouponRepository;
import com.project.product.repository.member.MemberRepository;
import com.project.product.repository.order.OrderRepository;
import com.project.product.repository.payment.CardRepository;
import com.project.product.repository.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class OrderServiceTest {

    @Mock
    private Member member;

    @Mock
    private Product product;

    @Mock
    private Card card;

    @Mock
    private Coupon coupon;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private OrderService orderService;


    public Order commonCardOrderProduct(OrderCreate orderCreate, Card card) throws Exception {

        when(productRepository.findAllById(orderCreate.getProductId()))
                .thenReturn(List.of(product));

        when(couponRepository.findById(orderCreate.getCouponId()))
                .thenReturn(Optional.of(coupon));

        when(cardRepository.findById(orderCreate.getCardId()))
                .thenReturn(Optional.of(card));

       return orderService.createOrder(orderCreate);

    }

    public Order commonCardPointOrderProduct(OrderCreate orderCreate, Card card, Member member) throws Exception {

        when(productRepository.findAllById(orderCreate.getProductId()))
                .thenReturn(List.of(product));

        when(couponRepository.findById(orderCreate.getCouponId()))
                .thenReturn(Optional.of(coupon));

        when(cardRepository.findById(orderCreate.getCardId()))
                .thenReturn(Optional.of(card));

        when(memberRepository.findById((orderCreate.getPurchaser())))
                .thenReturn(Optional.of(member));

        return orderService.createOrder(orderCreate);

    }

    @Test
    @DisplayName("카드 결제 주문 생성")
    public void orderCardProduct() throws Exception {
        OrderCreate orderCreate = new OrderCreate(List.of(1L,2L),1L,15000,0,
                "문 앞", "CARD",1L,1L);
        Card card =new Card();
        card.setCardStatus(CardStatus.TRANSACTION_POSSIBILITY);
        card.setMoney(30000);

        Order newOrder = commonCardOrderProduct(orderCreate, card);

        assertEquals(card.getMoney(),15000);
        assertEquals(newOrder.getOrderStatus(), OrderStatus.SHIPPING_PREPARATION);
    }

    @Test
    @DisplayName("포인트 결제 주문 생성")
    public void orderPointProduct() throws Exception {
        OrderCreate orderCreate = new OrderCreate(List.of(1L,2L),1L,15000,15000,
                "문 앞", "POINT",1L,1L);
        Member member = new Member();
        member.setPoint(16000);

        Order newOrder = commonCardPointOrderProduct(orderCreate, card, member);

        assertEquals(member.getPoint(),1000);
        assertEquals(member.getUsedPoint(),15000);
        assertEquals(newOrder.getOrderStatus(), OrderStatus.SHIPPING_PREPARATION);

    }

    @Test
    @DisplayName("포인트 카드 결제 주문 생성")
    public void orderPointCardProduct() throws Exception {
        OrderCreate orderCreate = new OrderCreate(List.of(1L,2L),1L,15000,12000,
                "문 앞", "ALL",1L,1L);
        Card card =new Card();
        card.setCardStatus(CardStatus.TRANSACTION_POSSIBILITY);
        card.setMoney(30000);

        Member member = new Member();
        member.setPoint(16000);

        Order newOrder = commonCardPointOrderProduct(orderCreate, card, member);

        assertEquals(card.getMoney(),27000);
        assertEquals(member.getPoint(),4000);
        assertEquals(member.getUsedPoint(),12000);
        assertEquals(newOrder.getOrderStatus(), OrderStatus.SHIPPING_PREPARATION);
    }

    @Test
    @DisplayName("카드 정지 상태일 시에 결제 주문 실패")
    public void orderCardProductFail(){
        OrderCreate orderCreate = new OrderCreate(List.of(1L,2L),1L,15000,12000,
                "문 앞", "ALL",1L,1L);
        Card card =new Card();
        card.setCardStatus(CardStatus.TRANSACTION_STOP);

        Assertions.assertThrows(NoSuchElementException.class,
                () ->  commonCardOrderProduct(orderCreate, card)).printStackTrace();
    }

    @Test
    @DisplayName("포인트 부족할시에 결제 주문 실패")
    public void orderPointProductFail() throws Exception {
        OrderCreate orderCreate = new OrderCreate(List.of(1L,2L),1L,15000,15000,
                "문 앞", "POINT",1L,1L);

        Member member = new Member();
        member.setPoint(14999);

        Assertions.assertThrows(NotPaymentPointException.class,
                () ->  commonCardPointOrderProduct(orderCreate, card, member)).printStackTrace();
    }

    @Test
    @DisplayName("계좌 금액 부족 결제 주문 실패")
    public void orderCardMoneyFail() throws Exception {
        OrderCreate orderCreate = new OrderCreate(List.of(1L,2L),1L,15000,0,
                "문 앞", "CARD",1L,1L);
        Card card = new Card();
        card.setCardStatus(CardStatus.TRANSACTION_POSSIBILITY);
        card.setMoney(14999);


        Assertions.assertThrows(NotPaymentCardException.class,
                () ->  commonCardOrderProduct(orderCreate, card)).printStackTrace();
    }

    @Test
    @DisplayName("포인트 및 카드 결제 포인트 부족 주문 실패")
    public void orderMoneyNoPointFail() throws Exception {
        OrderCreate orderCreate = new OrderCreate(List.of(1L,2L),1L,15000,7000,
                "문 앞", "ALL",1L,1L);
        Card card = new Card();
        card.setCardStatus(CardStatus.TRANSACTION_POSSIBILITY);
        card.setMoney(18000);
        Member member = new Member();
        member.setPoint(6000);

        Assertions.assertThrows(NotPaymentPointException.class,
                () ->  commonCardPointOrderProduct(orderCreate, card, member)).printStackTrace();
    }

    @Test
    @DisplayName("포인트 및 카드 결제 포인트 부족 주문 실패")
    public void orderNoMoneyPointFail() throws Exception {
        OrderCreate orderCreate = new OrderCreate(List.of(1L,2L),1L,15000,7000,
                "문 앞", "ALL",1L,1L);
        Card card = new Card();
        card.setCardStatus(CardStatus.TRANSACTION_POSSIBILITY);
        card.setMoney(6999);
        Member member = new Member();
        member.setPoint(8000);


        Assertions.assertThrows(NotPaymentCardException.class,()
                -> commonCardPointOrderProduct(orderCreate, card, member)).printStackTrace();
    }
}