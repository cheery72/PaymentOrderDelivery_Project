package com.project.product.repository.order;

import com.project.product.domain.order.QOrder;
import com.project.product.domain.product.QProduct;
import com.project.product.dto.order.MemberOrderListDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.awt.print.Pageable;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private static final QOrder qOrder = QOrder.order;
    private static final QProduct qProudct = QProduct.product;

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<MemberOrderListDto> findAllByMemberOrderList(Long memberId, Pageable pageable) {
        return null;
    }
}
