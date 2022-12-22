package com.studio.loyalty.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends BaseEntity {

    @NotEmpty
    private String productname;

    @NotNull
    private Double price;

    @NotEmpty
    private String description;

    @NotEmpty
    private String image;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "products")
    @JsonIgnore
    @ToString.Exclude
    private Set<TransactionEntity> transaction;
}
