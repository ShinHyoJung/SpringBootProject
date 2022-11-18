package com.project.shop.feature.authentication.service.impl;

import com.project.shop.feature.authentication.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    @Override
    public String createAuthCode() {
        Random random = new Random();
        StringBuilder numStr = new StringBuilder();

        for(int i =0; i < 6; i++) {
            String randomStr = Integer.toString(random.nextInt(10));
            numStr.append(randomStr);
        }
        return numStr.toString();
    }
}
