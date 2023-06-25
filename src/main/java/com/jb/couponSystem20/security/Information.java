package com.jb.couponSystem20.security;

import com.jb.couponSystem20.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Information {

    private int id;
    private LocalDateTime time;
    private ClientType clientType;
}
