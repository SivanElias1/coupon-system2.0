package com.jb.couponSystem20.clr;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.repository.CouponRepository;
import com.jb.couponSystem20.services.AdminService;
import com.jb.couponSystem20.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(4)
public class CustomerServiceTests implements CommandLineRunner {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CouponRepository couponRepository;


    @Override
    public void run(String... args) throws Exception {


        System.out.println("test -----------------> coupon purchase ----------> success");
        Customer c1 = Customer.builder()
                .firstName("eli")
                .lastName("choen")
                .email("eli@mail.com")
                .password("1234")
                .build();
        adminService.addNewCustomer(c1);
        customerService.couponPurchase(4, 4);
        System.out.println(customerService.getCustomerDetails(4));
        System.out.println("test -----------------> coupon purchase ----------> failed,you cant have same coupon twice");
        try {
            customerService.couponPurchase(4, 4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> coupon purchase ----------> failed,coupon amount is 0");
        Coupon couponToPurchase = couponRepository.findById(5).orElseThrow();
        couponToPurchase.setAmount(0);
        couponRepository.saveAndFlush(couponToPurchase);
        try {
            customerService.couponPurchase(4, 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        couponToPurchase.setAmount(40);
        System.out.println("test -----------------> coupon purchase ----------> failed,coupon date is expired");
        couponToPurchase.setEndDate(Date.valueOf(LocalDate.now().minusDays(1)));
        couponRepository.saveAndFlush(couponToPurchase);
        try {
            customerService.couponPurchase(4, 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> get customer coupons ----------> success");
        customerService.couponPurchase(4,5);
        customerService.getCustomerCoupons(4).forEach(System.out::println);
    }
}
