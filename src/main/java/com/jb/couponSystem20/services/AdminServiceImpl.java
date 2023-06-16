package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.repository.CompanyRepository;
import com.jb.couponSystem20.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (companyRepository.existsById(company.getId())) {
            throw new CouponSystemException(ErrMsg.ID_ALREADY_EXISTS);
        }
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_ALREADY_EXISTS);
        }
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXISTS);
        }

        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) {

    }

    @Override
    public void deleteCompany(int companyId) {

    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
    }

    @Override
    public void addNewCustomer(Customer customer) {

    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {

    }

    @Override
    public void deleteCustomer(int customerId) {

    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getSingleCustomer(int customerId) {
        return null;
    }
}
