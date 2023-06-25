package com.jb.couponSystem20.repository;

import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    boolean existsByCoupons(Coupon coupon);

    boolean existsByCouponsAndId(Coupon coupon, int customerId);

    boolean existsByEmailAndPassword(String email, String password);
    @Query(nativeQuery = true, value = "SELECT id FROM `couponsystem2.0`.customers where email = ? and password = ? ;")
    int findByEmailAndPassword(String email, String password);

}
