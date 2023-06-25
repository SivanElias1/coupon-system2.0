package com.jb.couponSystem20.security;

import com.jb.couponSystem20.beans.User;
import com.jb.couponSystem20.login.ClientType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private Map<UUID, Information> tokens = new HashMap<>();

    @Override
    public UUID addToken(User user) {
        UUID token = UUID.randomUUID();
        Information info = Information.builder()
                .id(user.getId())
                .time(LocalDateTime.now())
                .clientType(user.getClientType())
                .build();
        // TODO: 24/06/2023 remove last tokens
        tokens.put(token, info);
        return token;
    }

    @Override
    public boolean isUserAllowed(UUID token, ClientType type) {

        Information info = tokens.get(token);
        ClientType clientType = info.getClientType();
        return clientType.equals(type);
    }

    @Override
    public void clear() {
        this.tokens.entrySet().removeIf(item -> item.getValue().getTime().isAfter(LocalDateTime.now().plusMinutes(30)));
    }

    @Override
    public int getCustomerId(UUID token) {
        return this.tokens.get(token).getId();
    }
}
