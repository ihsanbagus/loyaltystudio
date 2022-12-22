package com.studio.loyalty.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Serializable {
    private static final long serialVersionUID = 2636936156391265891L;

    @NotEmpty(message = "Email harus diisi")
    private String email;

    @NotEmpty(message = "Password harus diisi")
    private String password;
}
