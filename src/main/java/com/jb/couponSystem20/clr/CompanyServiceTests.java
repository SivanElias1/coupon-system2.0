package com.jb.couponSystem20.clr;

import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(3)
public class CompanyServiceTests implements CommandLineRunner {
    @Autowired
    private CompanyService companyService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("test -----------------> add coupon ----------> success");
        Coupon coupon1 = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("buy iphone get headphone")
                .description("buy iphone 14 get headphone 2 pro for free ")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
                .amount(30)
                .price(4500)
                .company(companyService.getSingleCompany(4))
                .image("https://media.giphy.com/media/6FxJBpNTBgWdJCXKD4/giphy.gif")
                .build();
        companyService.addNewCoupon(coupon1);
        companyService.getAllCoupons().forEach(System.out::println);
    }
}
