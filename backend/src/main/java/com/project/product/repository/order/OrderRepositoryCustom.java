package com.project.product.repository.order;

import com.project.product.dto.order.MemberOrderListDto;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderRepositoryCustom {
    List<MemberOrderListDto> findAllByMemberOrderList(Long memberId, Pageable pageable);
}
