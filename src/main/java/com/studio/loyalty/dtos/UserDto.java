package com.studio.loyalty.dtos;

import com.studio.loyalty.entities.EnumEntity;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Getter
@Setter
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer point = 0;

    private String username;

    private String phone;

    private String email;

    private String password;

    private EnumEntity.ROLES rolename;

    private EnumEntity.RANK rankname;
}
