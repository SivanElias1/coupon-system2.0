package com.jb.couponSystem20.repository;

import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsByTitleAndCompanyId(String title, int companyId);

    boolean existsByEndDateBeforeAndId(LocalDate now, int couponId);

    List<Coupon> findByCategory(Category category);

    List<Coupon> findByCategoryAndCompany(Category category, Company companyId);

    List<Coupon> findByCompanyAndPriceBetween(Company companyId, double zero, double maxPrice);

    List<Coupon> findByCustomers(Customer customerId);

    List<Coupon> findByCustomersAndCategory(Customer customerId, Category category);

    List<Coupon> findByCustomersAndPriceBetween(Customer customerId, double zero, double maxPrice);

    List<Coupon> findByEndDateBefore(LocalDate now);

    List<Coupon> findByCompany(Company companyId);
    @Query(nativeQuery = true, value = "SELECT company_id FROM `couponsystem2.0`.coupons;")
    List<Integer> findCompanyId();


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete  FROM `couponsystem2.0`.customers_coupons where coupons_id = ?;")
    void deleteCoupon(int couponsId);




}
