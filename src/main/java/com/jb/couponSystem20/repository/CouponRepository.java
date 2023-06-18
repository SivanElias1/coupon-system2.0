package com.jb.couponSystem20.repository;

import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsByTitleAndCompanyId(String title, int companyId);
    @Query(nativeQuery = true,value = "SELECT EXISTS (SELECT * FROM `couponsystem2.0`.coupons where id = ? and end_date > current_date())as res")
    boolean isDateAvailable(int couponId);

    List<Coupon> findByCategory(Category category);
    List<Coupon> findByPriceBetween(double zero,double maxPrice);

}
