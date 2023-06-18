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

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CompanyRepository companyRepository;
    private int companyId;

    @Override
    public void addNewCoupon(Coupon coupon) throws CouponSystemException {
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), coupon.getCompany().getId())) {
            throw new CouponSystemException(ErrMsg.TITLE_ALREADY_TOKEN);
        }
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
    public List<Coupon> getAllCouponsByCategory(Category category) {
        return couponRepository.findByCategory(category);
    }

    @Override
    public List<Coupon> getAllCouponsByMaxPrice(double maxPrice) {
        return couponRepository.findByPriceBetween(0,maxPrice);
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
    }
}
