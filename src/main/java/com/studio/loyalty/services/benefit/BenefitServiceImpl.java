package com.studio.loyalty.services.benefit;

import com.studio.loyalty.dtos.BenefitDto;
import com.studio.loyalty.entities.BenefitEntity;
import com.studio.loyalty.entities.RankEntity;
import com.studio.loyalty.repositories.BenefitRepository;
import com.studio.loyalty.repositories.RankRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class BenefitServiceImpl implements BenefitService {

    @Autowired
    BenefitRepository benefitRepository;

    @Autowired
    RankRepository rankRepository;

    @Override
    public Object getAll() {
        return benefitRepository.findAll();
    }

    @Override
    public Object getOne(String id) {
        return benefitRepository.findById(id);
    }

    @Override
    public Object save(BenefitDto benefit) throws SQLException {
        try {
            RankEntity rank = rankRepository.findById(benefit.getRank()).get();
            BenefitEntity u = new BenefitEntity();
            u.setDiscount(benefit.getDiscount());
            u.setRank(rank);
            return benefitRepository.save(u);
        } catch (ConstraintViolationException e) {
            throw new SQLException("Data sudah terdaftar");
        }
    }

    @Override
    public Object update(BenefitDto role, String id) {
        // update belum berhasil
        BenefitEntity u = benefitRepository.findById(id).get();
        return benefitRepository.save(u);
    }

    @Override
    public Object delete(String id) throws SQLException {
        try {
            benefitRepository.deleteById(id);
            return "Berhasil menghapus data";
        } catch (Exception e) {
            throw new SQLException("Data tidak ditemukan");
        }
    }
}
