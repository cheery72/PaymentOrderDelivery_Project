package com.project.product.service.order;

import com.project.product.domain.order.Order;
import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto.DeliveryPossibilityStoreOrderListRequest;
import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto.DeliveryPossibilityStoreOrderListResponse;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.dto.product.OrderProductListResponse;
import com.project.product.exception.NotFoundMemberException;
import com.project.product.repository.member.MemberRepository;
import com.project.product.repository.order.OrderRepository;
import com.project.product.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Order createOrder(LocalDateTime paymentTime, OrderCreateRequest orderCreateRequest) throws RuntimeException {
        return orderRepository.save(Order.orderBuilder(paymentTime, orderCreateRequest,
                productRepository.findAllById(orderCreateRequest.getProductId())));
    }

    public List<DeliveryPossibilityStoreOrderListResponse> findStoreOrderList(DeliveryPossibilityStoreOrderListRequest deliveryPossibilityStoreOrderListRequest){
        return orderRepository.findAllByStoreOrderList(deliveryPossibilityStoreOrderListRequest);
    }

    //Todo: 주문한 물품 전체 조회
    public Page<OrderProductListResponse> findMemberOrderList(Long memberId, Pageable pageable){
        memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("요청한 멤버를 찾을 수 없습니다."));

        return orderRepository.findAllByMemberOrderList(memberId, pageable);
    }

    @Transactional
    public void deleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }

    //Todo: 배송 중으로 변경하기 위해서는 택배사랑 연결이 되어야되는 로직 구성



}

