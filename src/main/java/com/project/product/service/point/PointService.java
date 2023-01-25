package com.project.product.service.point;

import com.project.product.domain.member.Member;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.exception.ClientException;
import com.project.product.exception.ErrorCode;
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

    @Transactional
    public int pointPayment(OrderCreateRequest orderCreateRequest, int discount) {
        Member member = memberRepository.findById(orderCreateRequest.getPurchaser())
                .orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_MEMBER));

        if(memberPointCheck(orderCreateRequest.getUsePoint(), member.getPoint())){
            return member.memberPointPayment(orderCreateRequest.getTotalPrice(), orderCreateRequest.getUsePoint(), discount);
        }
        throw new ClientException(ErrorCode.REJECT_POINT_PAYMENT);

    }

    private boolean memberPointCheck(int userPoint, int memberAvailablePoint){
        return userPoint <= memberAvailablePoint;
    }

}
