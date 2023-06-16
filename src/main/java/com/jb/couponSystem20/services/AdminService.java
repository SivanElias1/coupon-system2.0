package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Customer;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    void addCompany(Company company) throws CouponSystemException;

    void updateCompany(int companyId, Company company);

    void deleteCompany(int companyId);

    List<Company> getAllCompanies();

    Company getSingleCompany(int companyId) throws CouponSystemException;

    void addNewCustomer(Customer customer);

    void updateCustomer(int customerId, Customer customer);

    void deleteCustomer(int customerId);

    List<Customer> getAllCustomers();
    Customer getSingleCustomer(int customerId);

}
