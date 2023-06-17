package com.jb.couponSystem20.clr;

import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.repository.CompanyRepository;
import com.jb.couponSystem20.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
public class Init implements CommandLineRunner {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        Coupon coupon1 = Coupon.builder()
                .category(Category.VOUCHER)
                .title("50% off")
                .description("fun day in the lona park")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(60)
                .price(100)
                .image("https://media.giphy.com/media/wP8jWbeY0FMchnbODv/giphy.gif")
                .build();
        Coupon coupon2 = Coupon.builder()
                .category(Category.VACATION)
                .title("pay for 2 nights get the third for free")
                .description("not on weekend")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(50)
                .price(1200)
                .image("https://media.giphy.com/media/SsaWuR3owjU7a0G8z1/giphy.gif")
                .build();
        Coupon coupon3 = Coupon.builder()
                .category(Category.VOUCHER)
                .title("100$ voucher")
                .description("can be use on amazon")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(4)))
                .amount(40)
                .price(350)
                .image("https://giphy.com/clips/credit-card-dad-jokes-joke-bRqjWFVB4FccTuInqV")
                .build();
        Coupon coupon4 = Coupon.builder()
                .category(Category.VOUCHER)
                .title("day in the hotel spa")
                .description("fun day in the hotel spa with breakfast")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(50)
                .price(350)
                .image("https://media.giphy.com/media/wP8jWbeY0FMchnbODv/giphy.gif")
                .build();
        Company c1 = Company.builder()
                .name("Lona park")
                .email("LonaPark@example.com")
                .password("1234")
                .coupons(List.of(coupon1))
                .build();

        Company c2 = Company.builder()
                .name("DanHotel")
                .email("DanHotel@example.com")
                .password("1234")
                .coupons(List.of(coupon2, coupon4))
                .build();

        Company c3 = Company.builder()
                .name("Amazon")
                .email("amazon@example.com")
                .password("1234")
                .coupons(List.of(coupon3))
                .build();

        Customer customer1 = Customer.builder()
                .firstName("or")
                .lastName("asolin")
                .email("or@mail.com")
                .password("1234")
                .build();
        customerRepository.save(customer1);
        coupon1.setCompany(c1);
        coupon2.setCompany(c2);
        coupon3.setCompany(c3);
        coupon4.setCompany(c2);
        companyRepository.saveAll(List.of(c1, c2, c3));


    }
}
