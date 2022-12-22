package com.studio.loyalty.services.user;

import com.studio.loyalty.dtos.UserDto;
import com.studio.loyalty.entities.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getOne(String id);

    UserEntity save(UserDto user) throws Exception;

    UserEntity update(UserDto role, String id);

    Object delete(String id) throws Exception;

}
