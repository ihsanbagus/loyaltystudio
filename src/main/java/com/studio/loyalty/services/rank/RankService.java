package com.studio.loyalty.services.rank;

import com.studio.loyalty.dtos.RankDto;

import java.sql.SQLException;

public interface RankService {

    Object getAll();

    Object getOne(String id);

    Object save(RankDto role) throws SQLException;

    Object update(RankDto role, String id);

    Object delete(String id) throws SQLException;

}
