package com.project.product.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND_MEMBER(-1000,"존재하지 않는 사용자 입니다.",HttpStatus.NOT_FOUND),
    NOT_FOUND_ORDER(-1001,"존재하지 않는 주문 입니다.",HttpStatus.NOT_FOUND),
    REJECT_POINT_PAYMENT(-1002,"포인트 부족으로 결제에 실패했습니다.",HttpStatus.FORBIDDEN),
    REJECT_ACCOUNT_PAYMENT(-1003,"잔액 부족으로 결제에 실패했습니다.",HttpStatus.FORBIDDEN),
    NOT_FOUND_DELIVERY(-1004,"존재하지 않는 배달 정보 입니다.",HttpStatus.NOT_FOUND),
    NOT_FOUND_DRIVER(-1005,"존재하지 않는 배달원 입니다.",HttpStatus.NOT_FOUND),
    NOT_FOUND_STORE(-1006,"존재하지 않는 가게 입니다.",HttpStatus.NOT_FOUND);

    ErrorCode(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;
}
