package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;

import java.util.List;

public interface CompanyService {
    void addNewCoupon(Coupon coupon,int companyId) throws CouponSystemException;

    void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int couponId) throws CouponSystemException;

    List<Coupon> getAllCoupons();

    List<Coupon> getAllCoupons(int companyId) throws CouponSystemException;


    List<Coupon> getAllCouponsByCategory(Category category,int companyId) throws CouponSystemException;

    List<Coupon> getAllCouponsByMaxPrice(double maxPrice,int companyId) throws CouponSystemException;

    Company getSingleCompany(int companyId) throws CouponSystemException;
    int getCompanyId(String email, String password);


}
