package com.studio.loyalty.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vouchers")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VoucherEntity extends BaseEntity {

    @NotEmpty
    private String vouchername;

    @NotNull
    private Integer voucherpoint;

    @NotEmpty
    private String vouchercode;

    @Enumerated(EnumType.STRING)
    private EnumEntity.STATUS voucherstatus = EnumEntity.STATUS.UNUSED;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "vouchers_merchants",
            joinColumns = {@JoinColumn(name = "voucher_id")},
            inverseJoinColumns = {@JoinColumn(name = "merchant_id")
            })
    @ToString.Exclude
    private MerchantEntity merchant;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vouchers_users",
            joinColumns = {@JoinColumn(name = "voucher_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")
            })
    @JsonIgnore
    @ToString.Exclude
    private Set<UserEntity> users = new HashSet<>();
}
