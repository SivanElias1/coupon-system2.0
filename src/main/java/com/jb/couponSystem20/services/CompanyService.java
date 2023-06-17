package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;

import java.util.List;

public interface CompanyService {
    void addNewCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(int couponId, Coupon coupon);

    void deleteCoupon(int couponId);

    List<Coupon> getAllCoupons();

    List<Coupon> getAllCouponsByCategory();

    List<Coupon> getAllCouponsByMaxPrice();

    Company getSingleCompany(int companyId) throws CouponSystemException;


}
