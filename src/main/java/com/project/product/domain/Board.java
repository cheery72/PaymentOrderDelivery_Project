package com.project.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String memberId;

    private String title;

    private String content;

    private boolean isCheck;

    private boolean isDelete;

    @Builder
    public Board(Long id, String memberId, String title, String content, boolean isCheck, boolean isDelete) {
        this.id = id;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.isCheck = isCheck;
        this.isDelete = isDelete;
    }
}
