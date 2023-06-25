package com.jb.couponSystem20.jobs;

import com.jb.couponSystem20.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearExpiredTokens {
    @Autowired
    private TokenService tokenService;
    @Scheduled(fixedRate = 1000*60)
    public void clear(){
        tokenService.clear();
    }
}
