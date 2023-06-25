package com.jb.couponSystem20.repository;

import com.jb.couponSystem20.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByName(String name);
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email,String password);
    @Query(nativeQuery = true,value = "SELECT id FROM `couponsystem2.0`.companies where email = ? and password = ? ;")
    int findByEmailAndPassword(String email, String password);
}
