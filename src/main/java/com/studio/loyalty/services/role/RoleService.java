package com.studio.loyalty.services.role;

import com.studio.loyalty.dtos.RoleDto;
import com.studio.loyalty.entities.RoleEntity;

import java.sql.SQLException;

public interface RoleService {

    Object getAll();

    Object getOne(String id);

    Object save(RoleDto role) throws SQLException;

    Object update(RoleDto role, String id);

    Object delete(String id) throws SQLException;

}
