package com.studio.loyalty.services.benefit;

import com.studio.loyalty.dtos.BenefitDto;

import java.sql.SQLException;

public interface BenefitService {

    Object getAll();

    Object getOne(String id);

    Object save(BenefitDto role) throws SQLException;

    Object update(BenefitDto role, String id);

    Object delete(String id) throws SQLException;

}
