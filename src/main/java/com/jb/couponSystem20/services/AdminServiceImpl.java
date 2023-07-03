package com.jb.couponSystem20.services;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.repository.CompanyRepository;
import com.jb.couponSystem20.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {


    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (companyRepository.existsById(company.getId())) {
            throw new CouponSystemException(ErrMsg.ID_ALREADY_EXISTS);
        }
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_ALREADY_EXISTS);
        }
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_ALREADY_EXISTS);
        }

        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXISTS);
        }
        if (companyId != company.getId()) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_ERROR);
        }
        Company companyFromDB = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        if (!companyFromDB.getName().equals(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_ERROR);
        }
        company.setId(companyId);
        companyRepository.saveAndFlush(company);

    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXISTS);
        }
        List<Coupon> couponList = couponRepository.findByCompany(companyRepository.findById(companyId).orElseThrow());
        couponList.forEach(coupon -> couponRepository.deleteCoupon(coupon.getId()));
        companyRepository.deleteById(companyId);
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
    public void addNewCustomer(Customer customer) throws CouponSystemException {
        if (customerRepository.existsById(customer.getId())) {
            throw new CouponSystemException(ErrMsg.ID_ALREADY_EXISTS);
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_ALREADY_EXISTS);
        }
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException {
        if (!customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXISTS);
        }
        if (customerId != customer.getId()) {
            throw new CouponSystemException(ErrMsg.ERROR_CANT_CHANGE_ID);
        }

        customer.setId(customerId);
        customerRepository.saveAndFlush(customer);

    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSystemException {
        if (!customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXISTS);
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
    }


}
