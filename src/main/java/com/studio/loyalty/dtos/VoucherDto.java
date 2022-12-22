package com.studio.loyalty.dtos;

import com.studio.loyalty.entities.EnumEntity;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDto {

    private static final long serialVersionUID = 1L;

    private String id;

    private String vouchername;

    private Integer voucherpoint;

    private String vouchercode;

    private EnumEntity.STATUS voucherstatus;

    private String vouchermerchant;

    private String voucheruser;
}
