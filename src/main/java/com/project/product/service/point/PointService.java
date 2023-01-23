package com.project.product.service.point;

import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.repository.member.MemberRepository;
import com.project.product.repository.payment.CardRepository;
import com.project.product.service.member.MemberService;
import com.project.product.service.payment.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointService {

    private final MemberRepository memberRepository;
    private final CardRepository cardRepository;

    @Transactional
    public int pointPayment(OrderCreateRequest orderCreateRequest){
        return 0;
    }
}
