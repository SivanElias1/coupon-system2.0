package com.jb.couponSystem20.advice;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ControllerAdvice {

    @ExceptionHandler(value = CouponSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleException(Exception e) {
        return new ErrDetails(e.getMessage());
    }
}
