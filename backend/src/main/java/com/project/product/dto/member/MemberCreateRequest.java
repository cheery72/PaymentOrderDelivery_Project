package com.project.product.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberCreateRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private String image;

    @NotBlank
    private String addressCity;

    @NotBlank
    private String addressGu;

    @NotBlank
    private String addressDong;

    @NotBlank
    private String addressDetail;
}
