package com.jb.couponSystem20.controllers;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.beans.User;
import com.jb.couponSystem20.dto.LoginResDto;
import com.jb.couponSystem20.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody User user ) throws CouponSystemException {
        authService.register(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody User user) throws CouponSystemException {
        String email = user.getEmail();
        UUID token = authService.login(user);
        int id = user.getId();
        return new LoginResDto(id,token,email);
    }
}
