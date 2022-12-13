package com.project.product.repository.order;

import com.project.product.dto.product.OrderProductListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {
    Page<OrderProductListResponse> findAllByMemberOrderList(Long memberId, Pageable pageable);
}
