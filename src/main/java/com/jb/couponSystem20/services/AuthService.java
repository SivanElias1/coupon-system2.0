package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.beans.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface AuthService {

    void register(User user) throws CouponSystemException;

    UUID login(User user) throws CouponSystemException;
}
