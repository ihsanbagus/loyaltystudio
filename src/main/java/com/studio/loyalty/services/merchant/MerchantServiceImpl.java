package com.studio.loyalty.services.merchant;

import com.studio.loyalty.dtos.MerchantDto;
import com.studio.loyalty.entities.MerchantEntity;
import com.studio.loyalty.repositories.MerchantRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantRepository MerchantRepository;

    @Override
    public Object getAll() {
        return MerchantRepository.findAll();
    }

    @Override
    public Object getOne(String id) {
        return MerchantRepository.findById(id);
    }

    @Override
    public Object save(MerchantDto merchant) throws SQLException {
        try {
            MerchantEntity u = new MerchantEntity();
            u.setMerchantname(merchant.getMerchantname());
            u.setPhone(merchant.getPhone());
            u.setAddress(merchant.getAddress());
            return MerchantRepository.save(u);
        } catch (ConstraintViolationException e) {
            throw new SQLException("Data sudah terdaftar");
        }
    }

    @Override
    public Object update(MerchantDto Merchant, String id) {
        // update belum berhasil
        MerchantEntity u = MerchantRepository.findById(id).get();
        return MerchantRepository.save(u);
    }

    @Override
    public Object delete(String id) throws SQLException {
        try {
            MerchantRepository.deleteById(id);
            return "Berhasil menghapus data";
        } catch (Exception e) {
            throw new SQLException("Data tidak ditemukan");
        }
    }
}
