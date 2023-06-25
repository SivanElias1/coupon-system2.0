package com.jb.couponSystem20.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrDetails {
    private final String title = "ERROR";
    private String description;
}
