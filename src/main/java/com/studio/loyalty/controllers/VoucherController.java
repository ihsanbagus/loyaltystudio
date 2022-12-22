package com.studio.loyalty.controllers;

import com.studio.loyalty.dtos.VoucherDto;
import com.studio.loyalty.entities.VoucherEntity;
import com.studio.loyalty.services.voucher.VoucherService;
import com.studio.loyalty.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @Autowired
    VoucherService voucherService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid VoucherDto voucher) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", voucherService.save(voucher));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", voucherService.getAll());
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid VoucherDto voucher) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", voucherService.update(voucher, voucher.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid VoucherEntity voucher) {
        try {
            return ResponseUtils.generate(HttpStatus.OK, "Success", voucherService.delete(voucher.getId()));
        } catch (Exception e) {
            return ResponseUtils.generate(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
