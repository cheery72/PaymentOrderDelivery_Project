package com.project.product.dto;

import com.project.product.domain.payment.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class MemberCardListDto {

    private Long id;

    private String status;

    private String name;


    public static List<MemberCardListDto> MemberCardListDtoBuilder(List<Card> cards){
        return cards.stream()
                .map(card -> MemberCardListDto.builder()
                        .id(card.getId())
                        .status(String.valueOf(card.getCardStatus()))
                        .name(card.getName())
                        .build())
                        .collect(Collectors.toList());
    }

    @Builder
    public MemberCardListDto(Long id, String status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }
}
