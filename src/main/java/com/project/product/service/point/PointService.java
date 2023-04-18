package com.project.product.service.point;

import com.project.product.domain.member.Member;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.exception.ClientException;
import com.project.product.exception.ErrorCode;
import com.project.product.repository.member.MemberRepository;
import com.project.product.repository.payment.CardRepository;
import com.project.product.service.member.MemberService;
import com.project.product.service.payment.CardService;
import com.project.product.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointService implements PaymentService<Object> {

    private final MemberRepository memberRepository;

    private boolean memberPointCheck(int userPoint, int memberAvailablePoint){
        return userPoint <= memberAvailablePoint;
    }

    @Transactional
    @Override
    public Integer payment(OrderCreateRequest orderCreateRequest, int restPrice) {
        Member member = memberRepository.findById(orderCreateRequest.getPurchaser())
                .orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_MEMBER));

        if(memberPointCheck(orderCreateRequest.getUsePoint(), member.getPoint())){
            return member.memberPointPayment(orderCreateRequest.getUsePoint(), restPrice);
        }

        throw new ClientException(ErrorCode.REJECT_POINT_PAYMENT);
    }
}
