package com.studio.loyalty.services.auth;

import com.studio.loyalty.entities.UserEntity;
import com.studio.loyalty.repositories.RoleRepository;
import com.studio.loyalty.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        if (email.equals(user.getEmail())) {
            return new User(user.getEmail(),
                    user.getPassword(),
                    Collections.singletonList((GrantedAuthority) () -> "ROLE_" + user.getRole().getRolename().name())
            );
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}