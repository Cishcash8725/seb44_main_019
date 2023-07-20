package com.shellwe.server.exception.exceptioncode;

import lombok.Getter;

@Getter
public enum CartExceptionCode {

    CART_ALREADY_EXISTS(400, "Cart Duplicated");

    private int status;

    private String message;

    CartExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
