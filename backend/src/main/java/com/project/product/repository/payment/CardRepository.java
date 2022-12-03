package com.project.product.repository.payment;

import com.project.product.domain.payment.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByMemberId(Long memberId);
}
