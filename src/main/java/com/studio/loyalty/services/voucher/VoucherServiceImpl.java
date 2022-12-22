package com.studio.loyalty.services.voucher;

import com.studio.loyalty.dtos.VoucherDto;
import com.studio.loyalty.entities.MerchantEntity;
import com.studio.loyalty.entities.VoucherEntity;
import com.studio.loyalty.repositories.MerchantRepository;
import com.studio.loyalty.repositories.UserRepository;
import com.studio.loyalty.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<VoucherEntity> getAll() {
        return voucherRepository.findAll();
    }

    @Override
    public VoucherEntity getOne(String id) {
        return voucherRepository.findById(id).orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));
    }

    @Override
    public VoucherEntity save(VoucherDto voucher) throws Exception {
        try {
            MerchantEntity merchant = merchantRepository.findById(voucher.getVouchermerchant()).orElseThrow(() -> new RuntimeException("Merchant not found"));
            VoucherEntity u = new VoucherEntity();
            u.setVouchername(voucher.getVouchername());
            u.setVoucherpoint(voucher.getVoucherpoint());
            u.setVouchercode(voucher.getVouchercode());
            u.setVoucherstatus(voucher.getVoucherstatus());
            u.setMerchant(merchant);
            return voucherRepository.save(u);
        } catch (Exception e) {
            throw new SQLException("Data sudah terdaftar " + e.getMessage());
        }
    }

    @Override
    public VoucherEntity update(VoucherDto voucher, String id) {
        // update belum berhasil
        VoucherEntity u = voucherRepository.findById(id).orElseThrow(() -> new RuntimeException("Voucher not found"));
        u.setVouchername(voucher.getVouchername());
        u.setVoucherpoint(voucher.getVoucherpoint());
        u.setVouchercode(voucher.getVouchercode());
        u.setVoucherstatus(voucher.getVoucherstatus());
        return voucherRepository.save(u);
    }

    @Override
    public Object delete(String id) throws Exception {
        try {
            voucherRepository.deleteById(id);
            return "Berhasil menghapus data";
        } catch (Exception e) {
            throw new SQLException("Data tidak ditemukan");
        }
    }
}
