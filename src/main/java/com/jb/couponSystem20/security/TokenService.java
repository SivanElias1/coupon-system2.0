package com.jb.couponSystem20.security;

import com.jb.couponSystem20.beans.User;
import com.jb.couponSystem20.login.ClientType;

import java.util.UUID;

public interface TokenService {

    UUID addToken(User user);

    boolean isUserAllowed(UUID token, ClientType clientType);

    void clear();

    int getCustomerId(UUID token);
}
