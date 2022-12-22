package com.studio.loyalty.controllers;

import com.studio.loyalty.components.TokenManager;
import com.studio.loyalty.dtos.JwtResponse;
import com.studio.loyalty.dtos.LoginDto;
import com.studio.loyalty.dtos.UserDto;
import com.studio.loyalty.entities.EnumEntity;
import com.studio.loyalty.entities.RankEntity;
import com.studio.loyalty.entities.RoleEntity;
import com.studio.loyalty.entities.UserEntity;
import com.studio.loyalty.repositories.RankRepository;
import com.studio.loyalty.repositories.RoleRepository;
import com.studio.loyalty.repositories.UserRepository;
import com.studio.loyalty.services.auth.JwtUserDetailsService;
import com.studio.loyalty.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RankRepository rankRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String jwtToken = tokenManager.generateToken(userDetails);
            return ResponseUtils.generate(HttpStatus.OK, "Success", new JwtResponse(userDetails.getUsername(), jwtToken));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto user) {
        try {
            RoleEntity role = roleRepository.findByRolename(EnumEntity.ROLES.CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
            RankEntity rank = rankRepository.findByRankname(EnumEntity.RANK.SILVER).orElseThrow(() -> new RuntimeException("Rank not found"));
            UserEntity u = new UserEntity();
            u.setUsername(user.getUsername());
            u.setPassword(passwordEncoder.encode(user.getPassword()));
            u.setPhone(user.getPhone());
            u.setEmail(user.getEmail());
            u.setRole(role);
            u.setRank(rank);
            return ResponseUtils.generate(HttpStatus.CREATED, "Success", userRepository.save(u));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
