package com.studio.loyalty.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnumEntity {
    public static enum ROLES {
        ADMIN, MERCHANT, CUSTOMER
    }

    public static enum STATUS {
        SUCCESS, FAILED, USED, UNUSED
    }

    public static enum RANK {
        SILVER, GOLD, PLATINUM
    }
}
