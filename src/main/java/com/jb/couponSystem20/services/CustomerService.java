package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;

import java.util.List;

public interface CustomerService {

    void couponPurchase(int customerId, int couponId) throws CouponSystemException;

    List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemException;

    List<Coupon> getCustomerCouponsByCategory(int customerId, Category category);

    List<Coupon> getCustomerCouponsByMaxPrice(int customerId,double zero,double maxPrice);

    Customer getCustomerDetails(int customerId) throws CouponSystemException;


}
