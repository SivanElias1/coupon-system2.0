package com.jb.couponSystem20.repository;

import com.jb.couponSystem20.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email,String password);
}
