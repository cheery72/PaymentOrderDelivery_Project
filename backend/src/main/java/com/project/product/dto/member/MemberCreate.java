package com.project.product.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberCreate {

    private String email;

    private String password;

    private String name;

    private String image;

    private String addressCity;

    private String addressGu;

    private String addressDong;

    private String addressDetail;
}
