package com.jb.couponSystem20.clr;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.repository.CompanyRepository;
import com.jb.couponSystem20.repository.CouponRepository;
import com.jb.couponSystem20.repository.CustomerRepository;
import com.jb.couponSystem20.services.AdminService;
import com.jb.couponSystem20.services.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class AdminServiceTests implements CommandLineRunner {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------------------------------start admin tests-------------------------------------------------------");

        Company c1 = Company.builder()
                .name("Apple")
                .email("apple@example.com")
                .password("1234")
                .build();
        Company c2 = Company.builder()
                .id(2)
                .name("tesla")
                .email("tesla@example.com")
                .password("1234")
                .build();
        Company c3 = Company.builder()
                .name("Amazon")
                .email("amazon@example.com")
                .password("1234")
                .build();
        Company c4 = Company.builder()
                .name("Amazon2")
                .email("amazon@example.com")
                .password("1234")
                .build();
        System.out.println("test -----------------> login admin ----------> failed, wrong email");
        System.out.println(adminService.login("no@admin.com", "admin"));
        System.out.println("test -----------------> login admin ----------> failed, wrong password");
        System.out.println(adminService.login("admin@admin.com", "nope"));
        System.out.println("test -----------------> login admin ----------> success");
        System.out.println(adminService.login("admin@admin.com", "admin"));

        System.out.println("test -----------------> add company ----------> failed, id already exists");
        try {
            adminService.addCompany(c2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> add company ----------> failed, company name already exists");
        try {
            adminService.addCompany(c3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> add company ----------> failed, company email already exists");
        try {
            adminService.addCompany(c4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> add company ----------> success");
        adminService.addCompany(c1);
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("test -----------------> update company ----------> failed, cannot change company id");
        Company companyToUpdate = companyRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        companyToUpdate.setId(2);
        try {
            adminService.updateCompany(1, companyToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        companyToUpdate.setId(1);
        System.out.println("test -----------------> update company ----------> failed, cannot change company name");
        companyToUpdate.setName("new name!!");
        try {
            adminService.updateCompany(1, companyToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        companyToUpdate.setName("Lona park");
        System.out.println("test -----------------> update company ----------> success");
        companyToUpdate.setEmail("update!!_email@mail.com");
        adminService.updateCompany(1, companyToUpdate);
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("test -----------------> delete company ----------> failed, id not exists");
        try {
            adminService.deleteCompany(88);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> delete company ----------> success");
        adminService.deleteCompany(2);
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("test -----------------> get all companies ----------> success");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("test -----------------> get single company ----------> success");
        System.out.println(adminService.getSingleCompany(3));
        System.out.println("test -----------------> add customer ----------> failed, id already exists");
        Customer customer1 = customerRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));

        try {
            adminService.addNewCustomer(customer1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> add customer ----------> failed, customer email already exists");
        customer1.setId(3);
        try {
            adminService.addNewCustomer(customer1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        customer1.setId(1);
        System.out.println("test -----------------> add customer ----------> success");
        Customer customer2 = Customer.builder()
                .firstName("sivan")
                .lastName("elias")
                .email("sivan@mail.com")
                .password("1234")
                .build();
        adminService.addNewCustomer(customer2);
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("test -----------------> update customer ----------> failed,id not exists");
        try {
            adminService.updateCustomer(44, customer2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> update customer ----------> failed,cant change customer id");
        customer2.setId(10);
        try {
            adminService.updateCustomer(2, customer2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        customer2.setId(2);
        System.out.println("test -----------------> update customer ----------> success");
        customer2.setEmail("update email!!");
        customer2.setFirstName("update name");
        adminService.updateCustomer(2, customer2);
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("test -----------------> delete customer ----------> failed, id not exists");
        try {
            adminService.deleteCustomer(33);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("test -----------------> delete customer ----------> success");
        Coupon coupon1 = couponRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        customer2.setCoupons(List.of(coupon1));
        adminService.updateCustomer(customer2.getId(), customer2);
        adminService.deleteCustomer(customer1.getId());
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("test -----------------> get all customers ----------> success");
        Customer customer3 = Customer.builder()
                .firstName("itzik")
                .lastName("djo")
                .email("itzik@mail.com")
                .password("1234")
                .build();
        Coupon coupon2 = couponRepository.findById(4).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        adminService.addNewCustomer(customer3);
        customer3.setCoupons(List.of(coupon2));
        adminService.updateCustomer(customer3.getId(), customer3);
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("test -----------------> get single customer ----------> success");
        System.out.println(adminService.getSingleCustomer(2));

        System.out.println("------------------------------------finish admin tests-------------------------------------------------------");
    }
}
