package com.project.product.dto.payment;

import com.project.product.domain.payment.Card;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class MemberCardListResponse {

    private Long id;

    private String status;

    private String name;
    
    public static List<MemberCardListResponse> memberCardListDtoBuilder(List<Card> cards){
        return cards.stream()
                .map(card -> MemberCardListResponse.builder()
                        .id(card.getId())
                        .status(String.valueOf(card.getCardStatus()))
                        .name(card.getName())
                        .build())
                        .collect(Collectors.toList());
    }

    @Builder
    public MemberCardListResponse(Long id, String status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }
}
