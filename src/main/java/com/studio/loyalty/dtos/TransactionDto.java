package com.studio.loyalty.dtos;

import com.studio.loyalty.entities.EnumEntity;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Validated
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer quantity;

    private Double total;

    private Integer earning;

    private EnumEntity.STATUS status;

    private String userid;

    private Set<ProductDto> products;
}
