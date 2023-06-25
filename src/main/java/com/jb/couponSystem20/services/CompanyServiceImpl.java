package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.repository.CompanyRepository;
import com.jb.couponSystem20.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {


    @Override
    public void addNewCoupon(Coupon coupon, int companyId) throws CouponSystemException {
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), companyId)) {
            throw new CouponSystemException(ErrMsg.TITLE_ALREADY_TOKEN);
        }
        coupon.setCompany(companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS)));
        couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXISTS);
        }
        if (couponId != coupon.getId()) {
            throw new CouponSystemException(ErrMsg.ERROR_CANT_CHANGE_COUPON_ID);
        }
        Coupon couponToCheck = couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        int companyId = couponToCheck.getCompany().getId();
        if (companyId != coupon.getCompany().getId()) {
            throw new CouponSystemException(ErrMsg.ERROR_COMPANY_ID_NOT_MATCH);
        }
        coupon.setId(couponId);
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) throws CouponSystemException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXISTS);
        }

        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public List<Coupon> getAllCoupons(int companyId) throws CouponSystemException {
            return couponRepository.findByCompany(companyRepository.findById(companyId).orElseThrow(()->new CouponSystemException(ErrMsg.ID_NOT_EXISTS)));

    }


    @Override
    public List<Coupon> getAllCouponsByCategory(Category category, int companyId) throws CouponSystemException {
        return couponRepository.findByCategoryAndCompany(category, companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS)));
    }

    @Override
    public List<Coupon> getAllCouponsByMaxPrice(double maxPrice, int companyId) throws CouponSystemException {
        if (maxPrice < 0) {
            throw new CouponSystemException(ErrMsg.PRICE_MUST_BE_GREATER_THEN_0);
        }
        return couponRepository.findByCompanyAndPriceBetween(companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS)), 0, maxPrice);
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
    }

    @Override
    public int getCompanyId(String email, String password) {
        int companyId = companyRepository.findByEmailAndPassword(email, password);
        return companyId;
    }

    @Override
    public boolean login(String email, String password) {
        return companyRepository.existsByEmailAndPassword(email, password);
    }
}
