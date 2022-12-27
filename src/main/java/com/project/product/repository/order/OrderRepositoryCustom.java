package com.project.product.repository.order;

import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto;
import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto.DeliveryPossibilityStoreOrderListRequest;
import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto.DeliveryPossibilityStoreOrderListResponse;
import com.project.product.dto.product.OrderProductListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepositoryCustom {
    Page<OrderProductListResponse> findAllByMemberOrderList(Long memberId, Pageable pageable);
    List<DeliveryPossibilityStoreOrderListResponse> findAllByStoreOrderList(String city, String gu, String dong);
}
