package com.project.product.controller.member;

import com.project.product.domain.member.Member;
import com.project.product.dto.member.MemberCreateRequest;
import com.project.product.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<Object> memberJoin(
            @RequestBody @Valid MemberCreateRequest memberCreateRequest) {

        Member newMember = memberService.createMember(memberCreateRequest);
        memberService.createProvideCoupon(newMember);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
