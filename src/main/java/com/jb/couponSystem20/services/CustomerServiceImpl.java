package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {

    public void couponPurchase(int customerId, int couponId) throws CouponSystemException {
        Customer customerToPurchase = customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        Coupon couponToPurchase = couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        if (customerRepository.existsByCouponsAndId(couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS)), customerId)) {
            throw new CouponSystemException(ErrMsg.YOU_ALREADY_HAVE_THIS_COUPON);
        }
        if (couponToPurchase.getAmount() <= 0) {
            throw new CouponSystemException(ErrMsg.COUPON_AMOUNT_IS_0);
        }
        if (couponRepository.existsByEndDateBeforeAndId(LocalDate.now(),couponId )) {
            throw new CouponSystemException(ErrMsg.COUPON_DATE_IS_EXPIRED);
        }
        List<Coupon> couponList = customerToPurchase.getCoupons();
        couponList.add(couponToPurchase);
        customerToPurchase.setCoupons(couponList);
        customerRepository.saveAndFlush(customerToPurchase);
        couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
        couponRepository.saveAndFlush(couponToPurchase);

    }


    @Override
    public List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemException {
        return couponRepository.findByCustomers(customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS)));
    }

    @Override
    public List<Coupon> getCustomerCouponsByCategory(int customerId, Category category) throws CouponSystemException {
        return couponRepository.findByCustomersAndCategory(customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS)), category);
    }

    @Override
    public List<Coupon> getCustomerCouponsByMaxPrice(int customerId, double maxPrice) throws CouponSystemException {
        if (maxPrice < 0) {
            throw new CouponSystemException(ErrMsg.PRICE_MUST_BE_GREATER_THEN_0);
        }
        return couponRepository.findByCustomersAndPriceBetween(customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS)), 0, maxPrice);
    }

    @Override
    public Customer getCustomerDetails(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
    }

    @Override
    public int getCustomerId(String email, String password) {
        int customerId = customerRepository.findByEmailAndPassword(email, password);
        return customerId;
    }

    @Override
    public boolean login(String email, String password) {
        return customerRepository.existsByEmailAndPassword(email, password);
    }

}
