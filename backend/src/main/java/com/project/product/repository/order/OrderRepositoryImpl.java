package com.project.product.repository.order;

import com.project.product.domain.order.OrderStatus;
import com.project.product.domain.order.QOrder;
import com.project.product.domain.product.QProduct;
import com.project.product.domain.store.QStore;
import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto;
import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto.DeliveryPossibilityStoreOrderListRequest;
import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto.DeliveryPossibilityStoreOrderListResponse;
import com.project.product.dto.delivery.QDeliveryPossibilityStoreOrderListDto_DeliveryPossibilityStoreOrderListResponse;
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
    private static final QStore qStore = QStore.store;

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

    @Override
    public List<DeliveryPossibilityStoreOrderListResponse> findAllByStoreOrderList(DeliveryPossibilityStoreOrderListRequest deliveryPossibilityStoreOrderListRequest) {
        return queryFactory
                .select(new QDeliveryPossibilityStoreOrderListDto_DeliveryPossibilityStoreOrderListResponse(
                        qOrder.id,
                        qStore.id,
                        qStore.city,
                        qStore.gu,
                        qStore.dong,
                        qStore.detail
                ))
                .from(qStore)
                .leftJoin(qOrder)
                .on(qOrder.store.id.eq(qStore.id))
                .where(
                        qOrder.orderStatus.eq(OrderStatus.SHIPPING_PREPARATION),
                        qStore.city.eq(deliveryPossibilityStoreOrderListRequest.getCity()),
                        qStore.gu.eq(deliveryPossibilityStoreOrderListRequest.getGu()),
                        qStore.dong.eq(deliveryPossibilityStoreOrderListRequest.getGu())
                )
                .fetch();
    }
}
