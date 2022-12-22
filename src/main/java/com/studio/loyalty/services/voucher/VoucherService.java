package com.studio.loyalty.services.voucher;

import com.studio.loyalty.dtos.VoucherDto;
import com.studio.loyalty.entities.VoucherEntity;

import java.util.List;

public interface VoucherService {

    List<VoucherEntity> getAll();

    VoucherEntity getOne(String id);

    VoucherEntity save(VoucherDto role) throws Exception;

    VoucherEntity update(VoucherDto role, String id);

    Object delete(String id) throws Exception;

}
