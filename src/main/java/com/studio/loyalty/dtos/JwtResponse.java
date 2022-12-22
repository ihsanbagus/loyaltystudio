package com.studio.loyalty.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;
    private String token;
}