package com.jb.couponSystem20.clr;

import com.jb.couponSystem20.Exceptions.CouponSystemException;
import com.jb.couponSystem20.Exceptions.ErrMsg;
import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.repository.CouponRepository;
import com.jb.couponSystem20.services.CompanyService;
import com.jb.couponSystem20.services.CompanyServiceImpl;
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
    private CompanyServiceImpl companyService;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------------------------------start company tests-------------------------------------------------------");

        System.out.println("test -----------------> login company ----------> failed, wrong email");
        System.out.println(companyService.login("stam@example.com", "1234"));
        System.out.println("test -----------------> login company ----------> failed, wrong password");
        System.out.println(companyService.login("amazon@example.com", "12345"));
        System.out.println("test -----------------> login company ----------> success");
        System.out.println(companyService.login("amazon@example.com", "1234"));

        System.out.println("test -----------------> add coupon ----------> failed, title already exists");
        Coupon coupon2 = couponRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        try {
            companyService.addNewCoupon(coupon2, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
        companyService.addNewCoupon(coupon1, 4);
        companyService.getAllCoupons().forEach(System.out::println);
        System.out.println("test -----------------> update coupon ----------> failed, id not exists");
        try {
            companyService.updateCoupon(44, coupon1,coupon1.getCompany().getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> update coupon ----------> failed, cannot change coupon id");
        Coupon couponToUpdate = couponRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXISTS));
        couponToUpdate.setId(55);
        try {
            companyService.updateCoupon(1, couponToUpdate,couponToUpdate.getCompany().getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        couponToUpdate.setId(1);
        System.out.println("test -----------------> update coupon ----------> failed, cannot change coupon company id");
        Company companyToChange = companyService.getSingleCompany(3);
        couponToUpdate.setCompany(companyToChange);
        try {
            companyService.updateCoupon(couponToUpdate.getId(), couponToUpdate,couponToUpdate.getCompany().getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        couponToUpdate.setCompany(companyService.getSingleCompany(1));
        System.out.println("test -----------------> update coupon ----------> success");
        couponToUpdate.setTitle("update title!!");
        couponToUpdate.setDescription("update !!");
        companyService.updateCoupon(couponToUpdate.getId(), couponToUpdate,couponToUpdate.getCompany().getId());
        companyService.getAllCoupons().forEach(System.out::println);
        System.out.println("test -----------------> delete coupon ----------> failed, id not exists");
        try {
            companyService.deleteCoupon(55);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("test -----------------> delete coupon ----------> success");
        try {
            companyService.deleteCoupon(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        companyService.getAllCoupons(4).forEach(System.out::println);
        System.out.println("test -----------------> get all company coupons ----------> success");
        companyService.getAllCoupons(4).forEach(System.out::println);
        System.out.println("test -----------------> get all company coupons by category ----------> success");
        companyService.getAllCouponsByCategory(Category.ELECTRICITY, 4).forEach(System.out::println);
        System.out.println("test -----------------> get all company coupons by max price ----------> success");
        companyService.getAllCouponsByMaxPrice(5000, 4).forEach(System.out::println);
        System.out.println("test -----------------> get single company coupons ----------> failed, company id not exists");
        try {
            System.out.println(companyService.getSingleCompany(55));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> get single company coupons ----------> success");
        System.out.println(companyService.getSingleCompany(4));

        System.out.println("------------------------------------finish company tests-------------------------------------------------------");

    }
}