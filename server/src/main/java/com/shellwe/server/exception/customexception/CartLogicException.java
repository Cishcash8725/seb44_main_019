package com.shellwe.server.exception.customexception;

import com.shellwe.server.exception.exceptioncode.CartExceptionCode;
import lombok.Getter;

@Getter
public class CartLogicException extends RuntimeException {

    private CartExceptionCode cartExceptionCode;

    public CartLogicException(CartExceptionCode cartExceptionCode) {
        super(cartExceptionCode.getMessage());
        this.cartExceptionCode = cartExceptionCode;
    }
}

