package com.jb.couponSystem20.Exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {

    ID_ALREADY_EXISTS("id already exists in system"),
    ID_NOT_EXISTS("id not exists in system"),
    COMPANY_NAME_ALREADY_EXISTS("company name already exists in system"),
    EMAIL_ALREADY_EXISTS("company email already exists in system");


    private String massage;

    ErrMsg(String massage) {
        this.massage = massage;
    }
}
