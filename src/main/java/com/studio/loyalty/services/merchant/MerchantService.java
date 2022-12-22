package com.studio.loyalty.services.merchant;

import com.studio.loyalty.dtos.MerchantDto;

import java.sql.SQLException;

public interface MerchantService {

    Object getAll();

    Object getOne(String id);

    Object save(MerchantDto Merchant) throws SQLException;

    Object update(MerchantDto role, String id);

    Object delete(String id) throws SQLException;

}
