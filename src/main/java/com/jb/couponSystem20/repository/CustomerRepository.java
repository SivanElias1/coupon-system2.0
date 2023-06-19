package com.jb.couponSystem20.repository;

import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);
        boolean existsByCoupons(Coupon coupon);
        boolean existsByCouponsAndId(Coupon coupon,int customerId);

}
