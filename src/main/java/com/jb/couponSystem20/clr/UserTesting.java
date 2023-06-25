package com.jb.couponSystem20.clr;

import com.jb.couponSystem20.beans.User;
import com.jb.couponSystem20.login.ClientType;
import com.jb.couponSystem20.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTesting implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        User u1 = User.builder()
                .email("admin@admin.com")
                .password("admin")
                .clientType(ClientType.ADMINISTRATOR)
                .build();

        User u2 = User.builder()
                .email("itzik@mail.com")
                .password("1234")
                .clientType(ClientType.CUSTOMER)
                .build();

        userRepository.saveAll(List.of(u1, u2));

    }
}
