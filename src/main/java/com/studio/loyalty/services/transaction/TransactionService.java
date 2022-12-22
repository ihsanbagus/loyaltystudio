package com.studio.loyalty.services.transaction;

import com.studio.loyalty.dtos.TransactionDto;
import com.studio.loyalty.entities.TransactionEntity;

import java.util.List;

public interface TransactionService {

    List<TransactionEntity> getAll();

    TransactionEntity getOne(String id);

    TransactionEntity save(TransactionDto transaction) throws Exception;

    TransactionEntity update(TransactionDto transaction, String id);

    Object delete(String id) throws Exception;

}
