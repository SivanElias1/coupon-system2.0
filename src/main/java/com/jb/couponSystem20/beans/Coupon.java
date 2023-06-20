package com.jb.couponSystem20.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;
    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    @JsonIgnore
    private Company company;
    @ManyToMany(mappedBy = "coupons", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Customer> customers;

}
