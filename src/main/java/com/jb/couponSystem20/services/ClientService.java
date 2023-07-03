package com.jb.couponSystem20.services;

import com.jb.couponSystem20.repository.CompanyRepository;
import com.jb.couponSystem20.repository.CouponRepository;
import com.jb.couponSystem20.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected CouponRepository couponRepository;

    public abstract boolean login(String email , String password);

}
