package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.repository.CouponRepository;
import com.jb.couponSystem20.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public void couponPurchase(int couponId, int customerId) throws CouponSystemException {
        Customer customerToPurchase = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        Coupon couponToPurchase = couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        if (customerRepository.existsByCoupon(couponId)) {
            throw new CouponSystemException(ErrMsg.YOU_ALREADY_HAVE_THIS_COUPON);
        }
        if (couponToPurchase.getAmount() <= 0) {
            throw new CouponSystemException(ErrMsg.COUPON_AMOUNT_IS_0);
        }
        if (!couponRepository.isDateAvailable(couponId)) {
            throw new CouponSystemException(ErrMsg.COUPON_DATE_IS_EXPIRED);
        }
        customerToPurchase.setCoupons(List.of(couponToPurchase));
        customerRepository.saveAndFlush(customerToPurchase);
        couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
        couponRepository.saveAndFlush(couponToPurchase);

    }


    @Override
    public List<Coupon> getCustomerCoupons() {
        return null;
    }

    @Override
    public List<Coupon> getCustomerCouponsByCategory() {
        return null;
    }

    @Override
    public List<Coupon> getCustomerCouponsByMaxPrice() {
        return null;
    }

    @Override
    public Customer getCustomerDetails(int customerId) {
        return null;
    }
}
