package com.jb.couponSystem20.repository;

import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsByTitleAndCompanyId(String title, int companyId);

    boolean existsByEndDateBefore(LocalDate now);

    List<Coupon> findByCategory(Category category);

    List<Coupon> findByPriceBetween(double zero, double maxPrice);

    List<Coupon> findByCustomers(Customer customerId);

    List<Coupon> findByCustomersAndCategory(Customer customerId, Category category);

    List<Coupon> findByCustomersAndPriceBetween(Customer customerId, double zero, double maxPrice);

}
