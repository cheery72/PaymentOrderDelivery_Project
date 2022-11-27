package com.project.product.service.member;

import com.project.product.domain.member.Member;
import com.project.product.dto.MemberCreate;
import com.project.product.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class MemberServiceTest {


    @Mock
    private Member member;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("멤버 회원가입")
    public void createMember(){
        MemberCreate memberCreate = new MemberCreate("asdf@naver.com", "1234", "김경민"
                , "이미지1", "광주광역시", "북구", "각화동", "아파트명 동 호");
        Member member = Member.memberBuilder(memberCreate);

        when(memberRepository.save(any()))
                .thenReturn(member);

        Member newMember = memberService.joinMember(memberCreate);

        assertEquals(newMember.getEmail(),"asdf@naver.com");
    }
}