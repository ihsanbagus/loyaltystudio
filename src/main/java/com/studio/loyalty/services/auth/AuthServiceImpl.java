package com.studio.loyalty.services.auth;

import com.studio.loyalty.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthServiceImpl implements AuthService{

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean register() {
        return false;
    }

    @Override
    public boolean forgotPassword() {
        return false;
    }
}
