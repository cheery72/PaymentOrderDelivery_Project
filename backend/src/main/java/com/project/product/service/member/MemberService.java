package com.project.product.service.member;

import com.project.product.domain.member.Member;
import com.project.product.dto.member.MemberCreate;
import com.project.product.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member joinMember(MemberCreate memberCreate){

        Member member = Member.memberBuilder(memberCreate);

        return memberRepository.save(member);
    }
}
