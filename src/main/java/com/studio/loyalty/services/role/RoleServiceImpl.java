package com.studio.loyalty.services.role;

import com.studio.loyalty.dtos.RoleDto;
import com.studio.loyalty.entities.EnumEntity;
import com.studio.loyalty.entities.RoleEntity;
import com.studio.loyalty.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Object getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Object getOne(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public Object save(RoleDto role) throws SQLException {
        try {
            RoleEntity r = new RoleEntity();
            r.setRolename(role.getRolename());
            return roleRepository.save(r);
        } catch (ConstraintViolationException e) {
            throw new SQLException("Data sudah terdaftar");
        }
    }

    @Override
    public Object update(RoleDto role, String id) {
        // update belum berhasil
        RoleEntity depDB = roleRepository.findById(id).get();
        depDB.setRolename(role.getRolename());
        return roleRepository.save(depDB);
    }

    @Override
    public Object delete(String id) throws SQLException {
        try {
            roleRepository.deleteById(id);
            return "Berhasil menghapus data";
        } catch (Exception e) {
            throw new SQLException("Data tidak ditemukan");
        }
    }
}
