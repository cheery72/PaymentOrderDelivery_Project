package com.project.product.repository.order;

import com.project.product.domain.order.OrderStatus;
import com.project.product.domain.order.QOrder;
import com.project.product.domain.product.QProduct;
import com.project.product.dto.product.OrderProductListResponse;
import com.project.product.dto.product.QOrderProductListResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private static final QOrder qOrder = QOrder.order;
    private static final QProduct qProduct = QProduct.product;

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<OrderProductListResponse> findAllByMemberOrderList(Long memberId, Pageable pageable) {
        List<OrderProductListResponse> orderProductListDtoList = queryFactory
                .select(new QOrderProductListResponse(
                        qProduct.id,
                        qProduct.name,
                        qProduct.price,
                        qProduct.category,
                        qOrder.orderStatus.stringValue(),
                        qOrder.paymentDateTime.stringValue()
                ))
                .from(qOrder)
                .leftJoin(qProduct)
                .on(qProduct.order.id.eq(qOrder.id))
                .where(
                        qOrder.member.id.eq(memberId),
                        qOrder.orderStatus.notIn(OrderStatus.CANCELLATION)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(orderProductListDtoList,pageable,pageable.getOffset());
    }
}
