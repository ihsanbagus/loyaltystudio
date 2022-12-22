package com.studio.loyalty.services.user;

import com.studio.loyalty.dtos.UserDto;
import com.studio.loyalty.entities.RankEntity;
import com.studio.loyalty.entities.RoleEntity;
import com.studio.loyalty.entities.UserEntity;
import com.studio.loyalty.repositories.RankRepository;
import com.studio.loyalty.repositories.RoleRepository;
import com.studio.loyalty.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RankRepository rankRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getOne(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
    }

    @Override
    public UserEntity save(UserDto user) throws Exception {
        try {
            RoleEntity role = roleRepository.findByRolename(user.getRolename()).orElseThrow(() -> new RuntimeException("Role tidak ditemukan"));
            RankEntity rank = rankRepository.findByRankname(user.getRankname()).orElse(null);
            UserEntity u = new UserEntity();
            u.setUsername(user.getUsername());
            u.setPassword(passwordEncoder.encode(user.getPassword()));
            u.setEmail(user.getEmail());
            u.setPhone(user.getPhone());
            u.setRole(role);
            u.setRank(rank);
            return userRepository.save(u);
        } catch (Exception e) {
            throw new SQLException("Data sudah terdaftar : " + e.getMessage());
        }
    }

    @Override
    public UserEntity update(UserDto user, String id) {
        // update belum berhasil
        UserEntity u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        u.setPhone(user.getPhone());
        u.setEmail(user.getEmail());
        return userRepository.save(u);
    }

    @Override
    public Object delete(String id) throws Exception {
        try {
            userRepository.deleteById(id);
            return "Berhasil menghapus data";
        } catch (Exception e) {
            throw new SQLException("Data tidak ditemukan");
        }
    }
}
