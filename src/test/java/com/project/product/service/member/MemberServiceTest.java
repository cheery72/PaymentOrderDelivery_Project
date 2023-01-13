package com.project.product.service.member;

import com.project.product.domain.member.Member;
import com.project.product.domain.member.MemberCoupon;
import com.project.product.dto.member.MemberCreateRequest;
import com.project.product.repository.member.MemberCouponRepository;
import com.project.product.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@EnableAsync
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class MemberServiceTest {


    @Mock
    private Member member;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberCouponRepository memberCouponRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("멤버 회원가입")
    public void createMember() throws InterruptedException {
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest("asdf@naver.com", "1234", "김경민"
                , "이미지1", "광주광역시", "북구", "각화동", "아파트명 동 호");
        Member member = Member.memberBuilder(memberCreateRequest);
        MemberCoupon coupon = MemberCoupon.builder()
                .name("신규가입 쿠폰")
                .discount(10)
                .member(member)
                .build();

        when(memberRepository.save(any()))
                .thenReturn(member);

        when(memberCouponRepository.save(any()))
                .thenReturn(coupon);

        Member newMember = memberService.createMember(memberCreateRequest);
        memberService.createProvideCoupon(newMember);

        System.out.println("끝?");
        assertEquals(newMember.getEmail(),"asdf@naver.com");
    }
}