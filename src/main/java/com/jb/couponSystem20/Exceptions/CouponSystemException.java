package com.jb.couponSystem20.Exceptions;

public class CouponSystemException extends Exception {

    public CouponSystemException(ErrMsg errMsg) {
        super(errMsg.getMassage());
    }
}
