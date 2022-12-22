package com.studio.loyalty.services.rank;

import com.studio.loyalty.dtos.RankDto;
import com.studio.loyalty.entities.RankEntity;
import com.studio.loyalty.repositories.RankRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    RankRepository rankRepository;

    @Override
    public Object getAll() {
        return rankRepository.findAll();
    }

    @Override
    public Object getOne(String id) {
        return rankRepository.findById(id);
    }

    @Override
    public Object save(RankDto role) throws SQLException {
        try {
            RankEntity u = new RankEntity();
            u.setRankname(role.getRankname());
            return rankRepository.save(u);
        } catch (ConstraintViolationException e) {
            throw new SQLException("Data sudah terdaftar");
        }
    }

    @Override
    public Object update(RankDto role, String id) {
        // update belum berhasil
        RankEntity u = rankRepository.findById(id).get();
        return rankRepository.save(u);
    }

    @Override
    public Object delete(String id) throws SQLException {
        try {
            rankRepository.deleteById(id);
            return "Berhasil menghapus data";
        } catch (Exception e) {
            throw new SQLException("Data tidak ditemukan");
        }
    }
}
