package com.studio.loyalty;

import com.studio.loyalty.entities.EnumEntity;
import com.studio.loyalty.entities.RankEntity;
import com.studio.loyalty.entities.RoleEntity;
import com.studio.loyalty.entities.UserEntity;
import com.studio.loyalty.repositories.RankRepository;
import com.studio.loyalty.repositories.RoleRepository;
import com.studio.loyalty.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LoyaltyStudioApplication implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RankRepository rankRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(LoyaltyStudioApplication.class, args);
    }

    @Override
    public void run(String... args) {
        for (EnumEntity.ROLES role : EnumEntity.ROLES.values()) {
            if (roleRepository.existsByRolename(role)) {
                continue;
            }
            RoleEntity r = new RoleEntity();
            r.setRolename(role);
            RoleEntity admin = roleRepository.save(r);
            if (role.equals(EnumEntity.ROLES.ADMIN)) {
                createAdmin(admin);
            }
        }

        for (EnumEntity.RANK rank : EnumEntity.RANK.values()) {
            if (rankRepository.existsByRankname(rank)) {
                continue;
            }
            RankEntity r = new RankEntity();
            r.setRankname(rank);
            rankRepository.save(r);
        }
    }

    private void createAdmin(RoleEntity role) {
        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setEmail("ihsan@mail.com");
        admin.setPhone("08123456789");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRole(role);
        userRepository.save(admin);
    }

}
