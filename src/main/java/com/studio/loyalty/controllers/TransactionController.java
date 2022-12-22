package com.studio.loyalty.controllers;

import com.studio.loyalty.dtos.TransactionDto;
import com.studio.loyalty.entities.TransactionEntity;
import com.studio.loyalty.services.transaction.TransactionService;
import com.studio.loyalty.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid TransactionDto transaction) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", transactionService.save(transaction));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", transactionService.getAll());
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid TransactionDto transaction) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", transactionService.update(transaction, transaction.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid TransactionEntity transaction) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", transactionService.delete(transaction.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
