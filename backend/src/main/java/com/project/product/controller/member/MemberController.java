package com.project.product.controller.member;

import com.project.product.dto.member.MemberCreate;
import com.project.product.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<Object> memberJoin(@RequestBody MemberCreate memberCreate){
        log.info("member create start ------");

        memberService.joinMember(memberCreate);

        return ResponseEntity
                .noContent()
                .build();
    }
}
