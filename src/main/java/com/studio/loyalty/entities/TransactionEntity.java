package com.studio.loyalty.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity extends BaseEntity {

    @NotNull
    @Min(value = 1, message = "Minimal pembelian 1 pcs")
    private Integer quantity;

    @NotNull
    private Double total;

    @NotNull
    private Integer earning;

    @Enumerated(EnumType.STRING)
    private EnumEntity.STATUS status = EnumEntity.STATUS.SUCCESS;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "transactions_users",
            joinColumns = {@JoinColumn(name = "transaction_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")
            })
    @ToString.Exclude
    private UserEntity user;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "transactions_products",
            joinColumns = { @JoinColumn(name = "transaction_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") })
    @ToString.Exclude
    private Set<ProductEntity> products = new HashSet<>();
}
