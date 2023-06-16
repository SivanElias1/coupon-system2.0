package com.jb.couponSystem20.clr;

import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AdminServiceTests implements CommandLineRunner {
    @Autowired
    private AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        Company c1 = Company.builder()
                .name("Apple")
                .email("apple@example.com")
                .password("1234")
                .build();
        Company c2 = Company.builder()
                .id(2)
                .name("tesla")
                .email("tesla@example.com")
                .password("1234")
                .build();
        Company c3 = Company.builder()
                .name("Amazon")
                .email("amazon@example.com")
                .password("1234")
                .build();
        System.out.println("test -----------------> add company ----------> failed, id already exists");
        try {
            adminService.addCompany(c2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("test -----------------> add company ----------> failed, company name already exists");
        try {
            adminService.addCompany(c3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("test -----------------> add company ----------> success");
        adminService.addCompany(c1);
        adminService.getAllCompanies().forEach(System.out::println);

    }
}
