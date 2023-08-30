package com.jb.couponSystem20.controllers;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.login.ClientType;
import com.jb.couponSystem20.security.TokenService;
import com.jb.couponSystem20.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TokenService tokenService;

    @PutMapping("/{customerId}/purchase/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void couponPurchase(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId, @PathVariable int couponId) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
//            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
//        }
//        if (tokenService.getCustomerId(token) != customerId) {
//            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
//        }
        customerService.couponPurchase(customerId, couponId);
    }

    @GetMapping("/{customerId}")
    Customer getCustomerDetails(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
//            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
//        }
//        if (tokenService.getCustomerId(token) != customerId) {
//            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
//        }
        return customerService.getCustomerDetails(customerId);
    }

    @GetMapping("/{customerId}/byMaxPrice")
    List<Coupon> getCustomerCouponsByMaxPrice(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId, @RequestParam int val) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
//            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
//        }
//        if (tokenService.getCustomerId(token) != customerId) {
//            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
//        }
        return customerService.getCustomerCouponsByMaxPrice(customerId, val);
    }

    @GetMapping("/{customerId}/byCategory")
    List<Coupon> getCustomerCouponsByCategory(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId, @RequestParam Category category) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
//            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
//        }
//        if (tokenService.getCustomerId(token) != customerId) {
//            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
//        }
        return customerService.getCustomerCouponsByCategory(customerId, category);
    }

    @GetMapping("/{customerId}/coupons")
    List<Coupon> getCustomerCoupons(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
//            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
//        }
//        if (tokenService.getCustomerId(token) != customerId) {
//            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
//        }
        return customerService.getCustomerCoupons(customerId);
    }

}
