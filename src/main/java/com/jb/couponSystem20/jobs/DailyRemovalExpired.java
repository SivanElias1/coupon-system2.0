package com.jb.couponSystem20.jobs;

import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.repository.CouponRepository;
import com.jb.couponSystem20.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DailyRemovalExpired {
    @Autowired
    private CouponRepository couponRepository;

    @Scheduled(fixedRate = 1000*60)
    public void deleteExpiredCoupons() {
        System.out.println("searching for expired coupons");
        List<Coupon>couponExpired = couponRepository.findByEndDateBefore(LocalDate.now());
        couponExpired.forEach(System.out::println);
        couponRepository.deleteAll(couponExpired);
        System.out.println("expired coupons deleted");
    }
}
