package com.jb.couponSystem20.Exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {

    ID_ALREADY_EXISTS("id already exists in system"),
    ID_NOT_EXISTS("id not exists in system"),
    ERROR_CANT_CHANGE_ID("you cant change customer id"),
    ERROR_CANT_CHANGE_COUPON_ID("you cant change coupon id"),
    ERROR_COMPANY_ID_NOT_MATCH("you cant change coupon company id"),
    YOU_ALREADY_HAVE_THIS_COUPON("you already have this coupon and cant have twice"),
    COUPON_AMOUNT_IS_0("coupon amount is 0, you cant purchase"),
    COUPON_DATE_IS_EXPIRED("coupon date is expired, you cant purchase"),
    COMPANY_ID_ERROR("you cant change your company id"),
    COMPANY_NAME_ERROR("you cant change your company name"),
    COMPANY_NAME_ALREADY_EXISTS("company name already exists in system"),
    EMAIL_ALREADY_EXISTS("company email already exists in system"),
    TITLE_ALREADY_TOKEN("title is already token"),
    CUSTOMER_EMAIL_ALREADY_EXISTS("customer email already exists in system");


    private String massage;

    ErrMsg(String massage) {
        this.massage = massage;
    }
}
