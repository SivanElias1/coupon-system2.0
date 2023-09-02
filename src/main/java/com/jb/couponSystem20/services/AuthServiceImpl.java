package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.beans.User;
import com.jb.couponSystem20.login.ClientType;
import com.jb.couponSystem20.repository.CustomerRepository;
import com.jb.couponSystem20.repository.UserRepository;
import com.jb.couponSystem20.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CompanyService companyService;


    @Override
    public void register(User user) throws CouponSystemException {
        if (user.getClientType().equals(ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.SECURITY_CANNOT_CREATE_ADMIN);
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CouponSystemException(ErrMsg.SECURITY_EMAIL_ALREADY_EXISTS);
        }

        userRepository.save(user);
    }

    @Override
    public UUID login(User user) throws CouponSystemException {
        if (!userRepository.existsByEmailAndPassword(user.getEmail(), user.getPassword())) {
            throw new CouponSystemException(ErrMsg.BAD_LOGIN_EMAIL_OR_PASSWORD_INCORRECT);
        }
        switch (user.getClientType()) {
            case CUSTOMER -> user.setId(customerService.getCustomerId(user.getEmail(), user.getPassword()));
            case COMPANY -> user.setId(companyService.getCompanyId(user.getEmail(), user.getPassword()));
        }
        return tokenService.addToken(user);
    }
}
