package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
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

    @Override
    public void addNewCoupon(Coupon coupon) throws CouponSystemException {
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), coupon.getCompany().getId())) {
            throw new CouponSystemException(ErrMsg.TITLE_ALREADY_TOKEN);
        }
        couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) {

    }

    @Override
    public void deleteCoupon(int couponId) {

    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public List<Coupon> getAllCouponsByCategory() {
        return null;
    }

    @Override
    public List<Coupon> getAllCouponsByMaxPrice() {
        return null;
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
    }
}
