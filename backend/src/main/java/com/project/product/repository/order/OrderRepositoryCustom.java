package com.project.product.repository.order;

import com.project.product.dto.product.OrderProductListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {
    Page<OrderProductListDto> findAllByMemberOrderList(Long memberId, Pageable pageable);
}
