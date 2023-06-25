package com.jb.couponSystem20.controllers;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/add/company")
    @ResponseStatus(HttpStatus.CREATED)
    void addCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(company);
    }

    @GetMapping("/get/company/{companyId}")
    Company getSingleCompany(@PathVariable int companyId) throws CouponSystemException {
        return adminService.getSingleCompany(companyId);
    }

    @PutMapping("/update/company/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCompany(@PathVariable int companyId, @RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(companyId, company);
    }

    @DeleteMapping("/delete/company/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCompany(@PathVariable int companyId) throws CouponSystemException {
        adminService.deleteCompany(companyId);
    }

    @GetMapping("/getAllCompanies")
    List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    @PostMapping("/add/customer")
    @ResponseStatus(HttpStatus.CREATED)
    void addNewCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.addNewCustomer(customer);
    }

    @GetMapping("/get/customer/{customerId}")
    Customer getSingleCustomer(@PathVariable int customerId) throws CouponSystemException {
        return adminService.getSingleCustomer(customerId);
    }

    @PutMapping("/update/customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/delete/customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable int customerId) throws CouponSystemException {
        adminService.deleteCustomer(customerId);
    }

    @GetMapping("/getAllCustomers")
    List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }
}
