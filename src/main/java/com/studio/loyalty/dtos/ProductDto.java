package com.studio.loyalty.dtos;

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
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String productname;

    private double price;

    private String description;

    private String image;

    private int qty;
}
