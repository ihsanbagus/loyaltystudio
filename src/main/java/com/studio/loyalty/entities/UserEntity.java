package com.studio.loyalty.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    private Integer point = 0;

    @NotEmpty
    private String username;

    @NotEmpty
    @Column(unique = true)
    private String phone;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    @JsonIgnore
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "users_ranks",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "rank_id")
            })
    @JsonIgnore
    @ToString.Exclude
    private RankEntity rank;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")
            })
    @ToString.Exclude
    private RoleEntity role;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<VoucherEntity> vouchers = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<TransactionEntity> transactions = new HashSet<>();

}
