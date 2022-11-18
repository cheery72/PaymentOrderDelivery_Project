package com.project.product.service.order;

import com.project.product.domain.member.Member;
import com.project.product.domain.order.Order;
import com.project.product.domain.product.Product;
import com.project.product.dto.OrderCreate;
import com.project.product.repository.member.MemberRepository;
import com.project.product.repository.order.OrderRepository;
import com.project.product.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public Order OrderProduct(OrderCreate orderCreate){
        Product product = productRepository.findById(orderCreate.getProductId())
                .orElseThrow(NoSuchElementException::new);
        Member member = memberRepository.findById(UUID.fromString(orderCreate.getPurchaser()))
                .orElseThrow(NoSuchElementException::new);

        //Todo: 포인트 확인
        if(member.memberPointCheck(orderCreate.getUsePoint(),member.getPoint())){
            //Todo: 결제 수단 확인
        }
        return null;
    }
}
