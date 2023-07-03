package com.jb.couponSystem20.controllers;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.login.ClientType;
import com.jb.couponSystem20.security.TokenService;
import com.jb.couponSystem20.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/{companyId}/coupon")
    @ResponseStatus(HttpStatus.CREATED)
    void addNewCoupon(@RequestHeader(value = "Authorization") UUID token, @RequestBody Coupon coupon, @PathVariable int companyId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        if (tokenService.getCustomerId(token) != companyId) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
        }
        companyService.addNewCoupon(coupon, companyId);
    }

    @PutMapping("/{companyId}/coupon/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @PathVariable int couponId, @RequestBody Coupon coupon) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        if (tokenService.getCustomerId(token) != companyId) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
        }
        companyService.updateCoupon(couponId, coupon,companyId);
    }

    @DeleteMapping("/{companyId}/coupon/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @PathVariable int couponId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        if (tokenService.getCustomerId(token) != companyId) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
        }
        companyService.deleteCoupon(couponId);
    }

    @GetMapping("/{companyId}/coupons")
    List<Coupon> getAllCoupons(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        if (tokenService.getCustomerId(token) != companyId) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
        }
        return companyService.getAllCoupons(companyId);
    }

    @GetMapping("/{companyId}/byCategory")
    List<Coupon> getAllCouponsByCategory(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @RequestParam Category category) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        if (tokenService.getCustomerId(token) != companyId) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
        }
        return companyService.getAllCouponsByCategory(category, companyId);
    }

    @GetMapping("/{companyId}/byMaxPrice")
    List<Coupon> getAllCouponsByMaxPrice(@RequestHeader(value = "Authorization") UUID token, @RequestParam double val, @PathVariable int companyId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        if (tokenService.getCustomerId(token) != companyId) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
        }
        return companyService.getAllCouponsByMaxPrice(val, companyId);
    }

    @GetMapping("/{companyId}")
    Company getSingleCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        if (tokenService.getCustomerId(token) != companyId) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD_ID);
        }
        return companyService.getSingleCompany(companyId);
    }


}
