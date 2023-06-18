package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;

import java.util.List;

public interface CompanyService {
    void addNewCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int couponId) throws CouponSystemException;

    List<Coupon> getAllCoupons();


    List<Coupon> getAllCouponsByCategory(Category category);

    List<Coupon> getAllCouponsByMaxPrice(double maxPrice);

    Company getSingleCompany(int companyId) throws CouponSystemException;


}
