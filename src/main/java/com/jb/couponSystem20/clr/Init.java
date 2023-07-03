package com.jb.couponSystem20.clr;

import com.jb.couponSystem20.beans.Category;
import com.jb.couponSystem20.beans.Company;
import com.jb.couponSystem20.beans.Coupon;
import com.jb.couponSystem20.beans.Customer;
import com.jb.couponSystem20.repository.CompanyRepository;
import com.jb.couponSystem20.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
public class Init implements CommandLineRunner {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        Coupon coupon1 = Coupon.builder()
                .category(Category.VOUCHER)
                .title("50% off")
                .description("fun day in the lona park")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(60)
                .price(100)
                .image("https://media.giphy.com/media/wP8jWbeY0FMchnbODv/giphy.gif")
                .build();
        Coupon coupon2 = Coupon.builder()
                .category(Category.VACATION)
                .title("pay for 2 nights get the third for free")
                .description("not on weekend")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(50)
                .price(1200)
                .image("https://media.giphy.com/media/SsaWuR3owjU7a0G8z1/giphy.gif")
                .build();
        Coupon coupon3 = Coupon.builder()
                .category(Category.VOUCHER)
                .title("100$ voucher")
                .description("can be use on amazon")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(4)))
                .amount(40)
                .price(350)
                .image("https://giphy.com/clips/credit-card-dad-jokes-joke-bRqjWFVB4FccTuInqV")
                .build();
        Coupon coupon4 = Coupon.builder()
                .category(Category.VOUCHER)
                .title("day in the hotel spa")
                .description("fun day in the hotel spa with breakfast")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(50)
                .price(350)
                .image("https://media.giphy.com/media/wP8jWbeY0FMchnbODv/giphy.gif")
                .build();

        Company c1 = Company.builder()
                .name("Lona park")
                .email("LonaPark@example.com")
                .password("1234")
                .coupons(List.of(coupon1))
                .build();

        Company c2 = Company.builder()
                .name("DanHotel")
                .email("DanHotel@example.com")
                .password("1234")
                .coupons(List.of(coupon2, coupon4))
                .build();

        Company c3 = Company.builder()
                .name("Amazon")
                .email("amazon@example.com")
                .password("1234")
                .coupons(List.of(coupon3))
                .build();

        Customer customer1 = Customer.builder()
                .firstName("or")
                .lastName("asolin")
                .email("or@mail.com")
                .password("1234")
                .build();
        customerRepository.save(customer1);
        coupon1.setCompany(c1);
        coupon2.setCompany(c2);
        coupon3.setCompany(c3);
        coupon4.setCompany(c2);
        companyRepository.saveAll(List.of(c1, c2, c3));
        Company company1 = Company.builder()
                .name("KSP")
                .email("KSP@gmail.com")
                .password("1234")
                .build();
        Company company2 = Company.builder()
                .name("Burger king")
                .email("burgerKing@gmail.com")
                .password("1234")
                .build();

        Company company3 = Company.builder()
                .name("Microsoft")
                .email("microsoft@example.com")
                .password("1234")
                .build();
        Company company4 = Company.builder()
                .name("Tesla")
                .email("tesla@example.com")
                .password("1234")
                .build();
        Company company5 = Company.builder()
                .name("IBM")
                .email("ibm@example.com")
                .password("1234")
                .build();

        Company company6 = Company.builder()
                .name("SUSHI")
                .email("sushi@example.com")
                .password("1234")
                .build();

        companyRepository.saveAll(List.of(company1, company2, company3, company4, company5, company6));
        Customer c10 = Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .password("1234").build();

        Customer c11 = Customer.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("janedoe@example.com")
                .password("1234").build();

        Customer c4 = Customer.builder()
                .firstName("David")
                .lastName("Smith")
                .email("davidsmith@example.com")
                .password("1234").build();

        Customer c5 = Customer.builder()
                .firstName("Emily")
                .lastName("Jones")
                .email("emilyjones@example.com")
                .password("1234").build();

        Customer c6 = Customer.builder()
                .firstName("Robert")
                .lastName("Johnson")
                .email("robertjohnson@example.com")
                .password("1234").build();

        Customer c7 = Customer.builder()
                .firstName("Amanda")
                .lastName("Davis")
                .email("amandadavis@example.com")
                .password("1234").build();

        Customer c8 = Customer.builder()
                .firstName("Michael")
                .lastName("Wilson")
                .email("michaelwilson@example.com")
                .password("1234").build();

        Customer c9 = Customer.builder()
                .firstName("Sarah")
                .lastName("Miller")
                .email("sarahmiller@example.com")
                .password("1234").build();

        Customer c12 = Customer.builder()
                .firstName("Adam")
                .lastName("Brown")
                .email("adambrown@example.com")
                .password("1234").build();
        customerRepository.saveAll(List.of(c4, c5, c6, c7, c8, c9, c10, c11, c12));
        Coupon coupon5 = Coupon.builder()
                .company(company5)
                .category(Category.KIDS)
                .title("1+1")
                .description("buy one get the second one for free, only for kids")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
                .amount(100)
                .price(75)
                .image("https://media.giphy.com/media/kVqiI1N62Lpyg36d6t/giphy.gif")
                .build();
        Coupon coupon6 = Coupon.builder()
                .company(company4)
                .category(Category.ELECTRICITY)
                .title("5% discount on tesla")
                .description("only for a week")
                .startDate(Date.valueOf(LocalDate.now().minusWeeks(2)))
                .endDate(Date.valueOf(LocalDate.now().minusWeeks(1)))
                .amount(99)
                .price(135)
                .image("https://media.giphy.com/media/SsaWuR3owjU7a0G8z1/giphy.gif")
                .build();
        Coupon coupon7 = Coupon.builder()
                .company(company2)
                .category(Category.FOOD)
                .title("free delivery")
                .description("free delivery")
                .startDate(Date.valueOf(LocalDate.now().minusDays(5)))
                .endDate(Date.valueOf(LocalDate.now()))
                .amount(50)
                .price(350)
                .image("https://media.giphy.com/media/wP8jWbeY0FMchnbODv/giphy.gif")
                .build();


        company2.setCoupons(List.of(coupon7));
        company4.setCoupons(List.of(coupon6));
        company5.setCoupons(List.of(coupon5));
        companyRepository.saveAllAndFlush(List.of(company2, company4, company5));
    }
}
