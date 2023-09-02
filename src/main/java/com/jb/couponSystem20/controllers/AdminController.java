package com.jb.couponSystem20.controllers;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.login.ClientType;
import com.jb.couponSystem20.security.TokenService;
import com.jb.couponSystem20.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED)
    void addCompany(@RequestHeader(value = "Authorization") UUID token, @RequestBody Company company) throws CouponSystemException {
       if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);     }
        adminService.addCompany(company);
    }

    @GetMapping("/company/{companyId}")
    Company getSingleCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        return adminService.getSingleCompany(companyId);
    }

    @PutMapping("/company/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @RequestBody Company company) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        adminService.updateCompany(companyId, company);
    }

    @DeleteMapping("/company/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        adminService.deleteCompany(companyId);
    }

    @GetMapping("/companies")
    List<Company> getAllCompanies(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        return adminService.getAllCompanies();
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    void addNewCustomer(@RequestHeader(value = "Authorization") UUID token, @RequestBody Customer customer) throws CouponSystemException {
       if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
       }
        adminService.addNewCustomer(customer);
    }

    @GetMapping("/customer/{customerId}")
    Customer getSingleCustomer(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
           throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);}
        return adminService.getSingleCustomer(customerId);
    }

    @PutMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCustomer(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId, @RequestBody Customer customer) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        adminService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId) throws CouponSystemException {
       if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
           throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        adminService.deleteCustomer(customerId);
    }

    @GetMapping("/customers")
    List<Customer> getAllCustomers(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.SECURITY_UNAUTHRAIZD);
        }
        return adminService.getAllCustomers();
    }
}
